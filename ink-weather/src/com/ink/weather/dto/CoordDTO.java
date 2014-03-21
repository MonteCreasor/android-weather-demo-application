package com.ink.weather.dto;

import org.json.JSONException;
import org.json.JSONObject;

import com.ink.weather.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class CoordDTO implements IDTO {
	private double mLon;
	private double mLat;
	
	public double getLon() {
		return mLon;
	}
	
	public void setLon(double newVal) {
		mLon = newVal;
	}
	
	public double getLat() {
		return mLat;
	}
	
	public void setLat(double newVal) {
		mLat = newVal;
	}
	
	public CoordDTO() { }
	
	public CoordDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mLon = jsonObject.has("lon") ? jsonObject.getDouble("lon") : -1;
		mLat = jsonObject.has("lat") ? jsonObject.getDouble("lat") : -1;
	}
}
