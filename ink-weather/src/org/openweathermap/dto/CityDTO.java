package org.openweathermap.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class CityDTO implements IDTO {
	private long mId;
	private String mName;
	private String mCountry;
	private CoordDTO mCoord;
	
	public long getId() {
		return mId;
	}
	
	public void setId(long newVal) {
		mId = newVal;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String newVal) {
		mName = newVal;
	}
	
	public String getCountry() {
		return mCountry;
	}
	
	public void setCountry(String newVal) {
		mCountry = newVal;
	}
	
	public CoordDTO getCoord() {
		return mCoord;
	}
	
	public void setCoord(CoordDTO newVal) {
		mCoord = newVal;
	}
	
	public CityDTO() { }
	
	public CityDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mId = jsonObject.has("id") ? jsonObject.getLong("id") : -1;
		mName = jsonObject.has("name") ? jsonObject.getString("name") : null;
		mCountry = jsonObject.has("country") ? jsonObject.getString("country") : null;
		
		if (jsonObject.has("coord")) {
			JSONObject coordJSONObject = jsonObject.getJSONObject("coord");
			CoordDTO coordDTO = new CoordDTO(coordJSONObject);
			mCoord = coordDTO;
		}
	}
}
