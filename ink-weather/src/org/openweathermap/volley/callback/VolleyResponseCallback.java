package org.openweathermap.volley.callback;

import org.openweathermap.dto.base.IDTO;

import com.android.volley.VolleyError;

/**
 * The response of a VolleyRequest, any Activity that runs a request
 * must implement this interface to receive a response from Volly.
 * @author samkirton
 */
public interface VolleyResponseCallback {
	public void onVolleyResponse(IDTO dto);
	public void onVolleyError(VolleyError error);
}
