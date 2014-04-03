package org.openweathermap.utils;

import org.openweather.R;

import android.content.Context;

/**
 * Builds web service URLs based on the arguments provided
 * @author samkirton
 */
public class RESTProvider {

	/**
	 * @return	The retrieve weather REST end point
	 */
	public static String getWeatherUrl(String city,Context context) {
		return context.getResources().getString(R.string.rest_weather, city);
	}
	
	/**
	 * @return	The retrieve weather conditions icon REST end point
	 */
	public static String getWeatherConditionIcon(String iconCode, Context context) {
		return context.getResources().getString(R.string.rest_weather_condition_icon, iconCode);
	}
}
