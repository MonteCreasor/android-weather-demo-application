package com.ink.weather.volly.provider;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.ink.weather.WeatherApplication;

/**
 * A helper class that sets up an ImageLoader object based
 * on the provided URL. This image loader can be provided to 
 * a NetworkImageView to load a Bitmap from the specified URL
 * @author samkirton
 */
public class VolleyImageLoader {
	private String mUrl;
	private ImageLoader mImageLoader;
	
	public String getUrl() {
		return mUrl;
	}
	
	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
	
	/**
	 * Setup an ImageLoader object to be used with a NetworkImageView
	 * @param 	url	The URL where the bitmap resides
	 */
	public VolleyImageLoader(String url) {
		mUrl = url;
		mImageLoader = new ImageLoader(
			WeatherApplication.getInstance().getRequestQueue(), 
			new ImageLoader.ImageCache() {
				private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
				public void putBitmap(String url, Bitmap bitmap) {
					mCache.put(url, bitmap);
				}
				public Bitmap getBitmap(String url) {
					return mCache.get(url);
				}
			}
		);
	}
}
