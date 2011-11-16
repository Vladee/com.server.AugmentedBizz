package com.server.util;

import com.google.appengine.api.datastore.Blob;

/**
 * Utility class that provides methods to convert various
 * basic types to and from Blobs.
 */
public class TypeConversion {

	/**
	 * Converts a single float number to an array of bytes.
	 * 
	 * @param floatNumber The floating point number to convert.
	 * @return Array of bytes holding the float number data.
	 */
	public static byte[] toByteArrayFrom(float floatNumber) {
	    return TypeConversion.toByteArrayFrom(Float.floatToRawIntBits(floatNumber));
	}
	
	/**
	 * Converts an array of float to an array of byte.
	 * 
	 * @param floatArray The float array to convert.
	 * @return Array of bytes holding the float array data.
	 */
	public static byte[] toByteArrayFrom(float[] floatArray) {
	    if (floatArray == null) return null;
	    // ----------
	    byte[] byts = new byte[floatArray.length * 4];
	    for (int i = 0; i < floatArray.length; i++)
	        System.arraycopy(TypeConversion.toByteArrayFrom(floatArray[i]), 0, byts, i * 4, 4);
	    return byts;
	}
	
	/**
	 * Converts single integer to an array of byte.
	 * 
	 * @param intNumber The integer to convert.
	 * @return Array of bytes holding the integer data.
	 */
	public static byte[] toByteArrayFrom(int intNumber) {
	    return new byte[] {
	        (byte)((intNumber >> 24) & 0xff),
	        (byte)((intNumber >> 16) & 0xff),
	        (byte)((intNumber >> 8) & 0xff),
	        (byte)((intNumber >> 0) & 0xff),
	    };
	}
	
	/**
	 * Converts an array of integer to an array of byte.
	 * 
	 * @param intArray The integer array to convert.
	 * @return Array of bytes holding the integer array data.
	 */
	public static byte[] toByteArrayFrom(int[] intArray) {
	    if (intArray == null) return null;
	    // ----------
	    byte[] byts = new byte[intArray.length * 4];
	    for (int i = 0; i < intArray.length; i++)
	        System.arraycopy(TypeConversion.toByteArrayFrom(intArray[i]), 0, byts, i * 4, 4);
	    return byts;
	}
	
	/**
	 * Converts single short to an array of byte.
	 * 
	 * @param shortNumber The short number to convert.
	 * @return Array of bytes holding the short number data.
	 */
	public static byte[] toByteArrayFrom(short shortNumber) {
	    return new byte[] {
	        (byte)((shortNumber >> 8) & 0xff),
	        (byte)((shortNumber >> 0) & 0xff),
	    };
	}
	
	/**
	 * Converts an array of short to an array of byte.
	 * 
	 * @param shortArray The integer array to convert.
	 * @return Array of bytes holding the short array number data.
	 */
	public static byte[] toByteArrayFrom(short[] shortArray) {
	    if (shortArray == null) return null;
	    // ----------
	    byte[] byts = new byte[shortArray.length * 2];
	    for (int i = 0; i < shortArray.length; i++)
	        System.arraycopy(TypeConversion.toByteArrayFrom(shortArray[i]), 0, byts, i * 2, 2);
	    return byts;
	}
	
	public static short toShortFrom(byte[] data) {
	    if (data == null || data.length != 2) return 0x0;
	    // ----------
	    return (short)(
	            (0xff & data[0]) << 8   |
	            (0xff & data[1]) << 0
	            );
	}
	 
	public static short[] toShortArrayFrom(byte[] data) {
	    if (data == null || data.length % 2 != 0) return null;
	    // ----------
	    short[] shts = new short[data.length / 2];
	    for (int i = 0; i < shts.length; i++) {
	        shts[i] = toShortFrom( new byte[] {
	            data[(i*2)],
	            data[(i*2)+1]
	        } );
	    }
	    return shts;
	}
	
	public static int toIntFrom(byte[] data) {
	    if (data == null || data.length != 4) return 0x0;
	    // ----------
	    return (int)( // NOTE: type cast not necessary for int
	            (0xff & data[0]) << 24  |
	            (0xff & data[1]) << 16  |
	            (0xff & data[2]) << 8   |
	            (0xff & data[3]) << 0
	            );
	}
	 
	public static int[] toIntArrayFrom(byte[] data) {
	    if (data == null || data.length % 4 != 0) return null;
	    // ----------
	    int[] ints = new int[data.length / 4];
	    for (int i = 0; i < ints.length; i++)
	        ints[i] = toIntFrom( new byte[] {
	            data[(i*4)],
	            data[(i*4)+1],
	            data[(i*4)+2],
	            data[(i*4)+3],
	        } );
	    return ints;
	}
	
	public static float toFloatFrom(byte[] data) {
	    if (data == null || data.length != 4) return 0x0;
	    // ---------- simple:
	    return Float.intBitsToFloat(toIntFrom(data));
	}
	 
	public static float[] toFloatArrayFrom(byte[] data) {
	    if (data == null || data.length % 4 != 0) return null;
	    // ----------
	    float[] flts = new float[data.length / 4];
	    for (int i = 0; i < flts.length; i++) {
	        flts[i] = toFloatFrom( new byte[] {
	            data[(i*4)],
	            data[(i*4)+1],
	            data[(i*4)+2],
	            data[(i*4)+3],
	        } );
	    }
	    return flts;
	}
}
