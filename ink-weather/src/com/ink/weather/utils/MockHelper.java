package com.ink.weather.utils;

import com.ink.weather.dto.CityDTO;
import com.ink.weather.dto.CoordDTO;
import com.ink.weather.dto.SysDTO;

/**
 * ALL METHODS IN THIS CLASS ARE FOR DEMO PURPOSES, a production release would
 * package the "Popular Cities" into the application as raw JSON and parse it
 * into an object during the initial application load
 * @author samkirton
 */
public class MockHelper {

	/**
	 * @return	A list of "popular cities" displayed in the navigation drawer
	 */
	public static CityDTO[] buildMockCityArray() {		
		CityDTO londonCityDTO = buildCityDTO(
			"GB", 
			"London", 
			51.5072, 
			-0.1275
		);
		
		CityDTO lutonCityDTO = buildCityDTO(
			"GB", 
			"Luton", 
			51.9000, 
			-0.4333
		);
		
		CityDTO manchesterCityDTO = buildCityDTO(
			"GB", 
			"Manchester", 
			53.4667, 
			-2.2333
		);
		
		CityDTO birminghamCityDTO = buildCityDTO(
			"GB", 
			"Birmingham", 
			52.4831, 
			-1.8936
		);
		
		CityDTO[] cityArray = {londonCityDTO,lutonCityDTO,manchesterCityDTO,birminghamCityDTO};
		return cityArray;
	}
	
	/**
	 * @return	A mock CityDTO created based on the information provided
	 */
	private static CityDTO buildCityDTO(String country, String city, double latitude, double longitude) {
		CityDTO cityDTO = new CityDTO();
		SysDTO sysDTO = new SysDTO();
		CoordDTO coordDTO = new CoordDTO();
		
		sysDTO.setCountry(country);
		coordDTO.setLat(latitude);
		coordDTO.setLon(longitude);
		
		cityDTO.setName(city);
		cityDTO.setSys(sysDTO);
		cityDTO.setCoord(coordDTO);
		return cityDTO;
	}
}
