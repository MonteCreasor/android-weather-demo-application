package org.openweathermap.view;

import java.util.ArrayList;

import org.openweathermap.dto.DayDTO;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 
 * @author samkirton
 */
public class ViewPagerIndicatorView extends LinearLayout implements OnPageChangeListener {
	private DayDTO[] mDayDTOArray;
	private Context mContext;
	private ArrayList<DateDisplayView> mDateDisplayViewCollection;
	
	public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	public void init(DayDTO[] dayDTOArray) {
		mDayDTOArray = dayDTOArray;
		mDateDisplayViewCollection = new ArrayList<DateDisplayView>();
		
		this.removeAllViews();
		
		for (int i = 0; i < mDayDTOArray.length; i++) {
			DayDTO dayDTO = mDayDTOArray[i];
			long timestamp = dayDTO.getDt();
			
			DateDisplayView dateDisplayView = new DateDisplayView(mContext);
			dateDisplayView.init(timestamp);
			this.addView(dateDisplayView);
			
			// add a reference of the view to the collection so its selection state
			// can be changed onPageSelected()
			mDateDisplayViewCollection.add(dateDisplayView);
			
			// select the first date
			if (i == 0) 
				dateDisplayView.selected();
		}
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
