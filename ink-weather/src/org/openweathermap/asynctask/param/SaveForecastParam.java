package org.openweathermap.asynctask.param;

import org.openweathermap.asynctask.base.IParam;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.dto.DayDTO;

/**
 * @author samkirton
 */
public class SaveForecastParam implements IParam {
	private CityDTO mCityDTO;
	private DayDTO[] mDayDTOArray;
	
	public CityDTO getCityDTO() {
		return mCityDTO;
	}
	
	public void setCityDTO(CityDTO newVal) {
		mCityDTO = newVal;
	}
	
	public DayDTO[] getDayDTOArray() {
		return mDayDTOArray;
	}
	
	public void setDayDTOArray(DayDTO[] newVal) {
		mDayDTOArray = newVal;
	}
}
