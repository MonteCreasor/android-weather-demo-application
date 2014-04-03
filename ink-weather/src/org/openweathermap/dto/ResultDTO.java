package org.openweathermap.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class ResultDTO implements IDTO {
	private String mMessage;
	private String mCod;
	private int mCount;
	private CityDTO[] mCityList;
	
	public String getMessage() {
		return mMessage;
	}
	
	public void setMessage(String newVal) {
		mMessage = newVal;
	}
	
	public String getCod() {
		return mCod;
	}
	
	public void setCod(String newVal) {
		mCod = newVal;
	}
	
	public int getCount() {
		return mCount;
	}
	
	public void setCount(int newVal) {
		mCount = newVal;
	}
	
	public CityDTO[] getCityList() {
		return mCityList;
	}
	
	public void setCityList(CityDTO[] newVal) {
		mCityList = newVal;
	}
	
	public ResultDTO() { }
	
	public ResultDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mMessage = jsonObject.has("message") ? jsonObject.getString("message") : null;
		mCod = jsonObject.has("cod") ? jsonObject.getString("cod") : null;
		mCount = jsonObject.has("count") ? jsonObject.getInt("count") : -1;
		
		if (jsonObject.has("list")) {
			JSONArray jsonArrayCities = new JSONArray(jsonObject.getString("list"));
			mCityList = new CityDTO[jsonArrayCities.length()];
			for (int i = 0; i < jsonArrayCities.length(); i++) {
				CityDTO cityDTO = new CityDTO(jsonArrayCities.getJSONObject(i));
				this.mCityList[i] = cityDTO;
			}
		}
	}
}
