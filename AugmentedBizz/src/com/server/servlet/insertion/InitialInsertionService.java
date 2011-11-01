package com.server.servlet.insertion;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.server.entity.ServiceTransferEntity;
import com.server.entity.datastore.Indicator;
import com.server.entity.datastore.Model;
import com.server.entity.datastore.Target;
import com.server.model.ModelFactory;
import com.server.model.repository.DummyCubeModel;
import com.server.parser.AbstractServiceParser;
import com.server.parser.dummy.DummyServiceParser;
import com.server.servlet.AbstractGetService;
import com.server.servlet.BadServletParametersException;

/**
 * Service for the initial data insertion
 * @author Vladi
 *
 */
public class InitialInsertionService extends AbstractGetService {
	
	protected DummyServiceParser dummyParser = new DummyServiceParser();
	
	@Override
	public ServiceTransferEntity createServiceEntityFromRequest(HttpServletRequest request) {
		return null;
	}
	
	@Override
	protected void processBeforeService()
	{
		logger.info("Insertion service - Deleting all datastore data");
		dropData();
		
		logger.info("Insertion service - Inserting dummy cube data");
		insertDummyCubeData();
	}

	@Override
	public AbstractServiceParser getServiceParser(HttpServletRequest request) {
		return dummyParser;
	}

	@Override
	protected void checkQueryParameters(Map parameterMap) throws BadServletParametersException {
		if(parameterMap.size() != 0)
		{
			throw(new BadServletParametersException());
		}
	}

	@Override
	protected String getServiceContentType(HttpServletRequest request) {
		return dummyParser.getCreatedContentType();
	}
	
	protected void dropData()
	{
		//delete all data
		Query<Indicator> query1 = getObjectify().query(Indicator.class);
		getObjectify().delete(query1);
		Query<Model> query2 = getObjectify().query(Model.class);
		getObjectify().delete(query2);
		Query<Target> query3 = getObjectify().query(Target.class);
		getObjectify().delete(query3);
	}

	protected void insertDummyCubeData()
	{
		//put a dummy cube model into the datastore
		Model cubeModel = null;
		try 
		{
			cubeModel = ModelFactory.createDatastoreModelEntity(99999L, 1, new DummyCubeModel(), getServletContext().getResourceAsStream("/WEB-INF/image/cube.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading dummy cube: " + e.getMessage());
			return;
		}
		getObjectify().put(cubeModel);
		//put a dummy cube target into the datastore
		Target targetCube = new Target(99999L, "Dummy cube", new Key<Model>(Model.class, cubeModel.getId()));
		getObjectify().put(targetCube);
		//put dummy cube indicators into the datastore
		Indicator dummyCubeIndicator1 = new Indicator(99001L, new Key<Target>(Target.class, targetCube.getId()), "Dummy error indicator 1 -- Test 1", .5f, .5f, .5f);
		Indicator dummyCubeIndicator2 = new Indicator(99002L, new Key<Target>(Target.class, targetCube.getId()), "Dummy error indicator 2 -- Test 2", .0f, -.5f, .25f);
		getObjectify().put(dummyCubeIndicator1);
		getObjectify().put(dummyCubeIndicator2);
	}
	
}
