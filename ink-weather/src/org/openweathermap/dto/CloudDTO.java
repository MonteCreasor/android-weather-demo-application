package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class CloudDTO implements IDTO {
	private double mAll;
	
	public double getAll() {
		return mAll;
	}
	
	public void setAll(double newVal) {
		mAll = newVal;
	}
	
	public CloudDTO() { }
	
	public CloudDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mAll = jsonObject.has("all") ? jsonObject.getDouble("all") : -1;
	}
}
