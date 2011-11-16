package com.server.servlet.outbound;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.repackaged.com.google.common.util.Base64;
import com.googlecode.objectify.NotFoundException;
import com.server.entity.ServiceTransferEntity;
import com.server.entity.datastore.Model;
import com.server.entity.datastore.Target;
import com.server.entity.transfer.ModelServiceEntity;
import com.server.entity.transfer.TargetServiceEntity;
import com.server.entity.transfer.TargetServiceEntity.TargetState;
import com.server.parser.AbstractServiceParser;
import com.server.parser.json.JSONServiceParser;
import com.server.servlet.AbstractGetService;
import com.server.servlet.BadServletParametersException;
import com.server.util.TypeConversion;

public class ModelService extends AbstractGetService {

	private JSONServiceParser serviceParser = new JSONServiceParser();
	
	@Override
	public ServiceTransferEntity createServiceEntityFromRequest(HttpServletRequest request) {
		Long modelId = new Long(request.getParameter("model"));
		return collectModelInfo(modelId);
	}

	@Override
	public AbstractServiceParser getServiceParser(HttpServletRequest request) {
		return serviceParser;
	}

	@Override
	protected void checkQueryParameters(Map parameterMap) throws BadServletParametersException {
		if(parameterMap.size() != 1 || 
		   parameterMap.get("model") == null || 
		   ((String[])(parameterMap.get("model")))[0].length() != 5)
		{
			throw(new BadServletParametersException());
		}
	}

	@Override
	protected String getServiceContentType(HttpServletRequest request) {
		return serviceParser.getCreatedContentType();
	}

	protected ModelServiceEntity collectModelInfo(Long modelId)
	{
		Model model = null;
		try
		{
			model = getObjectify().get(Model.class, modelId);
		}
		catch(NotFoundException e)
		{
			return null;
		}
		
		return new ModelServiceEntity(TypeConversion.toFloatArrayFrom(model.getVertices().getBytes()), 
									  TypeConversion.toFloatArrayFrom(model.getNormals().getBytes()), 
									  TypeConversion.toFloatArrayFrom(model.getTexcoords().getBytes()), 
									  TypeConversion.toShortArrayFrom(model.getIndices().getBytes()), 
				                      Base64.encode(model.getTextureData().getBytes()));
	}
}
