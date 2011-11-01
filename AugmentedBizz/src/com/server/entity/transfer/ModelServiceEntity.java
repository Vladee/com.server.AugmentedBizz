package com.server.entity.transfer;

import com.google.appengine.api.datastore.Blob;
import com.server.entity.ServiceTransferEntity;

/**
 * Service entity for model information delivery
 * 
 * @author Vladi
 *
 */
public class ModelServiceEntity extends ServiceTransferEntity {
	
	private float[] vertices;
	private float[] normals;
	private float[] texCoords;
	private short[] indices;//optional
	private String base64EncodedTexture;
	
	public ModelServiceEntity(float[] vertices, float[] normals, float[] texCoords, short[] indices, String base64Texture)
	{
		this.vertices = vertices;
		this.normals = normals;
		this.texCoords = texCoords;
		this.indices = indices;
		this.base64EncodedTexture = base64Texture;
	}

	public float[] getVertices() 
	{
		return vertices;
	}

	public float[] getNormals()
	{
		return normals;
	}

	public float[] getTexCoords()
	{
		return texCoords;
	}

	public short[] getIndices()
	{
		return indices;
	}

	public String getBase64EncodedTexture() 
	{
		return base64EncodedTexture;
	}
	
	
}
