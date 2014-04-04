package org.openweathermap.asynctask.base;

/**
 * The response of an AsyncTask
 * @author samkirton
 */
public abstract class BaseResponse {
	private boolean mIsValid;
	
	public boolean isValid() {
		return mIsValid;
	}
	
	public void setIsValid(boolean newVal) {
		mIsValid = newVal;
	}
}
