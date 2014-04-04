package org.openweathermap.sql.model;

import java.util.HashMap;

import android.content.ContentValues;

import com.app.sqlite.base.BaseModel;

public class WeatherModel extends BaseModel {
	private int pid;
	private int cityId;
	private String rawId;
	private String description;
	private String descriptionDetailed;
	private byte[] icon;
	private long timestamp;
	private double tmpDay;
	private double tmpMin;
	private double tmpMax;
	private double tmpNight;
	private double tmpEve;
	private double tmpMorn;
	private double pressure;
	private double humidity;
	private double windSpeed;
	private double windDegrees;
	private double cloudCoverage;

	private static final String COLUMN_PID = "pid";
	private static final String COLUMN_CITYID = "cityId";
	private static final String COLUMN_RAWID = "rawId";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_DESCRIPTIONDETAILED = "descriptionDetailed";
	private static final String COLUMN_ICON = "icon";
	private static final String COLUMN_TIMESTAMP = "timestamp";
	private static final String COLUMN_TMPDAY = "tmpDay";
	private static final String COLUMN_TMPMIN = "tmpMin";
	private static final String COLUMN_TMPMAX = "tmpMax";
	private static final String COLUMN_TMPNIGHT = "tmpNight";
	private static final String COLUMN_TMPEVE = "tmpEve";
	private static final String COLUMN_TMPMORN = "tmpMorn";
	private static final String COLUMN_PRESSURE = "pressure";
	private static final String COLUMN_HUMIDITY = "humidity";
	private static final String COLUMN_WINDSPEED = "windSpeed";
	private static final String COLUMN_WINDDEGREES = "windDegrees";
	private static final String COLUMN_CLOUDCOVERAGE = "cloudCoverage";

	// The SQL provider uses reflection to retrieve the table name from this variable
	public static final String TABLE_NAME = "Weather";

	public int getPid() {
		return pid;
	}

	public void setPid(int newVal) {
		pid = newVal;
	}
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int newVal) {
		cityId = newVal;
	}
	public String getRawId() {
		return rawId;
	}

	public void setRawId(String newVal) {
		rawId = newVal;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String newVal) {
		description = newVal;
	}
	public String getDescriptionDetailed() {
		return descriptionDetailed;
	}

	public void setDescriptionDetailed(String newVal) {
		descriptionDetailed = newVal;
	}
	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] newVal) {
		icon = newVal;
	}
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long newVal) {
		timestamp = newVal;
	}
	public double getTmpDay() {
		return tmpDay;
	}

	public void setTmpDay(double newVal) {
		tmpDay = newVal;
	}
	public double getTmpMin() {
		return tmpMin;
	}

	public void setTmpMin(double newVal) {
		tmpMin = newVal;
	}
	public double getTmpMax() {
		return tmpMax;
	}

	public void setTmpMax(double newVal) {
		tmpMax = newVal;
	}
	public double getTmpNight() {
		return tmpNight;
	}

	public void setTmpNight(double newVal) {
		tmpNight = newVal;
	}
	public double getTmpEve() {
		return tmpEve;
	}

	public void setTmpEve(double newVal) {
		tmpEve = newVal;
	}
	public double getTmpMorn() {
		return tmpMorn;
	}

	public void setTmpMorn(double newVal) {
		tmpMorn = newVal;
	}
	public double getPressure() {
		return pressure;
	}

	public void setPressure(double newVal) {
		pressure = newVal;
	}
	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double newVal) {
		humidity = newVal;
	}
	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double newVal) {
		windSpeed = newVal;
	}
	public double getWindDegrees() {
		return windDegrees;
	}

	public void setWindDegrees(double newVal) {
		windDegrees = newVal;
	}
	public double getCloudCoverage() {
		return cloudCoverage;
	}

	public void setCloudCoverage(double newVal) {
		cloudCoverage = newVal;
	}

	@Override
	public HashMap<String,Integer> getModelColumnTypeMap() {
		HashMap<String,Integer> modelColumns = new HashMap<String,Integer>();
		modelColumns.put(COLUMN_PID, BaseModel.FIELD_INTEGER);
		modelColumns.put(COLUMN_CITYID, BaseModel.FIELD_INTEGER);
		modelColumns.put(COLUMN_RAWID, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_DESCRIPTION, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_DESCRIPTIONDETAILED, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_ICON, BaseModel.FIELD_BLOB);
		modelColumns.put(COLUMN_TIMESTAMP, BaseModel.FIELD_LONG);
		modelColumns.put(COLUMN_TMPDAY, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_TMPMIN, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_TMPMAX, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_TMPNIGHT, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_TMPEVE, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_TMPMORN, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_PRESSURE, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_HUMIDITY, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_WINDSPEED, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_WINDDEGREES, BaseModel.FIELD_DOUBLE);
		modelColumns.put(COLUMN_CLOUDCOVERAGE, BaseModel.FIELD_DOUBLE);
		return modelColumns;
	}

	@Override
	public ContentValues toContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_CITYID, getCityId());
		contentValues.put(COLUMN_RAWID, getRawId());
		contentValues.put(COLUMN_DESCRIPTION, getDescription());
		contentValues.put(COLUMN_DESCRIPTIONDETAILED, getDescriptionDetailed());
		contentValues.put(COLUMN_ICON, getIcon());
		contentValues.put(COLUMN_TIMESTAMP, getTimestamp());
		contentValues.put(COLUMN_TMPDAY, getTmpDay());
		contentValues.put(COLUMN_TMPMIN, getTmpMin());
		contentValues.put(COLUMN_TMPMAX, getTmpMax());
		contentValues.put(COLUMN_TMPNIGHT, getTmpNight());
		contentValues.put(COLUMN_TMPEVE, getTmpEve());
		contentValues.put(COLUMN_TMPMORN, getTmpMorn());
		contentValues.put(COLUMN_PRESSURE, getPressure());
		contentValues.put(COLUMN_HUMIDITY, getHumidity());
		contentValues.put(COLUMN_WINDSPEED, getWindSpeed());
		contentValues.put(COLUMN_WINDDEGREES, getWindDegrees());
		contentValues.put(COLUMN_CLOUDCOVERAGE, getCloudCoverage());
		return contentValues;
	}
}