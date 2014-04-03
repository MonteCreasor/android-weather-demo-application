package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class TempDTO implements IDTO {
	private double mDay;
	private double mMin;
	private double mMax;
	private double mNight;
	private double mEve;
	private double mMorn;
	
	public double getDay() {
		return mDay;
	}
	
	public void setDay(double newVal) {
		mDay = newVal;
	}
	
	public double getMin() {
		return mMin;
	}
	
	public void setMin(double newVal) {
		mMin = newVal;
	}
	
	public double getMax() {
		return mMax;
	}
	
	public void setMax(double newVal) {
		mMax = newVal;
	}
	
	public double getNight() {
		return mNight;
	}
	
	public void setNight(double newVal) {
		mNight = newVal;
	}
	
	public double getEve() {
		return mEve;
	}
	
	public void setEve(double newVal) {
		mEve = newVal;
	}
	
	public double getMorn() {
		return mMorn;
	}
	
	public void setMorn(double newVal) {
		mMorn = newVal;
	}
	
	public TempDTO() { }
	
	public TempDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mDay = jsonObject.has("day") ? jsonObject.getDouble("day") : -1;
		mMin = jsonObject.has("min") ? jsonObject.getDouble("min") : -1;
		mMax = jsonObject.has("max") ? jsonObject.getDouble("max") : -1;
		mNight = jsonObject.has("night") ? jsonObject.getDouble("night") : -1;
		mEve = jsonObject.has("eve") ? jsonObject.getDouble("eve") : -1;
		mMorn = jsonObject.has("morn") ? jsonObject.getDouble("morn") : -1;
	}
}
