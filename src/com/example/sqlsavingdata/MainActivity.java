package com.example.sqlsavingdata;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {

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

	public void doPositiveClick() {
		// TODO Auto-generated method stub
	}
}
