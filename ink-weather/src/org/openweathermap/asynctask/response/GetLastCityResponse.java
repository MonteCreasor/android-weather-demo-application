package org.openweathermap.asynctask.response;

import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.sql.model.CityModel;

/**
 * @author samkirton
 */
public class GetLastCityResponse extends BaseResponse {
	private CityModel mCityModel;
	
	public CityModel getCityModel() {
		return mCityModel;
	}
	
	public void setCityModel(CityModel newVal) {
		mCityModel = newVal;
	}
}
