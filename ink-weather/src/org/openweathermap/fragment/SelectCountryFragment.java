package org.openweathermap.fragment;

import org.openweathermap.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ink.weather.R;

/**
 * This class is the Fragment assigned to the MainActivity NavigationDrawer, 
 * it contains a list of popular cities and a search field that allows the user
 * to enter a custom city that has not been defined. When a city has been selected (or found),
 * the requestWeatherInformation_Start() method of MainActivity is executed and the 
 * NavigationDrawer is closed.
 * @author samkirton
 */
public class SelectCountryFragment extends Fragment implements OnItemClickListener, OnClickListener {
	private EditText uiSearchEditText;
	private Button uiSearchButton;
//	private ListView uiPopularCityListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_select_country, container, false);
		
		uiSearchEditText = (EditText)view.findViewById(R.id.fragment_select_city_search_edittext);
		uiSearchButton = (Button)view.findViewById(R.id.fragment_select_city_search_button);
//		uiPopularCityListView = (ListView)view.findViewById(R.id.fragment_select_country_popular_city_list);
		
//		CityArrayAdapter cityArrayAdapter = new CityArrayAdapter(getActivity(), MockHelper.buildMockCityArray());
//		uiPopularCityListView.setAdapter(cityArrayAdapter);
		
		uiSearchEditText.setOnClickListener(this);
		uiSearchButton.setOnClickListener(this);
//		uiPopularCityListView.setOnItemClickListener(this);

		return view;
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
	private void search_Click() {
		String cityQueryString = uiSearchEditText.getText().toString();
		requestWeatherInformation(cityQueryString);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		if (parent == uiPopularCityListView) {
//			CityDTO cityDTO = (CityDTO)parent.getItemAtPosition(position);
//			String cityQueryString = cityDTO.getName() + ", " + cityDTO.getSys().getCountry();
//			requestWeatherInformation(cityQueryString);
//		}
	}

	@Override
	public void onClick(View v) {
		if (v == uiSearchEditText) {
			uiSearchEditText.setText("");
		} else if (v == uiSearchButton) {
			search_Click();
		}
	}
}
