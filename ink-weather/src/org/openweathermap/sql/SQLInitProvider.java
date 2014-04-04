package org.openweathermap.sql;

import org.openweathermap.utils.ResourceHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ink.weather.R;

/**
 * A provider class that creates a SQLite database schema
 * @author samkirton
 */
public class SQLInitProvider extends SQLiteOpenHelper {
	private Context mContext;
	
	private static final String DATABASE_NAME = "openweathermap";
	private static final int VERSION = 1;
	
	public SQLInitProvider(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String cityCacheSchema = ResourceHelper.getFileContentsAsString(mContext, R.raw.city);
		String weatherCacheSchema = ResourceHelper.getFileContentsAsString(mContext, R.raw.weather);
		String iconCacheSchema = ResourceHelper.getFileContentsAsString(mContext, R.raw.icon);
		
		database.execSQL(cityCacheSchema);
		database.execSQL(weatherCacheSchema);
		database.execSQL(iconCacheSchema);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) { }
}
