package org.openweathermap;

import org.openweathermap.sql.SQLInitProvider;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.sqlite.SQLProvider;

/**
 * The global application state as a singleton
 * @author samkirton
 */
public class WeatherApplication extends Application {
	private static WeatherApplication mInstance;
	private RequestQueue mRequestQueue;
	private SQLProvider mSQLProvider;
	
	public static WeatherApplication getInstance() {
		return mInstance;
	}
	
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
	public SQLProvider getSQLProvider() {
		return mSQLProvider;
	}
	
	@Override
	public final void onCreate() {
		super.onCreate();
		mInstance = this;
		
		SQLInitProvider sqlInitProvider = new SQLInitProvider(getBaseContext());
		mSQLProvider = new SQLProvider(sqlInitProvider.getWritableDatabase());
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
	}
}
