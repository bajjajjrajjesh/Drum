package com.virtual.drum;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AnyDiaglogFragment extends DialogFragment {
	
	

	private AlertDialog.Builder builder;
	private AnyDialogListener2 dialogListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dialogListener = (AnyDialogListener2) getActivity();
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		builder = new AlertDialog.Builder(getActivity(),
				AlertDialog.THEME_HOLO_DARK);
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		View v = layoutInflater.inflate(R.layout.input, null);
		final EditText input = (EditText) v.findViewById(R.id.ed_input);

		input.setText("Drum" + genRandowm());
		final TextView tv = (TextView) v.findViewById(R.id.textView1);
		v.findViewById(R.id.btn_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dismiss();

					}
				});

		v.findViewById(R.id.btn_save).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						String filename = input.getText().toString();
						if (filename != null && !(filename.length() <= 0)
								&& !filename.contains(" ")
								&& !filename.matches(".*\\W{1,}.*")) {
							dialogListener.onSaveFileName(filename);
							dismiss();
						} else {
							tv.setText("Invalid file name ");
						}

					}
				});

		builder.setView(v);
		return builder.create();
	}

	public int genRandowm() {
		Random r = new Random(System.currentTimeMillis());
		return (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
	}

}
