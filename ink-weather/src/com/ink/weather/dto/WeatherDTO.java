package com.ink.weather.dto;

import org.json.JSONException;
import org.json.JSONObject;

import com.ink.weather.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class WeatherDTO implements IDTO {
	private long mId;
	private String mMain;
	private String mDescription;
	private String mIcon;
	
	public long getId() {
		return mId;
	}
	
	public void setId(long newVal) {
		mId = newVal;
	}
	
	public String getMain() {
		return mMain;
	}
	
	public void setMain(String newVal) {
		mMain = newVal;
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public void setDescription(String newVal) {
		mDescription = newVal;
	}
	
	public String getIcon() {
		return mIcon;
	}
	
	public void setIcon(String newVal) {
		mIcon = newVal;
	}
	
	public WeatherDTO() { }
	
	public WeatherDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mId = jsonObject.has("id") ? jsonObject.getLong("id") : -1;
		mMain = jsonObject.has("main") ? jsonObject.getString("main") : null;	
		mDescription = jsonObject.has("description") ? jsonObject.getString("description") : null;	
		mIcon = jsonObject.has("icon") ? jsonObject.getString("icon") : null;	
	}
}
