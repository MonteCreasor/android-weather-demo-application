package com.ink.weather.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ink.weather.dto.base.IDTO;

/**
 * NOTE: This class should be auto generated from a web service spec
 * @author samkirton
 */
public class CityDTO implements IDTO {
	private long mId;
	private long mDt;
	private String mName;
	private CoordDTO mCoord;
	private MainDTO mMain;
	private WindDTO mWind;
	private SysDTO mSys;
	private CloudDTO mClouds;
	private ThreeHourDTO mRain3h;
	private ThreeHourDTO mSnow3h;
	private WeatherDTO[] mWeatherList;
	
	public long getId() {
		return mId;
	}
	
	public void setId(long newVal) {
		mId = newVal;
	}
	
	public long getDt() {
		return mDt;
	}
	
	public void setDt(long newVal) {
		mDt = newVal;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String newVal) {
		mName = newVal;
	}
	
	public CoordDTO getCoord() {
		return mCoord;
	}
	
	public void setCoord(CoordDTO newVal) {
		mCoord = newVal;
	}
	
	public MainDTO getMain() {
		return mMain;
	}
	
	public void setMain(MainDTO newVal) {
		mMain = newVal;
	}
	
	public WindDTO getWind() {
		return mWind;
	}
	
	public void setWind(WindDTO newVal) {
		mWind = newVal;
	}
	
	public SysDTO getSys() {
		return mSys;
	}
	
	public void setSys(SysDTO newVal) {
		mSys = newVal;
	}
	
	public CloudDTO getClouds() {
		return mClouds;
	}
	
	public void setClouds(CloudDTO newVal) {
		mClouds = newVal;
	}
	
	public ThreeHourDTO getRain3h() {
		return mRain3h;
	}
	
	public void setRain3g(ThreeHourDTO newVal) {
		mRain3h = newVal;
	}
	
	public ThreeHourDTO getSnow3h() {
		return mSnow3h;
	}
	
	public void setSnow3g(ThreeHourDTO newVal) {
		mSnow3h = newVal;
	}
	
	public WeatherDTO[] getWeatherList() {
		return mWeatherList;
	}
	
	public void setWeatherList(WeatherDTO[] newVal) {
		mWeatherList = newVal;
	}
	
	public CityDTO() { }
	
	public CityDTO(JSONObject jsonObject) throws JSONException {
		fromJson(jsonObject);
	}
	
	@Override
	public void fromJson(JSONObject jsonObject) throws JSONException {
		mId = jsonObject.has("id") ? jsonObject.getLong("id") : -1;
		mDt = jsonObject.has("dt") ? jsonObject.getLong("dt") : -1;
		mName = jsonObject.has("name") ? jsonObject.getString("name") : null;
		
		if (jsonObject.has("coord")) {
			JSONObject coordJSONObject = jsonObject.getJSONObject("coord");
			CoordDTO coordDTO = new CoordDTO(coordJSONObject);
			mCoord = coordDTO;
		}
		
		if (jsonObject.has("main")) {
			JSONObject mainJSONObject = jsonObject.getJSONObject("main");
			MainDTO mainDTO = new MainDTO(mainJSONObject);
			mMain = mainDTO;
		}
		
		if (jsonObject.has("wind")) {
			JSONObject windJSONObject = jsonObject.getJSONObject("wind");
			WindDTO windDTO = new WindDTO(windJSONObject);
			mWind = windDTO;
		}
		
		if (jsonObject.has("sys")) {
			JSONObject sysJSONObject = jsonObject.getJSONObject("sys");
			SysDTO sysDTO = new SysDTO(sysJSONObject);
			mSys = sysDTO;
		}
		
		if (jsonObject.has("clouds")) {
			JSONObject cloudsJSONObject = jsonObject.getJSONObject("clouds");
			CloudDTO cloudDTO = new CloudDTO(cloudsJSONObject);
			mClouds = cloudDTO;
		}
		
		if (jsonObject.has("rain")) {
			JSONObject rainJSONObject = jsonObject.getJSONObject("rain");
			ThreeHourDTO threeHourDTO = new ThreeHourDTO(rainJSONObject);
			mRain3h = threeHourDTO;
		}
		
		if (jsonObject.has("snow")) {
			JSONObject snowJSONObject = jsonObject.getJSONObject("snow");
			ThreeHourDTO threeHourDTO = new ThreeHourDTO(snowJSONObject);
			mSnow3h = threeHourDTO;
		}
		
		if (jsonObject.has("weather")) {
			JSONArray jsonArrayWeather = new JSONArray(jsonObject.getString("weather"));
			mWeatherList = new WeatherDTO[jsonArrayWeather.length()];
			for (int i = 0; i < jsonArrayWeather.length(); i++) {
				WeatherDTO weatherDTO = new WeatherDTO(jsonArrayWeather.getJSONObject(i));
				this.mWeatherList[i] = weatherDTO;
			}
		}
	}
}
