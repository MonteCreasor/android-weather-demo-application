package org.openweathermap.sql.model;

import com.app.sqlite.base.BaseModel;

import java.util.HashMap;

import android.content.ContentValues;

public class CityModel extends BaseModel {
	private int pid;
	private String rawId;
	private String name;
	private String country;
	private String displayValue;
	private String latitude;
	private String longitude;

	private static final String COLUMN_PID = "pid";
	private static final String COLUMN_RAWID = "rawId";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_COUNTRY = "country";
	private static final String COLUMN_DISPLAYVALUE = "displayValue";
	private static final String COLUMN_LATITUDE = "latitude";
	private static final String COLUMN_LONGITUDE = "longitude";

	// The SQL provider uses reflection to retrieve the table name from this variable
	public static final String TABLE_NAME = "City";

	public int getPid() {
		return pid;
	}

	public void setPid(int newVal) {
		pid = newVal;
	}
	public String getRawId() {
		return rawId;
	}

	public void setRawId(String newVal) {
		rawId = newVal;
	}
	public String getName() {
		return name;
	}

	public void setName(String newVal) {
		name = newVal;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String newVal) {
		country = newVal;
	}
	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String newVal) {
		displayValue = newVal;
	}
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String newVal) {
		latitude = newVal;
	}
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String newVal) {
		longitude = newVal;
	}

	@Override
	public HashMap<String,Integer> getModelColumnTypeMap() {
		HashMap<String,Integer> modelColumns = new HashMap<String,Integer>();
		modelColumns.put(COLUMN_PID, BaseModel.FIELD_INTEGER);
		modelColumns.put(COLUMN_RAWID, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_NAME, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_COUNTRY, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_DISPLAYVALUE, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_LATITUDE, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_LONGITUDE, BaseModel.FIELD_STRING);
		return modelColumns;
	}

	@Override
	public ContentValues toContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_RAWID, getRawId());
		contentValues.put(COLUMN_NAME, getName());
		contentValues.put(COLUMN_COUNTRY, getCountry());
		contentValues.put(COLUMN_DISPLAYVALUE, getDisplayValue());
		contentValues.put(COLUMN_LATITUDE, getLatitude());
		contentValues.put(COLUMN_LONGITUDE, getLongitude());
		return contentValues;
	}
}