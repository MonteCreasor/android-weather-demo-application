package org.openweathermap.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class DayDTO implements IDTO {
	private long mDt;
	private double mPressure;
	private double mHumidity;
	private double mSpeed;
	private double mDeg;
	private double mClouds;
	private TempDTO mTemp;
	private WeatherDTO[] mWeather;
	
	public long getDt() {
		return mDt;
	}
	
	public void setDt(long newVal) {
		mDt = newVal;
	}
	
	public double getPressure() {
		return mPressure;
	}
	
	public void setPressure(double newVal) {
		mPressure = newVal;
	}
	
	public double getHumidity() {
		return mHumidity;
	}
	
	public void setHumidity(double newVal) {
		mHumidity = newVal;
	}
	
	public double getSpeed() {
		return mSpeed;
	}
	
	public void setSpeed(double newVal) {
		mSpeed = newVal;
	}
	
	public double getDeg() {
		return mDeg;
	}
	
	public void setDeg(double newVal) {
		mDeg = newVal;
	}
	
	public double getClouds() {
		return mClouds;
	}
	
	public void setClouds(double newVal) {
		mClouds = newVal;
	}
	
	public TempDTO getTemp() {
		return mTemp;
	}
	
	public void setTemp(TempDTO newVal) {
		mTemp = newVal;
	}
	
	public WeatherDTO[] getWeatherList() {
		return mWeather;
	}
	
	public void setWeatherList(WeatherDTO[] newVal) {
		mWeather = newVal;
	}
	
	public DayDTO() { }
	
	public DayDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}

	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mDt = jsonObject.has("dt") ? jsonObject.getLong("dt") : -1;
		mPressure = jsonObject.has("pressure") ? jsonObject.getDouble("pressure") : -1;
		mHumidity = jsonObject.has("humidity") ? jsonObject.getDouble("humidity") : -1;
		mSpeed = jsonObject.has("speed") ? jsonObject.getDouble("speed") : -1;
		mDeg = jsonObject.has("deg") ? jsonObject.getDouble("deg") : -1;
		mClouds = jsonObject.has("clouds") ? jsonObject.getDouble("clouds") : -1;
		
		
		if (jsonObject.has("temp")) {
			JSONObject tempJSONObject = jsonObject.getJSONObject("temp");
			TempDTO tempDTO = new TempDTO(tempJSONObject);
			mTemp = tempDTO;
		}
		
		if (jsonObject.has("weather")) {
			JSONArray jsonArrayWeather = new JSONArray(jsonObject.getString("weather"));
			mWeather = new WeatherDTO[jsonArrayWeather.length()];
			for (int i = 0; i < jsonArrayWeather.length(); i++) {
				WeatherDTO weatherDTO = new WeatherDTO(jsonArrayWeather.getJSONObject(i));
				this.mWeather[i] = weatherDTO;
			}
		}
	}
}
