package org.openweathermap.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ink.weather.R;

/**
 * A view to display weather data rows, the view can
 * contain a title and a value. It will display the title aligned
 * to the left and the value aligned to the right
 * @author samkirton
 */
public class WeatherDataRowView extends LinearLayout {
	private TextView uiTitleTextView;
	private TextView uiValueTextView;
	
	public WeatherDataRowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		layoutInflater.inflate(R.layout.view_weather_data_row, this, true);
		uiTitleTextView = (TextView)findViewById(R.id.view_weather_data_row_title);
		uiValueTextView = (TextView)findViewById(R.id.view_weather_data_row_value);
	}
	
	public void setTitle(String title) {
		uiTitleTextView.setText(title);
	}
	
	public void setValue(String value) {
		uiValueTextView.setText(value);
	}
}
