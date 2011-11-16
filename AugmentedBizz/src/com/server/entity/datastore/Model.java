package com.server.entity.datastore;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;
import com.server.entity.DatastoreEntity;

public class Model extends DatastoreEntity
{
	private Integer version;
	private Blob vertices;//saves float[]
	private Blob normals;//saves float[]
	private Blob texcoords;//saves float[]
	private Blob indices;//saves short[]
	private Blob textureData;
	
	private Model()
	{
	}
	
	public Model(Long id, Integer version, Blob vertices, Blob normals, Blob texcoords, Blob indices, Blob textureData)
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

	public Blob getVertices() 
	{
		return vertices;
	}

	public void setVertices(Blob vertices) 
	{
		this.vertices = vertices;
	}

	public Blob getNormals()
	{
		return normals;
	}

	public void setNormals(Blob normals)
	{
		this.normals = normals;
	}

	public Blob getTexcoords()
	{
		return texcoords;
	}

	public void setTexcoords(Blob texcoords) 
	{
		this.texcoords = texcoords;
	}

	public Blob getIndices() 
	{
		return indices;
	}

	public void setIndices(Blob indices) 
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
