package com.server.entity.datastore;

import com.googlecode.objectify.Key;
import com.server.entity.DatastoreEntity;

public class Indicator extends DatastoreEntity
{
	private Key<Target> targetId;
	private String description;
	private Float positionX;
	private Float positionY;
	private Float positionZ;
	
	protected Indicator()
	{
	}
	
	public Indicator(Long id, Key<Target> target, String description, Float positionX, Float positionY, Float positionZ)
	{
		super(id);
		this.targetId = target;
		this.description = description;
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
	}

	public Key<Target> getTargetId() 
	{
		return targetId;
	}

	public void setTargetId(Key<Target> targetId) 
	{
		this.targetId = targetId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Float getPositionX()
	{
		return positionX;
	}

	public void setPositionX(Float positionX) 
	{
		this.positionX = positionX;
	}

	public Float getPositionY() 
	{
		return positionY;
	}

	public void setPositionY(Float positionY) 
	{
		this.positionY = positionY;
	}

	public Float getPositionZ() 
	{
		return positionZ;
	}

	public void setPositionZ(Float positionZ) 
	{
		this.positionZ = positionZ;
	}

	public Long getId() 
	{
		return id;
	}
	
}
