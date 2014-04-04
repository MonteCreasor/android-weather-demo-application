package org.openweathermap.asynctask.response;

import java.util.HashMap;

import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.sql.model.CityModel;
import org.openweathermap.sql.model.WeatherModel;

/**
 * @author samkirton
 */
public class SaveForecastResponse extends BaseResponse {
	private CityModel mCityModel;
	private WeatherModel[] mWeatherModelArray;
	private HashMap<String,Integer> mIconMap;
	
	public CityModel getCityModel() {
		return mCityModel;
	}
	
	public void setCityModel(CityModel newVal) {
		mCityModel = newVal;
	}
	
	public WeatherModel[] getWeatherModelArray() {
		return mWeatherModelArray;
	}
	
	public void setWeatherModelArray(WeatherModel[] newVal) {
		mWeatherModelArray = newVal;
	}
	
	public HashMap<String,Integer> getIconMap() {
		return mIconMap;
	}
	
	public void setIconMap(HashMap<String,Integer> newVal) {
		mIconMap = newVal;
	}
}
