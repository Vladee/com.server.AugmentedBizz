package com.server.servlet.outbound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Query;
import com.server.entity.ServiceTransferEntity;
import com.server.entity.datastore.Indicator;
import com.server.entity.datastore.Target;
import com.server.entity.transfer.IndicatorServiceEntity;
import com.server.entity.transfer.TargetServiceEntity;
import com.server.entity.transfer.TargetServiceEntity.TargetState;
import com.server.parser.AbstractServiceParser;
import com.server.parser.json.JSONServiceParser;
import com.server.servlet.AbstractGetService;
import com.server.servlet.BadServletParametersException;

public class IndicatorService extends AbstractGetService {
	
	private JSONServiceParser serviceParser = new JSONServiceParser();
	
	@Override
	public ServiceTransferEntity createServiceEntityFromRequest(HttpServletRequest request) {
		Long targetId = new Long(request.getParameter("target"));
		return collectTargetIndicatorsInfo(targetId);
	}

	@Override
	public AbstractServiceParser getServiceParser(HttpServletRequest request) {
		return serviceParser;
	}

	@Override
	protected void checkQueryParameters(Map parameterMap) throws BadServletParametersException {
		if(parameterMap.size() != 1 || 
		   parameterMap.get("target") == null || 
		   ((String[])(parameterMap.get("target")))[0].length() != 5)
		{
			throw(new BadServletParametersException());
		}
	}

	@Override
	protected String getServiceContentType(HttpServletRequest request) {
		return serviceParser.getCreatedContentType();
	}
	
	protected IndicatorServiceEntity collectTargetIndicatorsInfo(Long targetId)
	{
		Query<Indicator> indicatorQuery = getObjectify().query(Indicator.class).filter("targetId =", new Key<Target>(Target.class, targetId));
		
		List<IndicatorServiceEntity.TargetIndicator> indicatorTransferEntityList = new ArrayList<IndicatorServiceEntity.TargetIndicator>();
		for(Indicator storedIndicator : indicatorQuery)
		{
			indicatorTransferEntityList.add(new IndicatorServiceEntity.TargetIndicator(storedIndicator.getPositionX(), 
																					   storedIndicator.getPositionY(), 
																					   storedIndicator.getPositionZ(), 
																					   storedIndicator.getDescription()));
		}
		
		return new IndicatorServiceEntity(indicatorTransferEntityList);
	}
	
}
