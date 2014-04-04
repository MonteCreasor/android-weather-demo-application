package org.openweathermap.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ink.weather.R;

/**
 * A view to represent a city row that is displayed in the "Popular Cities"
 * section of the NavigationDrawer
 * @author samkirton
 */
public class CityListItemView extends LinearLayout {
    private TextView uiTitleTextView;
    private TextView uiLocationTextView;
	
	public CityListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		layoutInflater.inflate(R.layout.view_city_list_item, this, true);
		
	    uiTitleTextView = (TextView)findViewById(R.id.view_city_list_title);
	    uiLocationTextView = (TextView)findViewById(R.id.view_city_list_location);
	}
	
	public void setTitle(String newVal) {
		uiTitleTextView.setText(newVal);
	}
	
	public void setLocation(String newVal) {
		uiLocationTextView.setText(newVal);
	}
}
