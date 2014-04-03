package org.openweathermap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

/**
 * Helper methods for managing resources
 * @author samkirton
 */
public class ResourceHelper {
	
	/**
	 * Retrieves a text file resource from /raw and reads it into memory as a String
	 * @param	context	The application context
	 * @param	resId	The resourceId of the file being read into memory
	 * @return	The string contents of the file
	 */
	public static String getFileContentsAsString(Context context, int resId) {
		String fileContents = null;
		
		InputStream inputStream = context.getResources().openRawResource(resId);
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputReader);
		
		StringBuilder stringBuilder = new StringBuilder();
		
	    try {
	    	String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	    } catch (IOException e) { 
	    	
	    } finally {
	    	closeInputStream(inputStream);
	    	closeInputStreamReader(inputReader);
	    	closeBufferedReader(bufferedReader);
	    }
		
	    fileContents = stringBuilder.toString();
		return fileContents;
	}
	
	/**
	 * Close the inputStream associated with the provided reference
	 * @param	inputStream	The reference of the inputStream to close
	 */
	private static void closeInputStream(InputStream inputStream) {
		try {
			inputStream.close();
		} catch (IOException e) { }
	}
	
	/**
	 * Close the inputStreamReader associated with the provided reference
	 * @param	inputStreamReader	The reference of the inputStreamReader to close
	 */
	private static void closeInputStreamReader(InputStreamReader inputStreamReader) {
		try {
			inputStreamReader.close();
		} catch (IOException e) { }
	}
	
	/**
	 * Close the bufferedReader associated with the provided reference
	 * @param	bufferedReader	The reference of the bufferedReader to close
	 */
	private static void closeBufferedReader(BufferedReader bufferedReader) {
		try {
			bufferedReader.close();
		} catch (IOException e) { }
	}
}
