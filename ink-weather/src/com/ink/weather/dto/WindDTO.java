package com.ink.weather.dto;

import org.json.JSONException;
import org.json.JSONObject;

import com.ink.weather.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class WindDTO implements IDTO {
	private double mSpeed;
	private double mGust;
	private double mDeg;
	
	public double getSpeed() {
		return mSpeed;
	}
	
	public void setSpeed(double newVal) {
		mSpeed = newVal;
	}
	
	public double getGust() {
		return mGust;
	}
	
	public void setGuest(double newVal) {
		mGust = newVal;
	}
	
	public double getDeg() {
		return mDeg;
	}
	
	public void setDeg(double newVal) {
		mDeg = newVal;
	}

	public WindDTO() { }
	
	public WindDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException { 
		mSpeed = jsonObject.has("speed") ? jsonObject.getDouble("speed") : -1;	
		mGust = jsonObject.has("gust") ? jsonObject.getDouble("gust") : -1;
		mDeg = jsonObject.has("deg") ? jsonObject.getDouble("deg") : -1;
	}
}
