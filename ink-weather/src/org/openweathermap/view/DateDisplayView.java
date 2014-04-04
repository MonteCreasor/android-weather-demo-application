package org.openweathermap.view;

import org.openweathermap.utils.DateHelper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ink.weather.R;

/**
 * Display the day, day of month and 3 letter month for the provided timestamp
 * @author samkirton
 */
public class DateDisplayView extends LinearLayout {
	private TextView uiDayOfWeekTextView;
	private TextView uiDayOfMonthTextView;
	private TextView uiMonthTextView;
	
	private int mPosition;
	
	public int getPosition() {
		return mPosition;
	}
	
	public DateDisplayView(Context context) {
		super(context);
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		layoutInflater.inflate(R.layout.view_date_display, this, true);
		
		uiDayOfWeekTextView = (TextView)findViewById(R.id.view_date_display_day_of_week);
		uiDayOfMonthTextView = (TextView)findViewById(R.id.view_date_display_day_of_month);
		uiMonthTextView = (TextView)findViewById(R.id.view_date_display_month);
	}
	
	/**
	 * Build the view based on the current timestamp
	 * @param	timestamp	Determines the date values
	 * @param	position	Saves a reference of the position in the ViewPager that the view relates to
	 */
	public void init(long timestamp, int position) {
		mPosition = position;
		String dayOfWeek = DateHelper.getDayOfWeek(timestamp);
		String dayOfMonth = DateHelper.getDayOfMonth(timestamp);
		String month = DateHelper.getMonth(timestamp);
		
		uiDayOfWeekTextView.setText(dayOfWeek);
		uiDayOfMonthTextView.setText(dayOfMonth);
		uiMonthTextView.setText(month);
	}
	
	/**
	 * Set a background highlight
	 */
	public void selected() {
		this.setBackgroundColor(getResources().getColor(R.color.view_date_display_background));
		uiDayOfWeekTextView.setTextColor(getResources().getColor(R.color.view_date_display_select_foreground));
		uiDayOfMonthTextView.setTextColor(getResources().getColor(R.color.view_date_display_select_foreground));
		uiMonthTextView.setTextColor(getResources().getColor(R.color.view_date_display_select_foreground));
	}
	
	/**
	 * Remove the background highlight
	 */
	public void unSelected() {
		this.setBackgroundColor(Color.TRANSPARENT);
		uiDayOfWeekTextView.setTextColor(getResources().getColor(R.color.view_date_display_foreground));
		uiDayOfMonthTextView.setTextColor(getResources().getColor(R.color.view_date_display_foreground));
		uiMonthTextView.setTextColor(getResources().getColor(R.color.view_date_display_foreground));
	}
}
