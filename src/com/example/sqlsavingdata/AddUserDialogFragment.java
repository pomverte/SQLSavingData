package com.example.sqlsavingdata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class AddUserDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.label_add_user);

		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		builder.setView(inflater.inflate(R.layout.dialog_adduser, null));

		OnClickListener cancelListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				AddUserDialogFragment.this.getDialog().cancel();
			}
		};
		OnClickListener saveListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				((MainActivity) getActivity()).doPositiveClick();
			}
		};

		// binding button and listener
		builder.setNegativeButton(R.string.button_cancel, cancelListener);
		builder.setPositiveButton(R.string.button_save, saveListener);

		return builder.create();
	}
}
