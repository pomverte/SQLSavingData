package com.example.sqlsavingdata.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.sqlsavingdata.util.Constant;

public class UsersProvider extends ContentProvider {

	private static String AUTHORITY = "com.example.sqlsavingdata.sqlite.UsersProvider";

	private static final String BASE_PATH = "users";

	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	private UsersDataBase mUsers;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException("TODO delete");
	}

	@Override
	public String getType(Uri uri) {
		throw new UnsupportedOperationException("TODO getType");
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException("TODO insert");
	}

	@Override
	public boolean onCreate() {
		Log.d(Constant.TAG_SQL, "com.example.sqlsavingdata.sqlite.UsersProvider.onCreate()");
		mUsers = new UsersDataBase(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		throw new UnsupportedOperationException("TODO query");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException("TODO update");
	}

}
