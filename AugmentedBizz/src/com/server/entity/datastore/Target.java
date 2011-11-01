package com.server.entity.datastore;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.server.entity.DatastoreEntity;

public class Target extends DatastoreEntity
{
	private String name;
	private Key<Model> modelId;
	
	protected Target()
	{
	}
	
	public Target(Long id, String name, Key<Model> modelKey)
	{
		super(id);
		this.name = name;
		this.modelId = modelKey;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Key<Model> getModelId() 
	{
		return modelId;
	}

	public void setModelId(Key<Model> modelId)
	{
		this.modelId = modelId;
	}

	public Long getId()
	{
		return id;
	}
	
}
