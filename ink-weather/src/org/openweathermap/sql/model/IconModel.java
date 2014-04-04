package org.openweathermap.sql.model;

import com.app.sqlite.base.BaseModel;

import java.util.HashMap;

import android.content.ContentValues;

public class IconModel extends BaseModel {
	private int pid;
	private String code;
	private byte[] raw;

	private static final String COLUMN_PID = "pid";
	private static final String COLUMN_CODE = "code";
	private static final String COLUMN_RAW = "raw";

	// The SQL provider uses reflection to retrieve the table name from this variable
	public static final String TABLE_NAME = "Icon";

	public int getPid() {
		return pid;
	}

	public void setPid(int newVal) {
		pid = newVal;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String newVal) {
		code = newVal;
	}
	public byte[] getRaw() {
		return raw;
	}

	public void setRaw(byte[] newVal) {
		raw = newVal;
	}

	@Override
	public HashMap<String,Integer> getModelColumnTypeMap() {
		HashMap<String,Integer> modelColumns = new HashMap<String,Integer>();
		modelColumns.put(COLUMN_PID, BaseModel.FIELD_INTEGER);
		modelColumns.put(COLUMN_CODE, BaseModel.FIELD_STRING);
		modelColumns.put(COLUMN_RAW, BaseModel.FIELD_BLOB);
		return modelColumns;
	}

	@Override
	public ContentValues toContentValues() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_CODE, getCode());
		contentValues.put(COLUMN_RAW, getRaw());
		return contentValues;
	}
}