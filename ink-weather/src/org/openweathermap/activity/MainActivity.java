package org.openweathermap.activity;

import org.openweather.R;
import org.openweathermap.activity.base.BaseActivity;
import org.openweathermap.dto.ResultDTO;
import org.openweathermap.dto.base.IDTO;
import org.openweathermap.fragment.ModalDialogFragment;
import org.openweathermap.utils.RESTProvider;
import org.openweathermap.view.WeatherDataView;
import org.openweathermap.volley.callback.VolleyResponseCallback;
import org.openweathermap.volley.provider.VolleyImageLoader;
import org.openweathermap.volley.provider.VolleyRequest;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;

/**
 * An activity that loads the current weather details from openweathermap.org 
 * when it starts. After the details are available a WeatherDataView is 
 * populated with the information. Swiping left or clicking "Select new city"
 * opens the navigation drawer.
 * @author samkirton
 */
public class MainActivity extends BaseActivity implements OnClickListener, VolleyResponseCallback {
	private WeatherDataView uiWeatherDataView;
	private NetworkImageView uiNetworkImageView;
	private TextView uiSelectNewCityTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.useNavigationDrawer();

        uiWeatherDataView = (WeatherDataView)findViewById(R.id.activity_main_weather_data_view);
        uiNetworkImageView = uiWeatherDataView.getNetworkImageView();
        uiSelectNewCityTextView = (TextView)findViewById(R.id.activity_main_select_new_city);
        
        uiSelectNewCityTextView.setOnClickListener(this);
        
		String startingCity = "London, GB";
		requestWeatherInformation_Start(startingCity);
    }

	/**
	 * Request weather information for the starting city
	 */
	public void requestWeatherInformation_Start(String city) {		
		// close the navigation drawer if it is open
		if (getNavigationDrawer().isDrawerOpen(Gravity.LEFT)) {
			getNavigationDrawer().closeDrawer(Gravity.LEFT);
		}
		
		showDialog(getResources().getString(R.string.activity_loading_weather),ModalDialogFragment.BUTTON_TYPE_BLOCKING);
		
		VolleyRequest volleyRequest = new VolleyRequest(); 
		volleyRequest.setVollyResponseCallback(this);
		volleyRequest.execute(Request.Method.GET, 
			RESTProvider.getWeatherForecastUrl(city, getBaseContext()),
			null,
			ResultDTO.class
		);
	}
	
	/**
	 * Close the loading dialog and initialise the WeatherDataView
	 * @param	resultDTO	The weather results json as an easy to use DTO
	 */
	private void requestWeatherInformation_Finished(ResultDTO resultDTO) {
		closeDialog();
		
		if (resultDTO.getDay() != null && resultDTO.getDay().length > 0) {
			uiWeatherDataView.init(resultDTO.getDay()[0], resultDTO.getCity());
			requestWeatherConditionIcon(resultDTO.getDay()[0].getWeatherList()[0].getIcon());
		} else {
			showDialog(
				getResources().getString(R.string.activity_main_could_not_find_city),
				ModalDialogFragment.BUTTON_TYPE_OK
			);
		}
	}
	
	/**
	 * Download the weather condition icon
	 */
	private void requestWeatherConditionIcon(String iconCode) {
		String iconUrl = RESTProvider.getWeatherConditionIcon(iconCode, getBaseContext());
		VolleyImageLoader volleyImageLoader = new VolleyImageLoader(iconUrl);
		
		uiNetworkImageView.setImageUrl(
			volleyImageLoader.getUrl(),
			volleyImageLoader.getImageLoader()
		);
	}
	
	/**
	 * Open the navigation drawer
	 */
	private void selectNewCity_Click() {
		getNavigationDrawer().openDrawer(Gravity.LEFT);
	}
	
	@Override
	public void onVolleyResponse(IDTO dto) {
		if (dto instanceof ResultDTO) {
			ResultDTO resultDTO = (ResultDTO)dto;
			requestWeatherInformation_Finished(resultDTO);
		}
	}

	@Override
	public void onVolleyError(VolleyError error) {
		if (error.networkResponse == null) {
			showDialog(
				getResources().getString(R.string.activity_main_could_not_connect),
				ModalDialogFragment.BUTTON_TYPE_OK
			);
		} else {
			showDialog(
				getResources().getString(R.string.activity_main_could_not_find_city),
				ModalDialogFragment.BUTTON_TYPE_OK
			);
		}
	}
	
	@Override
	public void onClick(View v) {
		if (v == uiSelectNewCityTextView) {
			selectNewCity_Click();
		}
	}
	
	@Override
	protected View getContentLayout() {
		return findViewById(R.id.main_activity_content_layout);
	}
		
	@Override
	protected FrameLayout getNavigationContentLayout() {
		return (FrameLayout)findViewById(R.id.activity_main_navigation_fragment);
	}
	
	@Override
	public DrawerLayout getNavigationDrawer() {
		return (DrawerLayout)findViewById(R.id.main_activity_navigation_drawer);
	}
}
