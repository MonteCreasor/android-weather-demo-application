package org.openweathermap.activity;

import org.openweathermap.activity.base.BaseActivity;
import org.openweathermap.adapter.ForecastAdapter;
import org.openweathermap.asynctask.GetLastCityAsyncTask;
import org.openweathermap.asynctask.SaveForecastAsyncTask;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseAsyncTask.AsyncCallback;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.param.SaveForecastParam;
import org.openweathermap.asynctask.response.GetLastCityResponse;
import org.openweathermap.asynctask.response.SaveForecastResponse;
import org.openweathermap.dto.ResultDTO;
import org.openweathermap.dto.base.IDTO;
import org.openweathermap.fragment.ModalDialogFragment;
import org.openweathermap.sql.model.CityModel;
import org.openweathermap.sql.model.WeatherModel;
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
public class MainActivity extends BaseActivity implements OnClickListener, VolleyResponseCallback, AsyncCallback, PagerPositionCallback {
	private TextView uiSelectNewCityTextView;
	private ViewPager uiViewPager;
	private ViewPagerIndicatorView uiViewPagerIndicatorView;
	private TextView uiNoResultsTextView;
	
	private CityModel mCityModel;
	private WeatherModel[] mWeatherModelArray;
	private ForecastAdapter mForecastAdapter;
	private boolean mHasNoResults;
	private GetLastCityAsyncTask mGetLastCityAsyncTask;
	private SaveForecastAsyncTask mSaveForecastAsyncTask;
	
	public CityModel getCityModel() {
		return mCityModel;
	}
	
	public WeatherModel[] getWeatherModelArray() {
		return mWeatherModelArray;
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
        
		requestWeatherInformation_Start(null);
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
		
		if (city != null) {
			requestWeatherInformationApi(city);
		} else {
			// get the last viewed city and query 
			mGetLastCityAsyncTask = new GetLastCityAsyncTask(this);
			mGetLastCityAsyncTask.setAsyncCallback(this);
			mGetLastCityAsyncTask.runTask(null);
		}
	}
	
	/**
	 * Request weather information from the API using the CityModel to 
	 * build the request
	 * @param	cityModel	The city to request weather information for
	 */
	private void requestWeatherInformationApi(String city) {
		String cityQuery = "London, GB";
		if (city != null) 
			cityQuery = city;	
		
		VolleyRequest volleyRequest = new VolleyRequest(); 
		volleyRequest.setVollyResponseCallback(this);
		volleyRequest.execute(Request.Method.GET, 
			RESTProvider.getWeatherForecastUrl(cityQuery, getBaseContext()),
			null,
			ResultDTO.class
		);
	}
	
	/**
	 * Close the loading dialog and initialise the WeatherDataView
	 * @param	resultDTO	The weather results json as an easy to use DTO
	 */
	private void requestWeatherInformation_Finished(SaveForecastResponse saveForecastResponse) {
		closeDialog();

		mCityModel = saveForecastResponse.getCityModel();
		mWeatherModelArray = saveForecastResponse.getWeatherModelArray();
		
		mForecastAdapter = new ForecastAdapter(getSupportFragmentManager(), mWeatherModelArray.length);
		uiViewPager.setAdapter(mForecastAdapter);
		
		uiViewPagerIndicatorView.init(mWeatherModelArray);
		uiViewPagerIndicatorView.setPagerPositionCallback(this);
		uiViewPager.setOnPageChangeListener(uiViewPagerIndicatorView);
		
		if (mHasNoResults)
			uiNoResultsTextView.setVisibility(View.GONE);
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
			ResultDTO resultDTO = (ResultDTO)dto;
			
			if (resultDTO.getDay() != null && resultDTO.getDay().length > 0) {
				SaveForecastParam saveForecastParam = new SaveForecastParam();
				saveForecastParam.setCityDTO(resultDTO.getCity());
				saveForecastParam.setDayDTOArray(resultDTO.getDay());
				
				mSaveForecastAsyncTask = new SaveForecastAsyncTask(this);
				mSaveForecastAsyncTask.setAsyncCallback(this);
				mSaveForecastAsyncTask.runTask(saveForecastParam);
			} else {
				showDialog(
					getResources().getString(R.string.activity_main_could_not_find_city),
					ModalDialogFragment.BUTTON_TYPE_OK
				);
			}
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
	public void onAsyncTaskFinished(BaseResponse response, BaseAsyncTask asyncTask) {
		if (asyncTask == mSaveForecastAsyncTask) {
			SaveForecastResponse saveForecastResponse = (SaveForecastResponse)response;
			requestWeatherInformation_Finished(saveForecastResponse);
		} else if (asyncTask == mGetLastCityAsyncTask) {
			GetLastCityResponse getLastCityResponse = (GetLastCityResponse)response;
			
			CityModel cityModel = getLastCityResponse.getCityModel();
			String cityQuery = null;
			if (cityModel != null) 
				cityQuery = cityModel.getName() + ", " + cityModel.getCountry();
			
			requestWeatherInformationApi(cityQuery);
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
