package org.openweathermap.dto.base;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A data transfer object "DTO" is a pure java object representation of
 * of a definition language. DTOs are used to abstract the type of definition
 * language being used. In this example JSON is the definition language used so a
 * fromJson method is exposed. If XML or YAML were introduced a fromXML or fromYAML method 
 * could be exposed and no business logic would need to changed.
 * 
 * The actual DTO classes e.g CityDTO, handle the parsing between java objects and the
 * chosen definition language
 * @author samkirton
 */
public interface IDTO {
	public void fromJson(JSONObject jsonObject) throws JSONException;
}
