package org.openweathermap.view;

import java.util.ArrayList;

import org.openweathermap.sql.model.WeatherModel;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

/**
 * 
 * @author samkirton
 */
public class ViewPagerIndicatorView extends LinearLayout implements OnPageChangeListener, OnClickListener, AnimationListener {
	private WeatherModel[] mWeatherModelArray;
	private Context mContext;
	private ArrayList<DateDisplayView> mDateDisplayViewCollection;
	private PagerPositionCallback mPagerPositionCallback;
	private boolean mFirstLoad = true;
	
	public interface PagerPositionCallback {
		public void onSetPosition(int position);
	}
	
	public void setPagerPositionCallback(PagerPositionCallback pagerPositionCallback) {
		mPagerPositionCallback = pagerPositionCallback;
	}
	
	public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setVisibility(GONE);
	}
	
	/**
	 * Populate the indicator view with a list of day data
	 * @param	weatherModelArray	The data to populate the indicator with
	 */
	public void init(WeatherModel[] weatherModelArray) {
		mWeatherModelArray = weatherModelArray;
		mDateDisplayViewCollection = new ArrayList<DateDisplayView>();
		
		this.removeAllViews();
		
		for (int i = 0; i < mWeatherModelArray.length; i++) {
			WeatherModel weatherModel = mWeatherModelArray[i];
			long timestamp = weatherModel.getTimestamp();
			
			DateDisplayView dateDisplayView = new DateDisplayView(mContext);
			dateDisplayView.init(timestamp,i);
			dateDisplayView.setOnClickListener(this);
			this.addView(dateDisplayView);
			
			// add a reference of the view to the collection so its selection state
			// can be changed onPageSelected()
			mDateDisplayViewCollection.add(dateDisplayView);
			
			// select the first date as selected
			if (i == 0) 
				dateDisplayView.selected();
		}
		
		if (mFirstLoad) {
			mFirstLoad = false;
			showView();
		} else {
			hideView();
		}
	}
	
	/**
	 * Fade the layout in
	 */
	public void showView() {
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(500);
		animation.setRepeatCount(0);
		this.startAnimation(animation);
		this.setVisibility(VISIBLE);
	}
	
	/**
	 * Hides the view and when the animation ends it shows the view
	 */
	public void hideView() {
		AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
		animation.setDuration(500);
		animation.setRepeatCount(0);
		animation.setAnimationListener(this);
		this.startAnimation(animation);
	}
	
	@Override
	public void onClick(View v) {
		DateDisplayView dateDisplayView = (DateDisplayView)v;
		int position = dateDisplayView.getPosition();
		mPagerPositionCallback.onSetPosition(position);
		onPageSelected(position);
	}

	@Override
	public void onPageSelected(int position) {
		// unselect all views
		for (DateDisplayView dateDisplayView : mDateDisplayViewCollection) 
			dateDisplayView.unSelected();
		
		// select the current selected view
		mDateDisplayViewCollection.get(position).selected();
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) { }

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) { }

	@Override
	public void onAnimationEnd(Animation animation) {
		showView();
	}

	@Override
	public void onAnimationRepeat(Animation animation) { }

	@Override
	public void onAnimationStart(Animation animation) { }
}
