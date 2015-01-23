package com.example.smcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.Data;
import android.util.Log;

public class Database {

	public static final String KEY_ROWID = "Id";
	public static final String KEY_DATE = "Date";
	public static final String KEY_LATITUDE = "Lat";
	public static final String KEY_LONGITUDE = "Long";
	public static final String KEY_CONDITIONS = "Conditions";
	public static final String KEY_OVEREATINGSTRESS = "OvereatingStress";
	public static final String KEY_LOCATIONLABEL = "LocationLbl";
	public static final String KEY_HUNGERLVL = "HungerLvl";
	public static final String KEY_EATING = "Eating";
	public static final String KEY_HUNGERDIALOGLVL = "HungerLvlDialog";
	public static final String KEY_HUNGERDATETIME = "HungerDateTime";
	static final String TAG = Database.class.getSimpleName();

	private static final String KEY_SLEEP = "Sleep";
	private static final String KEY_SLEEPHOURS = "SleepHours";
	private static final String KEY_SLEEPTIME = "SleepTime";
	private static final String KEY_STRESS = "Stress";
	private static final String KEY_STRESSTIME = "StressTime";
	private static final String KEY_WEIGHT = "Weight";
	private static final String KEY_WEIGHTTIME = "WeightTime";
	
	

	private static final String DATABASE_NAME = "SmartCoach";

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE_OVEREATING = "Overeating_Table";
	private static final String DATABASE_TABLE_QUESTIONS = "Sleep_Quality_Table";
	private static final String DATABASE_TABLE_STRESS = "Stresslvl";
	private static final String DATABASE_TABLE_WEIGHT = "DailyWeight";
	private static final String DATABASE_TABLE_HUNGERLVL = "Hunger_Lvl_Table";
	

	private static String DATABASE_CREATE = "create table Overeating_Table (_Id integer primary key autoincrement, "
			+ "Date text not null,"
			+ "Lat float text not null,"
			+ "Long float text not null,"
			+ "LocationLbl text not null,"
			+ "Eating text not null,"
			+ "Conditions text not null," + "OvereatingStress text not null, " + "HungerLvl text not null);";

	private static final String DATABASE_CREATECUS = "create table Sleep_Quality_Table (_Id integer primary key autoincrement, "
			+ "Sleep text not null,"
			+ "SleepHours text not null,"
			+ "SleepTime text not null);";

	private static final String DATABASE_CREATEUS = "create table Stresslvl (_Id integer primary key autoincrement,"
			+ "Stress text not null," + "StressTime text not null);";


	private static final String DATABASE_CREATEW = "create table DailyWeight(_Id integer primary key autoincrement,"
			+ "Weight text not null," + "WeightTime text not null);";
	
	private static final String DATABASE_CREATEHUNGER = "create table Hunger_Lvl_Table(_Id integer primary key autoincrement, " + 
			"HungerLvlDialog integer not null, " + "HungerDateTime text not null);";
	

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
			Log.d(TAG, "Creating Database");
			try {
				db.execSQL(DATABASE_CREATE);
				db.execSQL(DATABASE_CREATECUS);
				db.execSQL(DATABASE_CREATEHUNGER);
				db.execSQL(DATABASE_CREATEW);
				db.execSQL(DATABASE_CREATEUS);
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
		Log.d(TAG, "Database/open");
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	public long insertRecordPhase1(OvereatingEntry data) {
		ContentValues initialValues = new ContentValues();
		Log.d(TAG,
				String.format("location %f, %f\n", data.getLatitude(),
						data.getLongitude()));
		initialValues.put(KEY_DATE, data.getDate());
		initialValues.put(KEY_LATITUDE, data.getLatitude());
		initialValues.put(KEY_LONGITUDE, data.getLongitude());
		initialValues.put(KEY_LOCATIONLABEL, data.getLocationlabel());
		initialValues.put(KEY_CONDITIONS, data.getNote());
		initialValues.put(KEY_EATING, data.getEatingAnswer());
		initialValues.put(KEY_OVEREATINGSTRESS, data.getOvereatingStress());
		initialValues.put(KEY_HUNGERLVL, data.getHungerLvl());
		return db.insert(DATABASE_TABLE_OVEREATING, null, initialValues);
	}

	public long insertQuestion(QuestionsEntry data) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_SLEEP, data.getSleep());
		initialValues.put(KEY_SLEEPHOURS, data.getSleephours());
		initialValues.put(KEY_SLEEPTIME, data.getSleeptimeentry());

		return db.insert(DATABASE_TABLE_QUESTIONS, null, initialValues);
	}

	public long insertStresslvl(StressEntry data) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_STRESS, data.getStress());
		initialValues.put(KEY_STRESSTIME, data.getStresstime());
		return db.insert(DATABASE_TABLE_STRESS, null, initialValues);
	}

	public long insertDailyWeight(WeightEntry data) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_WEIGHT, data.getWeight());
		initialValues.put(KEY_WEIGHTTIME, data.getWeightentrytime());
		return db.insert(DATABASE_TABLE_WEIGHT, null, initialValues);
	}

	public long insertHungerLvl(HungerEntry data){
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_HUNGERDIALOGLVL, data.getLevel());
		initialValues.put(KEY_HUNGERDATETIME, data.getDate());
		return db.insert(DATABASE_TABLE_HUNGERLVL, null, initialValues);
	}

	public String getPath() {
		Log.d(TAG, DBHelper.getWritableDatabase().getPath());
		return DBHelper.getWritableDatabase().getPath();

	}

	public List<OvereatingEntry> getAllDataFromTable() {
		List<OvereatingEntry> overeatingList = new ArrayList<OvereatingEntry>();
		OvereatingEntry insertdata = null;
		String sql = "select * from " + DATABASE_TABLE_OVEREATING;
		Cursor cursor = DBHelper.getReadableDatabase().rawQuery(sql, null);

		cursor.moveToNext();
		return overeatingList;
	}

	public void copyToStorage(Context ctx) {
		File dbFile = new File(DBHelper.getWritableDatabase().getPath());
		File outFile = new File(ctx.getExternalFilesDir(null),
				"smcpdb.db");
		Log.d(DatabaseHelper.class.getSimpleName(), outFile.getAbsolutePath());
		try {
			FileInputStream dbSource = new FileInputStream(dbFile);
			FileOutputStream dbOut = new FileOutputStream(outFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = dbSource.read(buffer)) > 0) {
				dbOut.write(buffer, 0, len);
			}
			dbSource.close();
			dbOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
