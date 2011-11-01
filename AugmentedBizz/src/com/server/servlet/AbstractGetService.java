package com.server.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.tools.util.Logging;
import com.server.entity.ServiceTransferEntity;
import com.server.parser.AbstractServiceParser;

/**
 * An abstract service only for HTTP GET requests
 * @author Vladi
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractGetService extends AbstractService {

	protected static final Logger logger = Logger.getLogger(AbstractService.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		logger.info("Service request on " + this.getClass().getName() + " from address " + req.getRemoteAddr());
		try
		{
			checkQueryParameters(req.getParameterMap());
			processBeforeService();
			String responseString = getServiceParser(req).parseServiceTransferEntity(createServiceEntityFromRequest(req));
			resp.setContentType(getServiceContentType(req));
			resp.getWriter().println(responseString);
		}
		catch(Exception e)
		{
			String stackTrace = "";
			for(int i = 0; i < e.getStackTrace().length; ++i)
			{
				stackTrace += e.getStackTrace()[i].getLineNumber() + "---" + e.getStackTrace()[i].getClassName() + "---" + e.getStackTrace()[i].getMethodName() + "\n";
			}
			logger.warning(this.getClass().getName() + " service error: '" + e.getClass().getName() + "' -> " + e.getMessage() + "\n Stack:\n" + stackTrace);
			resp.sendError(resp.SC_BAD_REQUEST, "Error while processing");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(resp.SC_FORBIDDEN, "Action forbidden");
	}
	
	
}
