package org.openweathermap.fragment;

import org.openweathermap.activity.MainActivity;
import org.openweathermap.adapter.CityAdapter;
import org.openweathermap.asynctask.GetCitiesAsyncTask;
import org.openweathermap.asynctask.SearchCitiesAsyncTask;
import org.openweathermap.asynctask.base.BaseAsyncTask;
import org.openweathermap.asynctask.base.BaseAsyncTask.AsyncCallback;
import org.openweathermap.asynctask.base.BaseResponse;
import org.openweathermap.asynctask.param.SearchCitiesParam;
import org.openweathermap.asynctask.response.GetCitiesResponse;
import org.openweathermap.sql.model.CityModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ink.weather.R;

/**
 * This class is the Fragment assigned to the MainActivity NavigationDrawer, 
 * it contains a list of popular cities and a search field that allows the user
 * to enter a custom city that has not been defined. When a city has been selected (or found),
 * the requestWeatherInformation_Start() method of MainActivity is executed and the 
 * NavigationDrawer is closed.
 * @author samkirton
 */
public class SelectCountryFragment extends Fragment implements OnItemClickListener, OnClickListener, AsyncCallback, TextWatcher {
	private EditText uiSearchEditText;
	private ListView uiPopularCityListView;
	private Button uiPopularCityNoResultsButton;
//	private ProgressBar uiPopularCityLoadingProgressBar;
	
	private GetCitiesAsyncTask mGetCitiesAsyncTask;
	private SearchCitiesAsyncTask mSearchCitiesAsyncTask;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_select_country, container, false);
		
		uiSearchEditText = (EditText)view.findViewById(R.id.fragment_select_city_search_edittext);
		uiPopularCityListView = (ListView)view.findViewById(R.id.fragment_select_country_popular_city_list);
		uiPopularCityNoResultsButton = (Button)view.findViewById(R.id.fragment_select_country_popular_city_no_results);
//		uiPopularCityLoadingProgressBar = (ProgressBar)view.findViewById(R.id.fragment_select_country_popular_city_loading);
		
		uiSearchEditText.setOnClickListener(this);
		uiSearchEditText.addTextChangedListener(this);
		uiPopularCityNoResultsButton.setOnClickListener(this);
		uiPopularCityListView.setOnItemClickListener(this);

//		setLoadingView();
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		uiSearchEditText.setText("");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mGetCitiesAsyncTask = new GetCitiesAsyncTask(getActivity());
		mGetCitiesAsyncTask.setAsyncCallback(this);
		mGetCitiesAsyncTask.runTask(null);
	}
	
	/**
	 * Sets the loading view
	 */
//	private void setLoadingView() {
//		uiPopularCityListView.setEmptyView(uiPopularCityLoadingProgressBar);
//		uiPopularCityListView.setAdapter(null);
//	}
//	
	/**
	 * Sets the no results empty view
	 */
	private void setEmptyView() {
		uiPopularCityListView.setEmptyView(uiPopularCityNoResultsButton);
	}
	
	/**
	 * Notify the activity that it should start looking for 
	 * new weather information based on the provided city argument	
	 */
	private void requestWeatherInformation(String cityQueryString) {
		MainActivity mainActivity = (MainActivity)getActivity();
		mainActivity.requestWeatherInformation_Start(cityQueryString);
	}
	
	/**
	 * Start looking for new weather information based on the search string
	 */
	private void search(String searchTerm) {
//		setLoadingView();
		
		SearchCitiesParam searchCitiesParam = new SearchCitiesParam();
		searchCitiesParam.setSearchTerms(searchTerm);
		
		mSearchCitiesAsyncTask = new SearchCitiesAsyncTask(getActivity());
		mSearchCitiesAsyncTask.setAsyncCallback(this);
		mSearchCitiesAsyncTask.runTask(searchCitiesParam);
	}
	
	/**
	 * Populate the ListView with a CityModel array
	 * @param	cityModelArray	The CityModel[] to populate the ListView with
	 */
	private void populateListView(CityModel[] cityModelArray) {
		CityAdapter cityArrayAdapter = new CityAdapter(getActivity(), cityModelArray);
		uiPopularCityListView.setAdapter(cityArrayAdapter);
		setEmptyView();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (parent == uiPopularCityListView) {
			CityModel cityModel = (CityModel)parent.getItemAtPosition(position);
			String cityQueryString = cityModel.getName() + ", " + cityModel.getCountry();
			requestWeatherInformation(cityQueryString);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == uiSearchEditText) {
			uiSearchEditText.setText("");
		} else if (v == uiPopularCityNoResultsButton) {
			requestWeatherInformation(uiSearchEditText.getText().toString());
		}
	}

	@Override
	public void onAsyncTaskFinished(BaseResponse response, BaseAsyncTask asyncTask) {
		if (asyncTask == mGetCitiesAsyncTask) {
			GetCitiesResponse getCitiesResponse = (GetCitiesResponse)response;
			populateListView(getCitiesResponse.getCityModelArray());
		} else if (asyncTask == mSearchCitiesAsyncTask) {
			GetCitiesResponse getCitiesResponse = (GetCitiesResponse)response;
			populateListView(getCitiesResponse.getCityModelArray());
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
		search(s.toString());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) { }
}
