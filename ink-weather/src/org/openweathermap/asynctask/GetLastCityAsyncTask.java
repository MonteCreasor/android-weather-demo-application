package org.openweathermap.asynctask;

import org.openweathermap.WeatherApplication;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.base.IParam;
import org.openweathermap.asynctask.response.GetLastCityResponse;
import org.openweathermap.sql.model.CityModel;

import android.content.Context;

/**
 * @author samkirton
 */
public class GetLastCityAsyncTask extends BaseAsyncTask {

	public GetLastCityAsyncTask(Context context) { super(context); }

	@Override
	protected BaseResponse run(IParam param) {
		GetLastCityResponse getLastCityResponse = new GetLastCityResponse();
		
		CityModel[] cityModel = WeatherApplication.getInstance().getSQLProvider().selectByWhereClause(
			CityModel.class, 
			new CityModel(), 
			null, 
			null,
			"lastViewedTimestamp DESC", 
			"0,1"
		);
		
		if (cityModel != null && cityModel.length > 0) 
			getLastCityResponse.setCityModel(cityModel[0]);
		
		return getLastCityResponse;
	}
}
