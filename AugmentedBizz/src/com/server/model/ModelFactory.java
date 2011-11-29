package com.server.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.obj2openjl.v2.Obj2OpenJL;
import org.obj2openjl.v2.model.OpenGLModelData;
import org.obj2openjl.v2.model.RawOpenGLModel;

import com.google.appengine.api.datastore.Blob;
import com.server.entity.datastore.Model;
import com.server.util.TypeConversion;

public class ModelFactory {
	
	private static Obj2OpenJL obj2openJl = new Obj2OpenJL();
	
	public static Model createDatastoreModelEntity(Long modelId, Integer version, InputStream modelObjectStream, InputStream textureStream) throws Exception
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
		
		OpenGLModelData baseModel = obj2openJl.convert(modelObjectStream).normalize().center().getDataForGLDrawElements();
		
		return new Model(modelId, 
						 version, 
						 new Blob(TypeConversion.toByteArrayFrom(baseModel.getVertices())), 
						 new Blob(TypeConversion.toByteArrayFrom(baseModel.getNormals())), 
						 new Blob(TypeConversion.toByteArrayFrom(baseModel.getTextureCoordinates())), 
						 new Blob(TypeConversion.toByteArrayFrom(baseModel.getIndices())), 
						 new Blob(bufferStream.toByteArray()));
	};
}
