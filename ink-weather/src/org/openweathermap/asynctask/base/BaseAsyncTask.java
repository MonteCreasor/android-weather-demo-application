package org.openweathermap.asynctask.base;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Inherit this method to run and async tasks and return its result to 
 * the UI thread or service. An activity or service reference must be provided to
 * receive a response.
 * @author samkirton
 */
public abstract class BaseAsyncTask extends AsyncTask<IParam, Void, BaseResponse>  {
	private Context mContext;
	private AsyncCallback mAsyncCallback;
	private BaseResponse mResult;
	private BaseAsyncTask mInstanceRef;
	
	public interface AsyncCallback {
		public void onAsyncTaskFinished(BaseResponse response, BaseAsyncTask asyncTask);
	}
	
	/**
	 * Run the result of the AsyncTask on the GUI thread
	 */
	private Runnable doUpdateGUI = new Runnable() {
		public void run() { 
			mAsyncCallback.onAsyncTaskFinished(mResult, mInstanceRef);
		} 
	};
	
	public void setAsyncCallback(AsyncCallback newVal) {
		mAsyncCallback = newVal;
	}
	
	public Context getContext() {
		return mContext;
	}
	
	/**
	 * Constructor
	 * @param	applicationContext	The application context
	 */
	public BaseAsyncTask(Context context) {
		if (!(context instanceof Activity) && !(context instanceof Service)) {
			throw new IllegalArgumentException("The AsyncTask context must be able to cast into Activity or Service");
		}
		
		mContext = context;
	}
	
	/**
	 * An override of the AsyncTask execute method that takes a single
	 * BaseContext param
	 * @param	baseContext	The BaseContext to use as params
	 */
	public void runTask(IParam param) {
		IParam[] params = new IParam[1];
		params[0] = param;
		this.execute(params);
	}
	
	@Override
	protected BaseResponse doInBackground(IParam... params) {
		IParam baseParam = null;
		if (params !=  null && params.length > 0) {
			baseParam = params[0];
		}
		
		return run(baseParam);
	}
	
	@Override
	protected void onPostExecute(BaseResponse result) {
		mResult = result;
		mInstanceRef = this;
		
		if (mContext instanceof Activity) {
			((Activity)mContext).runOnUiThread(doUpdateGUI);
		} else if (mContext instanceof Service) {
			mAsyncCallback.onAsyncTaskFinished(mResult, mInstanceRef);
		}
	}
	
	@Override
	protected void onPreExecute() { }
	
	@Override
	protected void onProgressUpdate(Void... values) { }
	
	/**
	 * Run the async logic based on the context provided
	 * @param	baseParam	The param for the logic
	 * @return	Return a response
	 */
	protected abstract BaseResponse run(IParam param);
}
