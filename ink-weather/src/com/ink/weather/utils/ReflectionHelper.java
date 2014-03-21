package com.ink.weather.utils;

/**
 * Helper methods related to reflection
 * @author samkirton
 */
public class ReflectionHelper {
	/**
	 * Create a new object instance based on the class provided
	 * @param	c	Create a new object instance based on this class
	 * @return	A new instance of the class
	 */
	public static Object newInstance(Class<?> c) {
		Object object = null;
		
		try {
			object = c.newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		return object;
	}
}

