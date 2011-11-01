package com.server.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.appengine.api.datastore.Blob;
import com.server.entity.datastore.Model;

public class ModelFactory {
	
	public static Model createDatastoreModelEntity(Long modelId, Integer version, BaseModel baseModel, InputStream textureStream) throws Exception
	{
		//write the texture data into a buffer
		ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
		
		int numReadBytes;
		byte[] data = new byte[1024 * 4];//read 4 kB chunks of the texture

		while((numReadBytes = textureStream.read(data, 0, data.length)) != -1)
		{
			bufferStream.write(data, 0, numReadBytes);
		}
		
		textureStream.close();
		bufferStream.flush();
		
		return new Model(modelId, version, baseModel.getVertices(), baseModel.getNormals(), baseModel.getTextureCoordinates(), baseModel.getIndices(), new Blob(bufferStream.toByteArray()));
	};
	
}
