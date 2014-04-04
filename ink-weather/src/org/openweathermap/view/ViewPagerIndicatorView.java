package org.openweathermap.view;

import java.util.ArrayList;

import org.openweathermap.dto.DayDTO;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

/**
 * 
 * @author samkirton
 */
public class ViewPagerIndicatorView extends LinearLayout implements OnPageChangeListener, OnClickListener {
	private DayDTO[] mDayDTOArray;
	private Context mContext;
	private ArrayList<DateDisplayView> mDateDisplayViewCollection;
	private PagerPositionCallback mPagerPositionCallback;
	
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
	 * @param	dayDTOArray	The data to populate the indicator with
	 */
	public void init(DayDTO[] dayDTOArray) {
		mDayDTOArray = dayDTOArray;
		mDateDisplayViewCollection = new ArrayList<DateDisplayView>();
		
		this.removeAllViews();
		
		for (int i = 0; i < mDayDTOArray.length; i++) {
			DayDTO dayDTO = mDayDTOArray[i];
			long timestamp = dayDTO.getDt();
			
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
}
