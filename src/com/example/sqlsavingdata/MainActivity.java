package com.example.sqlsavingdata;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqlsavingdata.sqlite.UsersEntry;
import com.example.sqlsavingdata.sqlite.UsersProvider;
import com.example.sqlsavingdata.util.Constant;

public class MainActivity extends FragmentActivity implements AddUserDialogFragment.AddUserDialogListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fillUsersList();
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
		fillUsersList();
	}

	private void fillUsersList() {

		Log.d(Constant.TAG_SQL, "com.example.sqlsavingdata.MainActivity.fillUsersList(View)");

		String[] projection = { UsersEntry._ID, UsersEntry.COLUMN_NAME_USERS_FULLNAME, UsersEntry.COLUMN_NAME_USERS_EMAIL };
		Cursor cursor = getContentResolver().query(UsersProvider.CONTENT_URI, projection, null, null, null);
		int usersNumber = cursor.getCount();

		TextView textView = (TextView) findViewById(R.id.users_list_caption);
		textView.setText(String.format("You have %s user(s) in your database :", usersNumber));

		ListView listView = (ListView) findViewById(R.id.users_list);

		List<String> values = new ArrayList<String>();
		while (!cursor.isAfterLast()) {
			// fill the list with the user's name
			String userFullName = cursor.getString(cursor.getColumnIndexOrThrow(UsersEntry.COLUMN_NAME_USERS_FULLNAME));
			values.add(userFullName);
			cursor.moveToNext();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1,
				values);

		listView.setAdapter(adapter);
	}
}
