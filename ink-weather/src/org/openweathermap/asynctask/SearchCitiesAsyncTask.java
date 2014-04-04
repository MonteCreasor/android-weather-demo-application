package org.openweathermap.asynctask;

import org.openweathermap.WeatherApplication;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.base.IParam;
import org.openweathermap.asynctask.param.SearchCitiesParam;
import org.openweathermap.asynctask.response.GetCitiesResponse;
import org.openweathermap.sql.model.CityModel;

import android.content.Context;

/**
 * @author samkirton
 */
public class SearchCitiesAsyncTask extends BaseAsyncTask {

	public SearchCitiesAsyncTask(Context context) { super(context); }

	@Override
	protected BaseResponse run(IParam param) {
		SearchCitiesParam searchCitiesParam = (SearchCitiesParam)param;
		
		CityModel[] cityModelArray = WeatherApplication.getInstance().getSQLProvider().selectByWhereClause(
			CityModel.class, 
			new CityModel(), 
			"name LIKE ? OR country LIKE ?",
			new String[] {searchCitiesParam.getSearchTerms() + "%", searchCitiesParam.getSearchTerms() + "%"},
			"pid DESC", 
			"0, 20"
		);
	
		GetCitiesResponse getCitiesResponse = new GetCitiesResponse();
		getCitiesResponse.setCityModelArray(cityModelArray);
		
		return getCitiesResponse;
	}
}
