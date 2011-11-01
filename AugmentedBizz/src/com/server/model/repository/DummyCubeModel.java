package com.server.model.repository;

import com.server.model.BaseModel;

public class DummyCubeModel extends BaseModel
{

	@Override
	public float[] getVertices() {
		return new float[] { // vertices
		    -1.00f,  -1.00f,   1.00f, // front
		     1.00f,  -1.00f,   1.00f,
		     1.00f,   1.00f,   1.00f,
		    -1.00f,   1.00f,   1.00f,

		    -1.00f,  -1.00f,  -1.00f, // back
		     1.00f,  -1.00f,  -1.00f,
		     1.00f,   1.00f,  -1.00f,
		    -1.00f,   1.00f,  -1.00f,

		    -1.00f,  -1.00f,  -1.00f, // left
		    -1.00f,  -1.00f,   1.00f,
		    -1.00f,   1.00f,   1.00f,
		    -1.00f,   1.00f,  -1.00f,

		     1.00f,  -1.00f,  -1.00f, // right
		     1.00f,  -1.00f,   1.00f,
		     1.00f,   1.00f,   1.00f,
		     1.00f,   1.00f,  -1.00f,

		    -1.00f,   1.00f,   1.00f, // top
		     1.00f,   1.00f,   1.00f,
		     1.00f,   1.00f,  -1.00f,
		    -1.00f,   1.00f,  -1.00f,

		    -1.00f,  -1.00f,   1.00f, // bottom
		     1.00f,  -1.00f,   1.00f,
		     1.00f,  -1.00f,  -1.00f,
		    -1.00f,  -1.00f,  -1.00f
		};
	}

	@Override
	public float[] getNormals() {
		return new float[] { // normals
			0, 0, 1,
		    0, 0, 1,
		    0, 0, 1,
		    0, 0, 1,

		    0, 0, -1,
		    0, 0, -1,
		    0, 0, -1,
		    0, 0, -1,

		    0, -1, 0,
		    0, -1, 0,
		    0, -1, 0,
		    0, -1, 0,

		    0, 1, 0,
		    0, 1, 0,
		    0, 1, 0,
		    0, 1, 0,

		    1, 0, 0,
		    1, 0, 0,
		    1, 0, 0,
		    1, 0, 0,

		    -1, 0, 0,
		    -1, 0, 0,
		    -1, 0, 0,
		    -1, 0, 0
		};
	}

	@Override
	public float[] getTextureCoordinates() {
		return new float[] { // texture coordinates
		    0, 0,
		    1, 0,
		    1, 1,
		    0, 1,

		    1, 0,
		    0, 0,
		    0, 1,
		    1, 1,

		    0, 0,
		    1, 0,
		    1, 1,
		    0, 1,

		    1, 0,
		    0, 0,
		    0, 1,
		    1, 1,

		    0, 0,
		    1, 0,
		    1, 1,
		    0, 1,

		    1, 0,
		    0, 0,
		    0, 1,
		    1, 1
		};
	}

	@Override
	public short[] getIndices() {
		return new short[] {
		     0,  1,  2,  0,  2,  3, // front
		     4,  6,  5,  4,  7,  6, // back
		     8,  9, 10,  8, 10, 11, // left
		    12, 14, 13, 12, 15, 14, // right
		    16, 17, 18, 16, 18, 19, // top
		    20, 22, 21, 20, 23, 22  // bottom
		};
	}
	
}
