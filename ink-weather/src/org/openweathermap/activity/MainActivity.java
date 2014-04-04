package org.openweathermap.activity;

import org.openweathermap.activity.base.BaseActivity;
import org.openweathermap.adapter.ForecastAdapter;
import org.openweathermap.dto.CityDTO;
import org.openweathermap.dto.DayDTO;
import org.openweathermap.dto.ResultDTO;
import org.openweathermap.dto.base.IDTO;
import org.openweathermap.fragment.ModalDialogFragment;
import org.openweathermap.utils.RESTProvider;
import org.openweathermap.view.ViewPagerIndicatorView;
import org.openweathermap.view.ViewPagerIndicatorView.PagerPositionCallback;
import org.openweathermap.volley.callback.VolleyResponseCallback;
import org.openweathermap.volley.provider.VolleyRequest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.ink.weather.R;

/**
 * An activity that loads the current weather details from openweathermap.org 
 * when it starts. After the details are available a WeatherDataView is 
 * populated with the information. Swiping left or clicking "Select new city"
 * opens the navigation drawer.
 * @author samkirton
 */
public class MainActivity extends BaseActivity implements OnClickListener, VolleyResponseCallback, PagerPositionCallback {
	private TextView uiSelectNewCityTextView;
	private ViewPager uiViewPager;
	private ViewPagerIndicatorView uiViewPagerIndicatorView;
	private TextView uiNoResultsTextView;
	
	private CityDTO mCityDTO;
	private DayDTO[] mDayDTOArray;
	private ForecastAdapter mForecastAdapter;
	private boolean mHasNoResults;
	
	public CityDTO getCityDTO() {
		return mCityDTO;
	}
	
	public DayDTO[] getDayDTOArray() {
		return mDayDTOArray;
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.useNavigationDrawer();

        uiSelectNewCityTextView = (TextView)findViewById(R.id.activity_main_select_new_city);   
        uiViewPager = (ViewPager)findViewById(R.id.activity_main_forecast_pager_viewpager);
        uiViewPagerIndicatorView = (ViewPagerIndicatorView)findViewById(R.id.activity_main_forecast_view_pager_indicator_view);
        uiNoResultsTextView = (TextView)findViewById(R.id.activity_main_forecast_no_results);
        
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
			mCityDTO = resultDTO.getCity();
			mDayDTOArray = resultDTO.getDay();
			
			mForecastAdapter = new ForecastAdapter(getSupportFragmentManager(), resultDTO);
			uiViewPager.setAdapter(mForecastAdapter);
			
			uiViewPagerIndicatorView.init(mDayDTOArray);
			uiViewPagerIndicatorView.setPagerPositionCallback(this);
			uiViewPager.setOnPageChangeListener(uiViewPagerIndicatorView);
			
			if (mHasNoResults)
				uiNoResultsTextView.setVisibility(View.GONE);
		} else {
			showDialog(
				getResources().getString(R.string.activity_main_could_not_find_city),
				ModalDialogFragment.BUTTON_TYPE_OK
			);
		}
	}
	
	/**
	 * Open the navigation drawer
	 */
	private void selectNewCity_Click() {
		getNavigationDrawer().openDrawer(Gravity.LEFT);
	}
	
	@Override
	public void onClick(View v) {
		if (v == uiSelectNewCityTextView) {
			selectNewCity_Click();
		}
	}
	
	@Override
	public void onSetPosition(int position) {
		uiViewPager.setCurrentItem(position);
	}
	
	@Override
	public void onVolleyResponse(IDTO dto) {
		if (dto instanceof ResultDTO) {
			requestWeatherInformation_Finished((ResultDTO)dto);
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
		
		if (!mHasNoResults) {
			mHasNoResults = true;
			uiNoResultsTextView.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	protected View getContentLayout() {
		return findViewById(R.id.activity_main_content_layout);
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
