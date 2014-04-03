package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class MainDTO implements IDTO {
	private double mTemp;
	private double mHumidity;
	private double mPressure;
	private double mTempMin;
	private double mTempMax;
	
	public double getTemp() {
		return mTemp;
	}
	
	public void setTemp(double newVal) {
		mTemp = newVal;
	}
	
	public double getHumidity() {
		return mHumidity;
	}
	
	public void setHumidity(double newVal) {
		mHumidity = newVal;
	}
	
	public double getPressure() {
		return mPressure;
	}
	
	public void setPressure(double newVal) {
		mPressure = newVal;
	}
	
	public double getTempMin() {
		return mTempMin;
	}
	
	public void setTempMin(double newVal) {
		mTempMin = newVal;
	}
	
	public double getTempMax() {
		return mTempMax;
	}
	
	public void setTempMax(double newVal) {
		mTempMax = newVal;
	}
	
	public MainDTO() { }
	
	public MainDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mTemp = jsonObject.has("temp") ? jsonObject.getDouble("temp") : -1;
		mHumidity = jsonObject.has("humidity") ? jsonObject.getDouble("humidity") : -1;
		mPressure = jsonObject.has("pressure") ? jsonObject.getDouble("pressure") : -1;
		mTempMin = jsonObject.has("temp_min") ? jsonObject.getDouble("temp_min") : -1;
		mTempMax = jsonObject.has("temp_max") ? jsonObject.getDouble("temp_max") : -1;
	}
}
