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
		
		logger.info("Insertion service - Inserting dummy box data");
		insertDummyBoxData();
		
		logger.info("Insertion service - Inserting teapot data");
		insertTeapotData();
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

	protected void insertDummyBoxData()
	{
		//put a dummy box model into the datastore
		Model boxModel = null;
		try 
		{
			boxModel = ModelFactory.createDatastoreModelEntity(99999L, 1, getServletContext().getResourceAsStream("/WEB-INF/obj/box.obj"), getServletContext().getResourceAsStream("/WEB-INF/texture/box.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading dummy box: " + e.getMessage());
			return;
		}
		getObjectify().put(boxModel);
		//put a dummy box target into the datastore
		Target targetBox = new Target(99999L, "Dummy box", new Key<Model>(Model.class, boxModel.getId()));
		getObjectify().put(targetBox);
		//put dummy box indicators into the datastore
		Indicator dummyBoxIndicator1 = new Indicator(99001L, new Key<Target>(Target.class, targetBox.getId()), "Dummy error indicator 1 -- Test 1", .5f, .5f, .5f);
		Indicator dummyBoxIndicator2 = new Indicator(99002L, new Key<Target>(Target.class, targetBox.getId()), "Dummy error indicator 2 -- Test 2", .0f, -.5f, .25f);
		getObjectify().put(dummyBoxIndicator1);
		getObjectify().put(dummyBoxIndicator2);
	}
	
	protected void insertTeapotData()
	{
		//put a teapot model into the datastore
		Model teapotModel = null;
		try 
		{
			teapotModel = ModelFactory.createDatastoreModelEntity(99998L, 1, getServletContext().getResourceAsStream("/WEB-INF/obj/teapot.obj"), getServletContext().getResourceAsStream("/WEB-INF/texture/teapot.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading teapot cube: " + e.getMessage());
			return;
		}
		getObjectify().put(teapotModel);
		//put a teapot target into the datastore
		Target targetTeapot = new Target(99998L, "Teapot", new Key<Model>(Model.class, teapotModel.getId()));
		getObjectify().put(targetTeapot);
		//put teapot indicators into the datastore
		Indicator dummyCubeIndicator1 = new Indicator(99011L, new Key<Target>(Target.class, targetTeapot.getId()), "Error indicator 1 -- Test 1", .5f, .5f, .5f);
		Indicator dummyCubeIndicator2 = new Indicator(99012L, new Key<Target>(Target.class, targetTeapot.getId()), "Error indicator 2 -- Test 2", .0f, -.5f, .25f);
		Indicator dummyCubeIndicator3 = new Indicator(99013L, new Key<Target>(Target.class, targetTeapot.getId()), "Error indicator 3 -- Test 3", 5.0f, 2.5f, -1.25f);
		getObjectify().put(dummyCubeIndicator1);
		getObjectify().put(dummyCubeIndicator2);
		getObjectify().put(dummyCubeIndicator3);
	}
	
}
