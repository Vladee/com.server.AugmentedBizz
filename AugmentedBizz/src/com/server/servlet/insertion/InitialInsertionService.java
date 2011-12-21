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
		
		logger.info("Insertion service - Inserting generator data");
		insertGeneratorData();
		
		logger.info("Insertion service - Inserting robot arm data");
		insertRobotArmData();
		
		logger.info("Insertion service - Inserting helicopter data");
		insertHelicopterData();
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
			logger.warning("Failed loading teapot: " + e.getMessage());
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
	
	protected void insertGeneratorData()
	{
		//put a generator model into the datastore
		Model generatorModel = null;
		try 
		{
			generatorModel = ModelFactory.createDatastoreModelEntity(11001L, 1, getServletContext().getResourceAsStream("/WEB-INF/obj/generator.rotated.obj"), getServletContext().getResourceAsStream("/WEB-INF/texture/white.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading generator: " + e.getMessage());
			return;
		}
		getObjectify().put(generatorModel);
		//put a generator target into the datastore
		Target targetGenerator = new Target(11001L, "Generator #107", new Key<Model>(Model.class, generatorModel.getId()));
		getObjectify().put(targetGenerator);
		//put generator indicators into the datastore
		Indicator indicator1 = new Indicator(11011L, new Key<Target>(Target.class, targetGenerator.getId()), "Sensor am Druckventil EGV1 defekt", -0.4746724f, -0.1424017f, 0.170882074f);
		Indicator indicator2 = new Indicator(11012L, new Key<Target>(Target.class, targetGenerator.getId()), "Planm‰ﬂige Wartung der Zuleitung f‰llig", 0.2848034f, 0.0f, 0.142401729f);
		Indicator indicator3 = new Indicator(11013L, new Key<Target>(Target.class, targetGenerator.getId()), "Mitarbeiter bemerkte Schleifger‰usche im Spulengeh‰use", 0.0f, 0.0f, 0.189868971f);
		getObjectify().put(indicator1);
		getObjectify().put(indicator2);
		getObjectify().put(indicator3);
	}
	
	private void insertRobotArmData() {
		//put a robot model into the datastore
		Model robotModel = null;
		try 
		{
			robotModel = ModelFactory.createDatastoreModelEntity(11201L, 1, getServletContext().getResourceAsStream("/WEB-INF/obj/robot.obj"), getServletContext().getResourceAsStream("/WEB-INF/texture/white.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading roboter arm: " + e.getMessage());
			return;
		}
		getObjectify().put(robotModel);
		//put a robot target into the datastore
		Target targetRobot = new Target(11201L, "Robot Arm XZ", new Key<Model>(Model.class, robotModel.getId()));
		getObjectify().put(targetRobot);
		//put robot indicators into the datastore
		Indicator indicator1 = new Indicator(11211L, new Key<Target>(Target.class, targetRobot.getId()), "Greifarm muss kalibriert werden", -0.126184093f,	-0.492582663f, 0.685254692f);
		Indicator indicator2 = new Indicator(11212L, new Key<Target>(Target.class, targetRobot.getId()), "Roboterarmgelenk quietscht wie ein nasser Schuh", 0.006672624f, -0.486565386f, 0.685254692f);
		Indicator indicator3 = new Indicator(11213L, new Key<Target>(Target.class, targetRobot.getId()), "Schutzlack besch‰digt", 0.006672624f, 0.461662198f, 0.199106345f);
		getObjectify().put(indicator1);
		getObjectify().put(indicator2);
		getObjectify().put(indicator3);
	}
	
	private void insertHelicopterData() {
		//put a helicopter model into the datastore
		Model helicopterModel = null;
		try 
		{
			helicopterModel = ModelFactory.createDatastoreModelEntity(11301L, 1, getServletContext().getResourceAsStream("/WEB-INF/obj/helicopter.obj"), getServletContext().getResourceAsStream("/WEB-INF/texture/helicopter.png"));
		} 
		catch (Exception e) {
			logger.warning("Failed loading helicopter: " + e.getMessage());
			return;
		}
		getObjectify().put(helicopterModel);
		//put a helicopter target into the datastore
		Target targetHelicopter = new Target(11301L, "Helicopter #39", new Key<Model>(Model.class, helicopterModel.getId()));
		getObjectify().put(targetHelicopter);
		//put helicopter indicators into the datastore
		//Indicator indicator1 = new Indicator(11311L, new Key<Target>(Target.class, targetHelicopter.getId()), "Riss im Hauptrotorblatt", 0.018841345f, 0.266420144f, 0.524035922f);
		//Indicator indicator2 = new Indicator(11312L, new Key<Target>(Target.class, targetHelicopter.getId()), "Kameralinse stark verschmutzt", 0.679168868f, -0.029406586f,	0.137876387f);
		Indicator indicator3 = new Indicator(11313L, new Key<Target>(Target.class, targetHelicopter.getId()), "Bruch an der rechten Kufe", 0.1073f,	-0.0336722f,	0.016728297f);
		//getObjectify().put(indicator1);
		//getObjectify().put(indicator2);
		getObjectify().put(indicator3);
	}
}
