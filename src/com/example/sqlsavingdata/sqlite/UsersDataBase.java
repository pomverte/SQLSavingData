package com.example.sqlsavingdata.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlsavingdata.util.Constant;

public class UsersDataBase {

	static final String DATABASE_NAME = "users.db";

	static final int DATABASE_VERSION = 1;

	private final UsersDataBaseHelper mDbHelper;

	UsersDataBase(Context context) {
		mDbHelper = new UsersDataBaseHelper(context);
	}

	private static class UsersDataBaseHelper extends SQLiteOpenHelper {

		private static final String TEXT_TYPE = " TEXT";

		private static final String COMMA_SEP = ",";

		private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + UsersEntry.TABLE_NAME + " (" + UsersEntry._ID
				+ " INTEGER PRIMARY KEY" + COMMA_SEP + UsersEntry.COLUMN_NAME_USERS_FULLNAME + TEXT_TYPE + COMMA_SEP
				+ UsersEntry.COLUMN_NAME_USERS_EMAIL + TEXT_TYPE + COMMA_SEP + ");";

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
