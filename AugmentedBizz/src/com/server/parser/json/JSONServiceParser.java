package com.server.parser.json;

import com.google.gson.Gson;
import com.server.entity.ServiceTransferEntity;
import com.server.parser.AbstractServiceParser;

public class JSONServiceParser extends AbstractServiceParser {

	protected Gson gson = new Gson();
	
	@Override
	public String parseServiceTransferEntity(ServiceTransferEntity stEntity) throws Exception 
	{
		if(stEntity == null)
		{
			throw(new Exception("Service entity is null"));
		}
		return gson.toJson(stEntity);
	}

	@Override
	public String getCreatedContentType() {
		return "application/json";
	}

}
