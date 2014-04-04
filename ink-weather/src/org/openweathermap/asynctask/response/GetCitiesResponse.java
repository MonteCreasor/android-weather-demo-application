package org.openweathermap.asynctask.response;

import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.sql.model.CityModel;

/**
 * @author samkirton
 */
public class GetCitiesResponse extends BaseResponse {
	private CityModel[] mCityModelArray;
	
	public CityModel[] getCityModelArray() {
		return mCityModelArray;
	}
	
	public void setCityModelArray(CityModel[] cityModelArray) {
		mCityModelArray = cityModelArray;
	}
}
