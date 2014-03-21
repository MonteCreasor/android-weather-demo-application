package com.ink.weather.test.dto;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import com.ink.weather.dto.CloudDTO;
import com.ink.weather.dto.CoordDTO;
import com.ink.weather.dto.MainDTO;
import com.ink.weather.dto.SysDTO;
import com.ink.weather.dto.ThreeHourDTO;
import com.ink.weather.dto.WeatherDTO;
import com.ink.weather.dto.WindDTO;

/**
 * When a JSONObject is parsed to a DTO representation, the original
 * JSON data MUST match the data in the DTO java object.
 * NOTE: This test suite should be auto generated from a web service spec
 * @author samkirton
 */
public class DTOParserTest extends TestCase {

	public DTOParserTest(String name) {
		super(name);
	}
	
	public void testCloudDTO() throws JSONException {
		// given the cloud JSON data request
		double all = 10.23;
		
		JSONObject json = new JSONObject();
		json.put("all", all);

		// when the CloudDTO is populated
		CloudDTO cloudDTO = new CloudDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(cloudDTO.getAll(), all);
	}
	
	public void testCoordDTO() throws JSONException {
		// given the coord JSON data request
		double lat = 10.23;
		double lon = 19.2;
		
		JSONObject json = new JSONObject();
		json.put("lat", lat);
		json.put("lon", lon);

		// when the CoordDTO is populated
		CoordDTO coordDTO = new CoordDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(coordDTO.getLat(), lat);
		assertEquals(coordDTO.getLon(), lon);
	}
	
	public void testMainDTO() throws JSONException {
		// given the main JSON data request
		double temp = 10.23;
		double humidity = 19.2;
		double pressure = 1.9;
		double tempMin = 559.1;
		double tempMax = 1002;
		
		JSONObject json = new JSONObject();
		json.put("temp", temp);
		json.put("humidity", humidity);
		json.put("pressure", pressure);
		json.put("temp_min", tempMin);
		json.put("temp_max", tempMax);
		
		// when the MainDTO is populated
		MainDTO mainDTO = new MainDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(mainDTO.getTemp(), temp);
		assertEquals(mainDTO.getHumidity(), humidity);
		assertEquals(mainDTO.getPressure(), pressure);
		assertEquals(mainDTO.getTempMin(), tempMin);
		assertEquals(mainDTO.getTempMax(), tempMax);
	}
	
	public void testSysDTO() throws JSONException {
		// given the sys JSON data request
		String country = "Spain";
		
		JSONObject json = new JSONObject();
		json.put("country", country);
		
		// when the SysDTO is populated
		SysDTO sysDTO = new SysDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(sysDTO.getCountry(), country);
	}
	
	public void testThreeHourDTO() throws JSONException {
		// given the 3h JSON data request
		int threeHourValue = 4;
		
		JSONObject json = new JSONObject();
		json.put("3h", threeHourValue);
		
		// when the ThreeHourDTO is populated
		ThreeHourDTO threeHourDTO = new ThreeHourDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(threeHourDTO.getThreeHour(), threeHourValue);
	}
	
	public void testWeatherDTO() throws JSONException {
		// given the weather JSON data request
		long id = 10384234;
		String main = "Windy";
		String description = "Small gusts of wind";
		String icon = "2D";
		
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("main", main);
		json.put("description", description);
		json.put("icon", icon);
		
		// when the WeatherDTO is populated
		WeatherDTO weatherDTO = new WeatherDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(weatherDTO.getId(), id);
		assertEquals(weatherDTO.getMain(), main);
		assertEquals(weatherDTO.getDescription(), description);
		assertEquals(weatherDTO.getIcon(), icon);
	}
	
	public void testWindDTO() throws JSONException {
		// given the wind JSON data request
		double speed = 1.28;
		double gust = 9.28;
		double deg = 7.15;
		
		JSONObject json = new JSONObject();
		json.put("speed", speed);
		json.put("gust", gust);
		json.put("deg", deg);
		
		// when the WindDTO is populated
		WindDTO windDTO = new WindDTO(json);
		
		// then the data is assigned to the correct DTO variable
		assertEquals(windDTO.getSpeed(), speed);
		assertEquals(windDTO.getGust(), gust);
		assertEquals(windDTO.getDeg(), deg);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
}
