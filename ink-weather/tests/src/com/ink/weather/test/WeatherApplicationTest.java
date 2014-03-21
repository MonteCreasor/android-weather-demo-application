package com.ink.weather.test;

import android.test.ApplicationTestCase;


import com.ink.weather.WeatherApplication;

/**
 * The Application singleton instance and all its public accessor
 * methods MUST not be null after the application has started
 * @author samkirton
 */
public class WeatherApplicationTest extends ApplicationTestCase<WeatherApplication>  {
	
	public WeatherApplicationTest() { 
		super(WeatherApplication.class);
	}

	public void testWeatherApplicationSingleton() {
		createApplication();
		assertNotNull(WeatherApplication.getInstance());
		assertNotNull(WeatherApplication.getInstance().getRequestQueue());
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
}
