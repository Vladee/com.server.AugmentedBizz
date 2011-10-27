package com.server.servlet.outbound;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.memcache.InvalidValueException;
import com.google.gson.JsonParseException;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.server.entity.ServiceTransferEntity;
import com.server.entity.transfer.TargetServiceEntity;
import com.server.entity.transfer.TargetServiceEntity.TargetState;
import com.server.parser.AbstractServiceParser;
import com.server.parser.json.JSONServiceParser;
import com.server.servlet.AbstractGetService;
import com.server.servlet.BadServletParametersException;
import com.server.entity.datastore.Model;
import com.server.entity.datastore.Target;

public class TargetService extends AbstractGetService {

	private static JSONServiceParser serviceParser = new JSONServiceParser();
	
	@Override
	public ServiceTransferEntity createServiceEntityFromRequest(HttpServletRequest request) 
	{
		Long targetId = new Long(request.getParameter("target"));
		return collectTargetInfo(targetId);
	}

	@Override
	public AbstractServiceParser getServiceParser(HttpServletRequest request) {
		return serviceParser;
	}
	
	
	private TargetServiceEntity collectTargetInfo(Long targetId)
	{
		Objectify ofy = ObjectifyService.begin();
		
		//collect datastore entities
		Target target = null;
		Model model = null;
		try
		{
			target = ofy.get(Target.class, targetId);
		}
		catch(NotFoundException e)
		{
			return new TargetServiceEntity(TargetState.NOT_FOUND, "", -1L, -1);
		}
		try
		{
			model = ofy.get(target.getModelId());
		}
		catch(NotFoundException e)
		{
			return new TargetServiceEntity(TargetState.NO_MODEL, "", -1L, -1);
		}
		
		return new TargetServiceEntity(TargetState.OK, target.getName(), model.getId(), model.getVersion());
	}

	@Override
	protected void checkQueryParameters(Map parameterMap) throws BadServletParametersException {
		if(parameterMap.size() != 1 || 
		   parameterMap.get("target") == null || 
		   ((String)(parameterMap.get("target"))).length() != 5)
		{
			throw(new BadServletParametersException());
		}
	}

	@Override
	protected String getServiceContentType(HttpServletRequest request) 
	{
		return serviceParser.getCreatedContentType();
	}
}
