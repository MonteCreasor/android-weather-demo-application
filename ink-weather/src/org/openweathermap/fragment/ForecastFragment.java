package org.openweathermap.fragment;

import org.openweathermap.activity.MainActivity;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.dto.DayDTO;
import org.openweathermap.utils.RESTProvider;
import org.openweathermap.utils.StringHelper;
import org.openweathermap.view.WeatherDataRowView;
import org.openweathermap.volley.provider.VolleyImageLoader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ink.weather.R;

public class ForecastFragment extends Fragment {
	private TextView uiCityTextView;
	private TextView uiLongLatTextView;
	private TextView uiWeatherConditionMainTextView;
	private TextView uiWeatherConditionDescriptionTextView;
	private TextView uiTemperatureTextView;
	private NetworkImageView uiNetworkImageView;
	private WeatherDataRowView uiTemperatureRangeWeatherDataRowView;
	private WeatherDataRowView uiAtmosphericPressureWeatherDataRowView;
	private WeatherDataRowView uiHumidityRangeWeatherDataRowView;
	
	private int mDataPosition;
	
	public static final String ARG_DATA_POSITION = "ARG_DATA_POSITION";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forecast, container, false);
		
		uiCityTextView = (TextView)view.findViewById(R.id.fragment_forecast_city);
		uiLongLatTextView = (TextView)view.findViewById(R.id.fragment_forecast_lng_lat);
		uiWeatherConditionMainTextView = (TextView)view.findViewById(R.id.fragment_forecast_weather_conditions_main);
		uiWeatherConditionDescriptionTextView = (TextView)view.findViewById(R.id.fragment_forecast_weather_conditions_description);
		uiTemperatureTextView = (TextView)view.findViewById(R.id.fragment_forecast_temperature);
		uiNetworkImageView = (NetworkImageView)view.findViewById(R.id.fragment_forecast_weather_conditions_icon);
		uiTemperatureRangeWeatherDataRowView = (WeatherDataRowView)view.findViewById(R.id.fragment_forecast_temperature_range_row);
		uiAtmosphericPressureWeatherDataRowView = (WeatherDataRowView)view.findViewById(R.id.fragment_forecast_atmospheric_pressure_row);
		uiHumidityRangeWeatherDataRowView = (WeatherDataRowView)view.findViewById(R.id.fragment_forecast_humidity_row);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mDataPosition = getArguments().getInt(ARG_DATA_POSITION);
		
		DayDTO dayDTO = ((MainActivity)getActivity()).getDayDTOArray()[mDataPosition];
		CityDTO cityDTO = ((MainActivity)getActivity()).getCityDTO();
		init(dayDTO,cityDTO);
		requestWeatherConditionIcon(dayDTO.getWeatherList()[0].getIcon());
	}
	
	/**
	 * Populate the view with city data and fade the layout in
	 */
	public void init(DayDTO dayDTO, CityDTO cityDTO) {
		String locationName = cityDTO.getName() + " (" + cityDTO.getCountry() + ")";
		double latitude = cityDTO.getCoord().getLat();
		double longitude = cityDTO.getCoord().getLon();
		String coords = "(" + String.valueOf(latitude) + "," + String.valueOf(longitude) + ")";
		
		String mainCondition = dayDTO.getWeatherList()[0].getMain();
		String conditionDescription = "(" + dayDTO.getWeatherList()[0].getDescription() + ")";
		
		String temperature = String.valueOf(dayDTO.getTemp().getDay()) + StringHelper.buildDegreeSymbol();
		double temperatureMin = dayDTO.getTemp().getMin();
		double temperatureMax = dayDTO.getTemp().getMax();
		String temperatureRange = String.valueOf(temperatureMin) + " - " + String.valueOf(temperatureMax) + StringHelper.buildDegreeSymbol();
		String humidity = String.valueOf(dayDTO.getHumidity()) + "%";
		String atmosphericPressure = String.valueOf(dayDTO.getPressure()) + "hpa";
		
		uiCityTextView.setText(locationName);
		uiLongLatTextView.setText(coords);
		uiWeatherConditionMainTextView.setText(mainCondition);
		uiWeatherConditionDescriptionTextView.setText(conditionDescription);
		uiTemperatureTextView.setText(temperature);
		
		uiTemperatureRangeWeatherDataRowView.setTitle(getResources().getString(R.string.view_weather_data_row_temperature_range));
		uiTemperatureRangeWeatherDataRowView.setValue(temperatureRange);
		
		uiAtmosphericPressureWeatherDataRowView.setTitle(getResources().getString(R.string.view_weather_data_row_atmospheric_pressure));
		uiAtmosphericPressureWeatherDataRowView.setValue(atmosphericPressure);
		
		uiHumidityRangeWeatherDataRowView.setTitle(getResources().getString(R.string.view_weather_data_row_humidity));
		uiHumidityRangeWeatherDataRowView.setValue(humidity);
	}
	
	/**
	 * Download the weather condition icon
	 * @param	iconCode	The weather condition icon to download
	 */
	private void requestWeatherConditionIcon(String iconCode) {
		String iconUrl = RESTProvider.getWeatherConditionIcon(iconCode, getActivity());
		VolleyImageLoader volleyImageLoader = new VolleyImageLoader(iconUrl);
		
		uiNetworkImageView.setImageUrl(
			volleyImageLoader.getUrl(),
			volleyImageLoader.getImageLoader()
		);
	}
}
