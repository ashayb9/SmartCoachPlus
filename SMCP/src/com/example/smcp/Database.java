package com.example.smcp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {

	public static final String KEY_ROWID = "Id";
	public static final String KEY_DATE = "Date";
	public static final String KEY_LATITUDE = "Lat";
	public static final String KEY_LONGITUDE = "Long";
	public static final String KEY_NOTES = "Notes";
	private static final String TAG = Database.class.getSimpleName();

	private static final String KEY_SLEEP = "Sleep";
	private static final String KEY_SLEEPHOURS = "SleepHours";
	private static final String KEY_STRESS = "Stress";

	private static final String DATABASE_NAME = "SmartCoach";

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE_OVEREATING = "Phase1";
	private static final String DATABASE_TABLE_QUESTIONS = "Questions";

	private static String DATABASE_CREATE = "create table Phase1 (_Id integer primary key autoincrement, "
			+ "Date text not null,"
			+ "Lat float text not null,"
			+ "Long float text not null," + "Notes text not null);";

	private static final String DATABASE_CREATECUS = "create table Questions (_Id integer primary key autoincrement, "
			+ "Sleep text not null,"
			+ "SleepHours text not null,"
			+ "Stress text not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public Database(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
				db.execSQL(DATABASE_CREATECUS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}

	// ---opens the database---
	public Database open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	public long insertRecordPhase1(OvereatingEntry data) {
		ContentValues initialValues = new ContentValues();
		Log.d(TAG, String.format("location %f, %f\n", data.getLatitude(), data.getLongitude()));
		initialValues.put(KEY_DATE, data.getDate());
		initialValues.put(KEY_LATITUDE, data.getLatitude());
		initialValues.put(KEY_LONGITUDE, data.getLongitude());
		initialValues.put(KEY_NOTES, data.getNote());
		return DBHelper.getWritableDatabase().insert(DATABASE_TABLE_OVEREATING, null, initialValues);
	}

	public long insertQuestion(String Sleep, String SleepHours, String Stress) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_SLEEP, Sleep);
		initialValues.put(KEY_SLEEPHOURS, SleepHours);
		initialValues.put(KEY_STRESS, Stress);
		return db.insert(DATABASE_TABLE_QUESTIONS, null, initialValues);
	}
	
	public String getPath(){
		Log.d(TAG, DBHelper.getWritableDatabase().getPath());
		return DBHelper.getWritableDatabase().getPath();


	}
public List<OvereatingEntry> getAllDataFromTable (){
	List<OvereatingEntry> overeatingList = new ArrayList<OvereatingEntry>();
	OvereatingEntry insertdata = null;
	String sql = "select * from " + DATABASE_TABLE_OVEREATING;
	Cursor cursor = DBHelper.getReadableDatabase().rawQuery(sql,null);
		
	cursor.moveToNext();
	return overeatingList;
}
}

