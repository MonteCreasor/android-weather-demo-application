package org.openweathermap.view;

import org.openweather.R;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.utils.StringHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * A view to display weather data for a particular city,
 * based on the provided CityDTO
 * @author samkirton
 */
public class WeatherDataView extends LinearLayout {
	private TextView uiCityTextView;
	private TextView uiLongLatTextView;
	private TextView uiWeatherConditionMainTextView;
	private TextView uiWeatherConditionDescriptionTextView;
	private TextView uiTemperatureTextView;
	private NetworkImageView uiNetworkImageView;
	private WeatherDataRowView uiTemperatureRangeWeatherDataRowView;
	private WeatherDataRowView uiAtmosphericPressureWeatherDataRowView;
	private WeatherDataRowView uiHumidityRangeWeatherDataRowView;
	
	/**
	 * @return	The NetworkImageView is exposed so that the consuming
	 * activity can run a volley request to populate the view with 
	 * an image downloaded from the network
	 */
	public NetworkImageView getNetworkImageView() {
		return uiNetworkImageView;
	}
	
	public WeatherDataView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		layoutInflater.inflate(R.layout.view_weather_data, this, true);
		
		uiCityTextView = (TextView)findViewById(R.id.view_weather_data_city);
		uiLongLatTextView = (TextView)findViewById(R.id.view_weather_data_lng_lat);
		uiWeatherConditionMainTextView = (TextView)findViewById(R.id.view_weather_data_weather_conditions_main);
		uiWeatherConditionDescriptionTextView = (TextView)findViewById(R.id.view_weather_data_weather_conditions_description);
		uiTemperatureTextView = (TextView)findViewById(R.id.view_weather_data_temperature);
		uiNetworkImageView = (NetworkImageView)findViewById(R.id.view_weather_data_weather_conditions_icon);
		uiTemperatureRangeWeatherDataRowView = (WeatherDataRowView)findViewById(R.id.view_weather_data_temperature_range_row);
		uiAtmosphericPressureWeatherDataRowView = (WeatherDataRowView)findViewById(R.id.view_weather_data_atmospheric_pressure_row);
		uiHumidityRangeWeatherDataRowView = (WeatherDataRowView)findViewById(R.id.view_weather_data_humidity_row);
		
		this.setVisibility(GONE);
	}
	
	/**
	 * Populate the view with city data and fade the layout in
	 * @param cityDTO
	 */
	public void init(CityDTO cityDTO) {
		String cityName = cityDTO.getName();
		String countryName = cityDTO.getSys().getCountry();
		String locationName = cityName + " (" + countryName + ")";
		double latitude = cityDTO.getCoord().getLat();
		double longitude = cityDTO.getCoord().getLon();
		String coords = "(" + String.valueOf(latitude) + "," + String.valueOf(longitude) + ")";
		
		//TODO: The API returns a collection of weather conditions, for this example
		// the application assumes it always returns at least 1. This is dangerous assumption.
		String mainCondition = cityDTO.getWeatherList()[0].getMain();
		String conditionDescription = "(" + cityDTO.getWeatherList()[0].getDescription() + ")";
		
		String temperature = String.valueOf(cityDTO.getMain().getTemp()) + StringHelper.buildDegreeSymbol();
		double temperatureMin = cityDTO.getMain().getTempMin();
		double temperatureMax = cityDTO.getMain().getTempMax();
		String temperatureRange = String.valueOf(temperatureMin) + " - " + String.valueOf(temperatureMax) + StringHelper.buildDegreeSymbol();
		String humidity = String.valueOf(cityDTO.getMain().getHumidity()) + "%";
		String atmosphericPressure = String.valueOf(cityDTO.getMain().getPressure()) + "hpa";
		
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
		
		showView();
	}
	
	/**
	 * Fade the layout in
	 */
	public void showView() {
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(1500);
		animation.setRepeatCount(0);
		this.startAnimation(animation);
		this.setVisibility(VISIBLE);
	}
}
