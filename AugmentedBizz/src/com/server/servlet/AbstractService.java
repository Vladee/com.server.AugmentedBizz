package com.server.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.server.entity.ServiceTransferEntity;
import com.server.entity.datastore.Indicator;
import com.server.entity.datastore.Model;
import com.server.entity.datastore.Target;
import com.server.parser.AbstractServiceParser;

/**
 * Abstract service definition
 * 
 * @author Vladi
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractService extends HttpServlet {
	
	protected AbstractServiceParser parser = null;
	
	static {
		//register all datastore entities
		ObjectifyService.register(Indicator.class);
		ObjectifyService.register(Model.class);
		ObjectifyService.register(Target.class);
	}
	
	public AbstractService()
	{
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			checkQueryParameters(req.getParameterMap());
			String responseString = getServiceParser(req).parseServiceTransferEntity(createServiceEntityFromRequest(req));
			resp.setContentType(getServiceContentType(req));
			resp.getWriter().println(responseString);
		}
		catch(Exception e)
		{
			resp.setStatus(400);
		}
	}
	
	/**
	 * Creates a service transfer entity which is passed to the response object and delivered to the client
	 * @param request The service request from the client
	 * @return A created service transfer entity which should be processed
	 */
	public abstract ServiceTransferEntity createServiceEntityFromRequest(HttpServletRequest request);
	
	/**
	 * @return Returns the right service parser for this servlet based upon the request
	 */
	public abstract AbstractServiceParser getServiceParser(HttpServletRequest request);
	
	/**
	 * Validates the incoming URL query parameters
	 * @param parameterMap
	 * @throws BadServletParametersException
	 */
	protected abstract void checkQueryParameters(Map parameterMap) throws BadServletParametersException;
	
	/**
	 * @param request
	 * @return The content type of the response data based upon the request
	 */
	protected abstract String getServiceContentType(HttpServletRequest request);
}
