package com.server.entity.datastore;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;
import com.server.entity.DatastoreEntity;

public class Model extends DatastoreEntity
{
	private Integer version;
	private float[] vertices;
	private float[] normals;
	private float[] texcoords;
	private float[] indices;
	private Blob textureData;
	
	public Model(Long id, Integer version, float[] vertices, float[] normals, float[] texcoords, float[] indices, Blob textureData)
	{
		super(id);
		this.version = version;
		this.vertices = vertices;
		this.normals = normals;
		this.texcoords = texcoords;
		this.indices = indices;
		this.textureData = textureData;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	public Long getId() 
	{
		return id;
	}

	public float[] getVertices() 
	{
		return vertices;
	}

	public void setVertices(float[] vertices) 
	{
		this.vertices = vertices;
	}

	public float[] getNormals()
	{
		return normals;
	}

	public void setNormals(float[] normals)
	{
		this.normals = normals;
	}

	public float[] getTexcoords()
	{
		return texcoords;
	}

	public void setTexcoords(float[] texcoords) 
	{
		this.texcoords = texcoords;
	}

	public float[] getIndices() 
	{
		return indices;
	}

	public void setIndices(float[] indices) 
	{
		this.indices = indices;
	}

	public Blob getTextureData() 
	{
		return textureData;
	}

	public void setTextureData(Blob textureData) 
	{
		this.textureData = textureData;
	}
	
	
}
