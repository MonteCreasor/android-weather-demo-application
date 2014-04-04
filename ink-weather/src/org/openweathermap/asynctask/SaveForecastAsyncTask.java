package org.openweathermap.asynctask;

import java.util.HashMap;

import org.openweathermap.WeatherApplication;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.base.IParam;
import org.openweathermap.asynctask.param.SaveForecastParam;
import org.openweathermap.asynctask.response.SaveForecastResponse;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.dto.DayDTO;
import org.openweathermap.sql.model.CityModel;
import org.openweathermap.sql.model.IconModel;
import org.openweathermap.sql.model.WeatherModel;

import android.content.Context;

/**
 * @author samkirton
 */
public class SaveForecastAsyncTask extends BaseAsyncTask {

	public SaveForecastAsyncTask(Context context) { super(context); }

	@Override
	protected BaseResponse run(IParam param) {
		SaveForecastParam saveForecastParam = (SaveForecastParam)param;
		SaveForecastResponse saveForecastResponse = new SaveForecastResponse();
		
		CityDTO cityDTO = saveForecastParam.getCityDTO();
		DayDTO[] dayDTOArray = saveForecastParam.getDayDTOArray();
		
		WeatherModel[] weatherModelArray = null;
		long[] weatherPidArray = null;
		
		// insert icon model if the code does not already exist
		HashMap<String,Integer> iconMap = buildIconModel(dayDTOArray);
		
		// select or insert the city model if it does not exist
		CityModel cityModel = doesCityExist(cityDTO);
		long cityPid = -1;
		if (cityModel == null) {
			cityModel = buildCityModel(cityDTO);
			cityPid = WeatherApplication.getInstance().getSQLProvider().insert(cityModel);
		} else {
			cityPid = cityModel.getPid();
			cityModel.setLastViewedTimestamp(System.currentTimeMillis());
			
			// clear the forecast for this city
			WeatherApplication.getInstance().getSQLProvider().deleteBy(
				WeatherModel.class, 
				"cityId", 
				String.valueOf(cityPid)
			);
			
			//  Update the last viewed timestamp
			int testUpdate = WeatherApplication.getInstance().getSQLProvider().update(
				cityModel, 
				"pid = ?", 
				new String[] {String.valueOf(cityPid)}
			);
			
			System.out.println("test");
		}
		
		// insert weather the models
		if (cityPid > -1) { 
			weatherModelArray = buildWeatherModelArray(dayDTOArray,(int)cityPid,iconMap);
			weatherPidArray = WeatherApplication.getInstance().getSQLProvider().insertArray(weatherModelArray);
		}
		
		// were the SQL queries executed successfully?
		if (weatherPidArray != null && weatherPidArray.length == dayDTOArray.length) {
			saveForecastResponse.setCityModel(cityModel);
			saveForecastResponse.setWeatherModelArray(weatherModelArray);
			saveForecastResponse.setIconMap(iconMap);
		} else {
			saveForecastResponse.setIsValid(false);
		}
		
		return saveForecastResponse;
	}
	
	/**
	 * Check whether the city already exists in the database
	 * @param	cityDTO	Use to check whether the city exists 
	 * @return	Does the city already exist in the databse
	 */
	private CityModel doesCityExist(CityDTO cityDTO) {
		CityModel[] cityModelArray = WeatherApplication.getInstance().getSQLProvider().selectByWhereClause(
			CityModel.class, 
			new CityModel(), 
			"name = ? AND country = ?", 
			new String[] {cityDTO.getName(),cityDTO.getCountry()}, 
			null, 
			null
		);
		
		CityModel cityModel = null;
		if (cityModelArray.length > 0) {
			cityModel = cityModelArray[0];
		}
		
		return cityModel;
	}
	
	/**
	 * Populate a HashMap of code/pid pair for the icons required by the forecast
	 * @param 	dayDTOArray	The DayDTO where the icon codes reside
	 * @return	 HashMap of code/pid pair for the icons required by the forecast
	 */
	private HashMap<String,Integer> buildIconModel(DayDTO[] dayDTOArray) {
		HashMap<String,Integer> iconCodes = new HashMap<String,Integer>();
		
		for (int i = 0; i < dayDTOArray.length; i++) {
			DayDTO dayDTO = dayDTOArray[i];
			String iconCode = dayDTO.getWeatherList()[0].getIcon();
			
			IconModel[] iconModelArray = WeatherApplication.getInstance().getSQLProvider().selectByWhereClause(
				IconModel.class, 
				new IconModel(), 
				"code = ?", 
				new String[] {iconCode}, 
				null, 
				null
			);
			
			if (iconModelArray.length > 0) {
				IconModel iconModel = iconModelArray[0];
				iconCodes.put(iconModel.getCode(), iconModel.getPid());
			} else {
				IconModel iconModel = new IconModel();
				iconModel.setCode(iconCode);
				long pid = WeatherApplication.getInstance().getSQLProvider().insert(iconModel);
				iconCodes.put(iconCode, (int)pid);
			}
		}
		
		return iconCodes;
	}
	
	/**
	 * Create a CityModel object based on the provided CityDTO
	 * @param	cityDTO	The DTO to build the CityModel
	 * @return	CityModel object based on the provided CityDTO
	 */
	private CityModel buildCityModel(CityDTO cityDTO) {
		CityModel cityModel = new CityModel();
		cityModel.setName(cityDTO.getName());
		cityModel.setCountry(cityDTO.getCountry());
		cityModel.setLatitude(String.valueOf(cityDTO.getCoord().getLat()));
		cityModel.setLongitude(String.valueOf(cityDTO.getCoord().getLon()));
		cityModel.setLastViewedTimestamp(System.currentTimeMillis());
		return cityModel;
	}
	
	/**
	 * Create a WeatherModel array based on the provided DayDTO array.
	 * @param	dayDTOArray	The DTO to build the WeatherModel array
	 * @return	WeatherModel array based on the provided DayDTO array
	 */
	private WeatherModel[] buildWeatherModelArray(DayDTO[] dayDTOArray, int cityId, HashMap<String,Integer> iconMap) {
		WeatherModel[] weatherModelArray = new WeatherModel[dayDTOArray.length];
		for (int i = 0; i < weatherModelArray.length; i++) {
			DayDTO dayDTO = dayDTOArray[i];
			WeatherModel weatherModel = new WeatherModel();
			weatherModel.setIconId(iconMap.get(dayDTO.getWeatherList()[0].getIcon()));
			weatherModel.setCityId(cityId);
			weatherModel.setDescription(dayDTO.getWeatherList()[0].getMain());
			weatherModel.setDescriptionDetailed(dayDTO.getWeatherList()[0].getDescription());
			weatherModel.setTimestamp(dayDTO.getDt());
			weatherModel.setTmpDay(dayDTO.getTemp().getDay());
			weatherModel.setTmpEve(dayDTO.getTemp().getEve());
			weatherModel.setTmpNight(dayDTO.getTemp().getNight());
			weatherModel.setTmpMin(dayDTO.getTemp().getMin());
			weatherModel.setTmpMax(dayDTO.getTemp().getMax());
			weatherModel.setPressure(dayDTO.getPressure());
			weatherModel.setHumidity(dayDTO.getHumidity());
			weatherModel.setWindSpeed(dayDTO.getSpeed());
			weatherModel.setWindDegrees(dayDTO.getDeg());
			weatherModel.setCloudCoverage(dayDTO.getClouds());
			weatherModelArray[i] = weatherModel;
		}
		
		return weatherModelArray;
	}
}