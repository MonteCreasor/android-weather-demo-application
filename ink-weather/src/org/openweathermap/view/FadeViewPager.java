package org.openweathermap.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;

/**
 * An override of the ViewPager that fades the view into the screen when
 * an adapter is set. The pager is not visible until the animation starts.
 * @author samkirton
 */
public class FadeViewPager extends ViewPager {

	public FadeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		setVisibility(GONE);
	}

	@Override
	public void setAdapter(PagerAdapter pagerAdapter) {
		showView();
		super.setAdapter(pagerAdapter);
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
}
