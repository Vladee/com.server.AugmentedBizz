package com.server.model;

/**
 * Abstract base model data
 * @author Vladi
 *
 */
public abstract class BaseModel {
	
	protected BaseModel()
	{
	}

	public abstract float[] getVertices();

	public abstract float[] getNormals();

	public abstract float[] getTextureCoordinates();

	public abstract short[] getIndices();
	
	
}
