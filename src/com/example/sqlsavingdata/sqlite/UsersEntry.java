package com.example.sqlsavingdata.sqlite;

import android.provider.BaseColumns;

public abstract class UsersEntry implements BaseColumns {

	public static final String TABLE_NAME = "users";

	public static final String COLUMN_NAME_USERS_FULLNAME = "fullname";

	public static final String COLUMN_NAME_USERS_EMAIL = "email";

	private UsersEntry() {
	}

}
