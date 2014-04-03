package org.openweathermap.utils;

/**
 * Helper methods related to strings
 * @author samkirton
 */
public class StringHelper {

	/**
	 * @return	The unicode degrees symbol
	 */
	public static char buildDegreeSymbol() {
		return (char) 0x00B0;
	}
	
	/**
	 * @param	latitude	To build the string with
	 * @param	longitude	To build the string with
	 * @return	A user friendly location string based on the provided latitude and longitude
	 */
	public static String buildLocationString(double latitude, double longitude) {
		return "(" + String.valueOf(latitude) + ", " + String.valueOf(longitude) + ")";
	}
}
