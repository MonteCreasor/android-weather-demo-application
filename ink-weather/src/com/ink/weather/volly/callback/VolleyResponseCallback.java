package com.ink.weather.volly.callback;

import com.android.volley.VolleyError;
import com.ink.weather.dto.base.IDTO;

/**
 * The response of a VolleyRequest, any Activity that runs a request
 * must implement this interface to receive a response from Volly.
 * @author samkirton
 */
public interface VolleyResponseCallback {
	public void onVolleyResponse(IDTO dto);
	public void onVolleyError(VolleyError error);
}
