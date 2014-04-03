package org.openweathermap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * The global application state as a singleton
 * @author samkirton
 */
public class WeatherApplication extends Application {
	private static WeatherApplication mInstance;
	private RequestQueue mRequestQueue;
	
	public static WeatherApplication getInstance() {
		return mInstance;
	}
	
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
	@Override
	public final void onCreate() {
		super.onCreate();
		mInstance = this;
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
	}
}
