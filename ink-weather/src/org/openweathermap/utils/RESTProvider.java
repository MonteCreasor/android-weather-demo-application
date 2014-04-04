package org.openweathermap.utils;

import android.content.Context;

import com.ink.weather.R;

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
	 * @return	The retrieve weather forecast REST end point
	 */
	public static String getWeatherForecastUrl(String city,Context context) {
		return context.getResources().getString(R.string.rest_weather_forecast, city);
	}
	
	/**
	 * @return	The retrieve weather conditions icon REST end point
	 */
	public static String getWeatherConditionIcon(String iconCode, Context context) {
		return context.getResources().getString(R.string.rest_weather_condition_icon, iconCode);
	}
}
