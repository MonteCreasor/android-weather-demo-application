package org.openweathermap.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * An override of the ViewPager that fades the view into the screen when
 * an adapter is set. The pager is not visible until the animation starts.
 * @author samkirton
 */
public class ViewPagerFade extends ViewPager implements AnimationListener {
	private PagerAdapter mPagerAdapter;
	private boolean mFirstLoad = true;
	
	public ViewPagerFade(Context context, AttributeSet attrs) {
		super(context, attrs);
		setVisibility(GONE);
	}

	@Override
	public void setAdapter(PagerAdapter pagerAdapter) {
		mPagerAdapter = pagerAdapter;
		// When the  view is first loaded it just needs to be faded in. All subsequent
		// loads it should fade out and then back in
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
		super.setAdapter(mPagerAdapter);
		
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
	public void onAnimationEnd(Animation animation) {
		showView();
	}

	@Override
	public void onAnimationRepeat(Animation animation) { }

	@Override
	public void onAnimationStart(Animation animation) { }
}
