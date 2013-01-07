package com.example.sqlsavingdata.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.sqlsavingdata.util.Constant;

/**
 * Implementation of the users database
 * 
 * @author PoMverte
 * 
 */
public class UsersDataBase {

	static final String DATABASE_NAME = "users.db";

	static final int DATABASE_VERSION = 1;

	private final UsersDataBaseHelper mDbHelper;

	UsersDataBase(Context context) {
		mDbHelper = new UsersDataBaseHelper(context);
	}

	public long insert(String table, String nullColumnHack, ContentValues values) {
		SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
		return sqlDB.insert(table, nullColumnHack, values);
	}

	/**
	 * @param projection
	 *            The columns to return
	 * @param selection
	 *            The selection clause
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replaced by the values from selectionArgs, in order
	 *            that they appear in the selection. The values will be bound as Strings.
	 * @param groupBy
	 * @param having
	 * @param sortOrder
	 * @return A Cursor over all rows matching the query
	 */
	public Cursor query(String[] projection, String selection, String[] selectionArgs, String groupBy, String having,
			String sortOrder) {

		SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();

		/*
		 * The SQLiteBuilder provides a map for all possible columns requested to actual columns in the database,
		 * creating a simple column alias mechanism by which the ContentProvider does not need to know the real column
		 * names
		 */
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables(UsersEntry.TABLE_NAME);
		builder.setProjectionMap(null);

		Cursor cursor = builder.query(sqlDB, projection, selection, selectionArgs, groupBy, having, sortOrder);

		if (cursor == null) {
			return null;
		} else if (!cursor.moveToFirst()) {
			cursor.close();
			return null;
		}
		return cursor;
	}

	private static class UsersDataBaseHelper extends SQLiteOpenHelper {

		private static final String TEXT_TYPE = " TEXT";

		private static final String COMMA_SEP = ",";

		private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + UsersEntry.TABLE_NAME + " (" + UsersEntry._ID
				+ " INTEGER PRIMARY KEY" + COMMA_SEP + UsersEntry.COLUMN_NAME_USERS_FULLNAME + TEXT_TYPE + COMMA_SEP
				+ UsersEntry.COLUMN_NAME_USERS_EMAIL + TEXT_TYPE + ");";

		private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UsersEntry.TABLE_NAME;

		UsersDataBaseHelper(Context context) {
			super(context, UsersDataBase.DATABASE_NAME, null, UsersDataBase.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(Constant.TAG_SQL, SQL_CREATE_ENTRIES);
			db.execSQL(SQL_CREATE_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}

	}

}
