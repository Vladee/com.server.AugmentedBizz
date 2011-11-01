package com.server.entity.transfer;

import com.server.entity.ServiceTransferEntity;

/**
 * Service entity for target information delivery
 * 
 * @author Vladi
 *
 */
public class TargetServiceEntity extends ServiceTransferEntity {
	
	/**
	 * State of the target
	 * @author Vladi
	 *
	 */
	public static enum TargetState
	{
		OK,
		NOT_FOUND,
		NO_MODEL,
	}
	
	private TargetState targetState;
	private String targetName;
	private Long modelId;
	private Integer latestModelVersion;
	
	public TargetServiceEntity(TargetState targetState, String targetName, Long modelId, Integer latestModelVersion)
	{
		this.targetState = targetState;
		this.targetName = targetName;
		this.modelId = modelId;
		this.latestModelVersion = latestModelVersion;
	}
	
	public TargetState getTargetState()
	{
		return targetState;
	}
	
	public String getTargetName() 
	{
		return targetName;
	}
	
	public Long getModelId() 
	{
		return modelId;
	}
	
	public Integer getLatestModelVersion()
	{
		return latestModelVersion;
	}
}
