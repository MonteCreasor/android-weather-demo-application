package org.openweathermap.activity.base;

import org.openweathermap.fragment.ModalDialogFragment;

import android.R.color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.ink.weather.R;

/**
 * Inheriting from BaseActivity provides an Activity with a NavigationDrawer 
 * implementation. super.useNavigationDrawer() must be called during onCreate() 
 * to setup the NavigationDrawer. Helper methods for opening and closing the
 * DialogActivity are exposed to remove the dialog management responsibility from Activity
 * @author samkirton
 */
public abstract class BaseActivity extends FragmentActivity {
	private ModalDialogFragment uiModalDialogFragment;
	
	private ActionBarDrawerToggle mDrawerToggle;
	private float mLastTranslate;
	private boolean mDialogOpen;
	
	/**
	 * Setup the navigation drawer and hook the onNavigationDrawerClosed 
	 * abstract method to the onDraweClosed callback, this allows the activities
	 * that use BaseActivity to be notified when the navigation drawer closes.
	 */
    protected void useNavigationDrawer() {
		final DrawerLayout uiDrawerLayout = getNavigationDrawer();
		
		if (uiDrawerLayout != null) {
			uiDrawerLayout.setScrimColor(color.transparent);
			mDrawerToggle = new ActionBarDrawerToggle(
					this, 
					uiDrawerLayout, 
					R.drawable.ic_launcher, 
					R.string.app_name, 
					R.string.app_name) {
				
				public void onDrawerClosed(View drawerView) { }
				
				public void onDrawerOpened(View drawerView) { }
				
				public void onDrawerSlide(View drawerView, float slideOffset) {
					slideActivityContent(drawerView,slideOffset);
				}
			};
			
			uiDrawerLayout.setDrawerListener(mDrawerToggle);
		}
	}
    
	/**
	 * Adjust the activity content when the navigation drawer opens. The native
	 * implementation of the navigation drawer will cover the activity content 
	 * when it is opened
	 */
	private void slideActivityContent(View drawerView, float slideOffset) {
		float moveFactor = (getNavigationContentLayout().getWidth() * slideOffset);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getContentLayout().setTranslationX(moveFactor);
		} else {
			TranslateAnimation translateAnimation = new TranslateAnimation(mLastTranslate, moveFactor, 0.0f, 0.0f);
			translateAnimation.setDuration(0);
			translateAnimation.setFillAfter(true);
			getContentLayout().startAnimation(translateAnimation);
			
			mLastTranslate = moveFactor;
		}
	}
	
	/**
	 * Close the loading dialog
	 */
	protected void closeDialog() {
		if (uiModalDialogFragment != null) {
			mDialogOpen = false;
			uiModalDialogFragment.dismiss();
		}
	}
	
	/**
	 * Open the loading dialog and display the loadingText
	 */
	protected void showDialog(String dialogText, int buttonType) {
		// Close the existing dialog before opening another one
		if (mDialogOpen) 
			closeDialog();
		
		// Open the fragment dialog with the provided extras
		Bundle args = new Bundle();
		args.putString(ModalDialogFragment.EXTRA_DIALOG_TEXT, dialogText);
		args.putInt(ModalDialogFragment.EXTRA_BUTTON_TYPE, buttonType);

		mDialogOpen = true;
		uiModalDialogFragment = new ModalDialogFragment();
		uiModalDialogFragment.setArguments(args);
		uiModalDialogFragment.show(getSupportFragmentManager(), "dialog");
	}
	
	/**
	 * @return The content of the activity that should be moved when the navigation
	 * drawer is opened
	 */
	protected abstract View getContentLayout();
	
	/**
	 * @return The navigation drawer fragment
	 */
	protected abstract FrameLayout getNavigationContentLayout();
	
	/**
	 * @return A reference to the DrawerLayout ui component
	 */
	protected abstract DrawerLayout getNavigationDrawer();
}
