package org.openweathermap.asynctask;

import org.openweathermap.WeatherApplication;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.base.IParam;
import org.openweathermap.asynctask.response.GetCitiesResponse;
import org.openweathermap.sql.model.CityModel;

import android.content.Context;

/**
 * @author samkirton
 */
public class GetCitiesAsyncTask extends BaseAsyncTask {

	public GetCitiesAsyncTask(Context context) { super(context); }

	@Override
	protected BaseResponse run(IParam param) {
		CityModel[] cityModelArray = WeatherApplication.getInstance().getSQLProvider().selectAll(
			CityModel.class, 
			new CityModel(), 
			"lastViewedTimestamp DESC", 
			"0, 20"
		);
		
		GetCitiesResponse getCitiesResponse = new GetCitiesResponse();
		getCitiesResponse.setCityModelArray(cityModelArray);
		
		return getCitiesResponse;
	}
}
