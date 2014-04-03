package org.openweathermap.volley.provider;

import org.json.JSONException;
import org.json.JSONObject;
import org.openweathermap.WeatherApplication;
import org.openweathermap.dto.base.IDTO;
import org.openweathermap.utils.ReflectionHelper;
import org.openweathermap.volley.callback.VolleyResponseCallback;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * 
 * @author samkirton
 */
public class VolleyRequest {
	private VolleyResponseCallback mVolleyResponseCallback;
	
	public void setVollyResponseCallback(VolleyResponseCallback volleyResponseCallback) {
		mVolleyResponseCallback = volleyResponseCallback;
	}
	
	/**
	 * Create a new instance of a IDTO object based on the classRef provided.
	 * With the new object the fromJson() method is executed to create an 
	 * friendly object representation of the JSONObject.
	 * @param	jsonObject	The JSONObject to convert into a DTO
	 * @param	classRef	The class to create the new object instance from
	 * @return	The JSONObject as a friendly DTO 
	 */
	public IDTO buildResponseDTO(JSONObject jsonObject, Class<?> classRef) {
		IDTO responseDTO = null;
		
		if (jsonObject != null) {
			responseDTO = (IDTO)ReflectionHelper.newInstance(classRef);
			
			try {
				responseDTO.fromJson(jsonObject);
			} catch (JSONException e) { }
		}
		
		return responseDTO;
	}
	
	/**
	 * Executes a Volley request and returns the response back to the consuming activity
	 * via the VolleyResponseCallback. When a successful request occurs a new instance of the 
	 * dtoClassDef is created, this instance is then populated with the JSONObject.
	 * @param	method	Request.Method.*
	 * @param 	url	
	 * @param	dtoClassDef	
	 */
	public void execute(int method, String url, JSONObject requestJSONObject, final Class<?> dtoResponseClassDef) {	
		JsonObjectRequest request = new JsonObjectRequest(
			method, 
			url, 
			requestJSONObject, 
			new Response.Listener<JSONObject>() {
				@Override
                public void onResponse(JSONObject response) {
					mVolleyResponseCallback.onVolleyResponse(buildResponseDTO(response,dtoResponseClassDef));
                }
			}, 
			new ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mVolleyResponseCallback.onVolleyError(error);
				}
			}
		);
		
		WeatherApplication.getInstance().getRequestQueue().add(request);
	}
}
