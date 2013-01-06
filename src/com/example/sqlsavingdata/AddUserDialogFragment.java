package com.example.sqlsavingdata;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.sqlsavingdata.util.Constant;

/**
 * Dialog Fragment displaying user information to be stored
 * 
 * @author PoMverte
 * 
 */
public class AddUserDialogFragment extends DialogFragment {

	private AddUserDialogListener mListener;

	private String userFullname;

	private String userEmail;

	/*
	 * The activity that creates an instance of this dialog fragment must implement this interface in order to receive
	 * event callbacks. Each method passes the DialogFragment in case the host needs to query it.
	 */
	public interface AddUserDialogListener {
		/**
		 * Callbacks method
		 * 
		 * @param dialog
		 */
		public void onDialogPositiveClick(DialogFragment dialog);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the AddUserDialogListener so we can send events to the host
			mListener = (AddUserDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString() + " must implement AddUserDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.label_add_user);

		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		final View view = inflater.inflate(R.layout.dialog_adduser, null);
		builder.setView(view);

		// Create listener
		OnClickListener cancelListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				AddUserDialogFragment.this.getDialog().cancel();
			}
		};

		OnClickListener saveListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {

				// get informations submitted
				EditText userFullname = (EditText) view.findViewById(R.id.field_username);
				EditText userEmail = (EditText) view.findViewById(R.id.field_useremail);

				AddUserDialogFragment.this.userFullname = userFullname.getText().toString();
				AddUserDialogFragment.this.userEmail = userEmail.getText().toString();

				Log.d(Constant.TAG_SQL, AddUserDialogFragment.this.userFullname);
				Log.d(Constant.TAG_SQL, AddUserDialogFragment.this.userEmail);

				mListener.onDialogPositiveClick(AddUserDialogFragment.this);
			}
		};

		// binding button and listener
		builder.setNegativeButton(R.string.button_cancel, cancelListener);
		builder.setPositiveButton(R.string.button_save, saveListener);

		return builder.create();
	}

	public String getUserFullname() {
		return userFullname;
	}

	public String getUserEmail() {
		return userEmail;
	}
}
