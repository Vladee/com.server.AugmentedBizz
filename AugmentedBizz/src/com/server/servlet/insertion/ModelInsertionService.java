package com.server.servlet.insertion;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.server.entity.ServiceTransferEntity;
import com.server.parser.AbstractServiceParser;
import com.server.servlet.AbstractGetService;
import com.server.servlet.BadServletParametersException;

public class ModelInsertionService extends AbstractGetService {

	@Override
	public ServiceTransferEntity createServiceEntityFromRequest(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractServiceParser getServiceParser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkQueryParameters(Map parameterMap)
			throws BadServletParametersException {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getServiceContentType(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
