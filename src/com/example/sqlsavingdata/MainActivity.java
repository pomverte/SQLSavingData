package com.example.sqlsavingdata;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.sqlsavingdata.sqlite.UsersEntry;
import com.example.sqlsavingdata.sqlite.UsersProvider;
import com.example.sqlsavingdata.util.Constant;

public class MainActivity extends FragmentActivity implements AddUserDialogFragment.AddUserDialogListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void showAddUserForm(View view) {
		DialogFragment dialogFragment = new AddUserDialogFragment();
		dialogFragment.show(getSupportFragmentManager(), Constant.TAG_DIALOG);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Log.d(Constant.TAG_SQL, "com.example.sqlsavingdata.MainActivity.onDialogPositiveClick(DialogFragment)");

		String userFullName = ((AddUserDialogFragment) dialog).getUserFullname();
		String userEmail = ((AddUserDialogFragment) dialog).getUserEmail();

		Log.d(Constant.TAG_SQL, userFullName);
		Log.d(Constant.TAG_SQL, userEmail);

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(UsersEntry.COLUMN_NAME_USERS_FULLNAME, userFullName);
		values.put(UsersEntry.COLUMN_NAME_USERS_EMAIL, userEmail);
		Uri uri = getContentResolver().insert(UsersProvider.CONTENT_URI, values);
		Log.d(Constant.TAG_SQL, uri.toString());
	}
}
