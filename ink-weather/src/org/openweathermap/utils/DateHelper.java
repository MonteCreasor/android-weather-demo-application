package org.openweathermap.utils;

import android.annotation.SuppressLint;
import android.text.format.Time;

/**
 * Helper methods used to convert timestamps to human readable values. The 
 * openweathermap api returns timestamps in seconds, so they are all multiplied
 * by 1000 before being handled by Java.
 * @author samkirton
 */
@SuppressLint("DefaultLocale")
public class DateHelper {

	/**
	 * @return	The day of the week e.g; Tue
	 */
	public static String getDayOfWeek(long timestamp) {
		Time time = new Time();
		time.set(timestamp*1000);
		return time.format("%a");
	}
	
	/**
	 * @return	The day of the month e.g; 31
	 */
	public static String getDayOfMonth(long timestamp) {
		Time time = new Time();
		time.set(timestamp*1000);
		return time.format("%d");
	}
	
	/**
	 * @return	The month e.g; Feb
	 */
	public static String getMonth(long timestamp) {
		Time time = new Time();
		time.set(timestamp*1000);
		return time.format("%b");
	}
}
