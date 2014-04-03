package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class SysDTO implements IDTO {
	private String mCountry;
	
	public String getCountry() {
		return mCountry;
	}
	
	public void setCountry(String newVal) {
		mCountry = newVal;
	}
	
	public SysDTO() { }
	
	public SysDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mCountry = jsonObject.has("country") ? jsonObject.getString("country") : null;
	}
}
