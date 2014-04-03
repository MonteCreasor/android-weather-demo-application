package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class ThreeHourDTO implements IDTO {
	private int mThreeHour;
	
	public int getThreeHour() {
		return mThreeHour;
	}
	
	public void setThreeHour(int newVal) {
		mThreeHour = newVal;
	}
	
	public ThreeHourDTO() { }
	
	public ThreeHourDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mThreeHour = jsonObject.has("3h") ? jsonObject.getInt("3h") : -1;
	}
}
