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
	private String mCod;
	private String mMessage;
	private int mCount;
	private CityDTO mCity;
	private DayDTO[] mDayList;
	
	public String getCod() {
		return mCod;
	}
	
	public void setCod(String newVal) {
		mCod = newVal;
	}
	
	public String getMessage() {
		return mMessage;
	}
	
	public void setMessage(String newVal) {
		mMessage = newVal;
	}
	
	public int getCount() {
		return mCount;
	}
	
	public void setCount(int newVal) {
		mCount = newVal;
	}
	
	public CityDTO getCity() {
		return mCity;
	}
	
	public void setCity(CityDTO newVal) {
		mCity = newVal;
	}
	
	public DayDTO[] getDay() {
		return mDayList;
	}
	
	public void setDay(DayDTO[] newVal) {
		mDayList = newVal;
	}
	
	public ResultDTO() { }
	
	public ResultDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mCod = jsonObject.has("cod") ? jsonObject.getString("cod") : null;
		mMessage = jsonObject.has("message") ? jsonObject.getString("message") : null;
		mCount = jsonObject.has("cnt") ? jsonObject.getInt("cnt") : -1;
		
		if (jsonObject.has("city")) {
			JSONObject coordJSONObject = jsonObject.getJSONObject("city");
			CityDTO cityDTO = new CityDTO(coordJSONObject);
			mCity = cityDTO;
		}
		
		if (jsonObject.has("list")) {
			JSONArray jsonArrayDay = new JSONArray(jsonObject.getString("list"));
			mDayList = new DayDTO[jsonArrayDay.length()];
			for (int i = 0; i < jsonArrayDay.length(); i++) {
				DayDTO dayDTO = new DayDTO(jsonArrayDay.getJSONObject(i));
				this.mDayList[i] = dayDTO;
			}
		}
	}
}
