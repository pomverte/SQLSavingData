package com.example.sqlsavingdata.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.sqlsavingdata.util.Constant;

/**
 * Provides access to the users database
 * 
 * @author PoMverte
 * 
 */
public class UsersProvider extends ContentProvider {

	private static String AUTHORITY = "com.example.sqlsavingdata.sqlite.UsersProvider";

	private static final String BASE_PATH = "users";

	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	private static final int USERS = 10;
	private static final int USER_ID = 20;

	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, USERS);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", USER_ID);
	}

	/**
	 * Users database
	 */
	private UsersDataBase mUsersDataBase;

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
		Log.d(Constant.TAG_SQL, "com.example.sqlsavingdata.sqlite.UsersProvider.insert(Uri, ContentValues)");

		int uriType = sURIMatcher.match(uri);
		long id = 0;

		switch (uriType) {
		case USERS:
			id = mUsersDataBase.insert(UsersEntry.TABLE_NAME, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public boolean onCreate() {
		Log.d(Constant.TAG_SQL, "com.example.sqlsavingdata.sqlite.UsersProvider.onCreate()");
		mUsersDataBase = new UsersDataBase(getContext());
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
