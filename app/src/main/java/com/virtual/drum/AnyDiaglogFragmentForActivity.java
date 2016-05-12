package com.virtual.drum;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

@SuppressLint("NewApi")
public class AnyDiaglogFragmentForActivity extends DialogFragment {

	private AlertDialog.Builder builder;
	private AnyDialogListener dialogListener;
	private ListView fileListView;
	private List<FileModel> fileList;
	private View view;
	private QuickAdapter<FileModel> fileAdapter;
	private TextView tvnoiteam;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dialogListener = (AnyDialogListener) getActivity();

	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		dialogListener.OnDialogCancel();

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = LayoutInflater.from(getActivity());
		view = inflater.inflate(R.layout.diaglog_layout, null, false);
		tvnoiteam = (TextView) view.findViewById(R.id.tvnoiteam);
		tvnoiteam.setVisibility(TextView.GONE);

		view.findViewById(R.id.btn_close).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dismiss();

					}
				});
		fileListView = (ListView) view.findViewById(R.id.list);

		// ret = new ListView(getActivity());
		builder = new AlertDialog.Builder(getActivity(),
				AlertDialog.THEME_HOLO_DARK);
		fileList = getFileslist();
		setAdapter();
		builder.setView(view);
		return builder.create();
	}

	private void setAdapter() {

		fileAdapter = new QuickAdapter<FileModel>(getActivity(),
				R.layout.list_layout) {

			@Override
			protected void convert(BaseAdapterHelper v, final FileModel model) {
				v.setText(R.id.tv_name, model.getFile_name());
				v.getView(R.id.btn_play_record).setOnClickListener(
						new android.view.View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dismiss();
								dialogListener.onDialogSingleItemSelected(model
										.getFile());

							}
						});
				v.getView(R.id.btn_share).setOnClickListener(
						new android.view.View.OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(Intent.ACTION_SEND);
								intent.setType("audio/mp3");
								intent.putExtra(
										Intent.EXTRA_STREAM,
										Uri.parse("file://"
												+ (model.getFile())
														.getAbsolutePath()));
								startActivity(intent);

							}
						});
				v.getView(R.id.btn_delete).setOnClickListener(
						new android.view.View.OnClickListener() {

							@Override
							public void onClick(View v) {
								model.getFile().delete();
								updatedData();
							}
						});

			}
		};
		if (fileList.size() <= 0) {
			tvnoiteam.setVisibility(TextView.VISIBLE);
			fileListView.setVisibility(ListView.GONE);
		} else {
			tvnoiteam.setVisibility(TextView.GONE);
			fileListView.setVisibility(ListView.VISIBLE);
		}

		fileAdapter.addAll(fileList);
		fileListView.setAdapter(fileAdapter);

	}

	public List<FileModel> getFileslist() {
		List<FileModel> fileModels = new ArrayList<FileModel>();
		File filesDir = new File(DrumBeats.file_save_directory);
		if (!filesDir.exists()) {
			filesDir.mkdirs();
		}
		File[] list = filesDir.listFiles();
		for (File f : list) {

			Log.d("sssssssssssss", f.getName());
			if (f.getAbsolutePath().endsWith(".drum")) {
				fileModels.add(new FileModel(f));
			}
		}

		Collections.sort(fileModels, new FileComparator());
		return fileModels;
	}

	public void updatedData() {
		fileAdapter.clear();
		fileList = getFileslist();
		fileAdapter.addAll(fileList);
		fileAdapter.notifyDataSetChanged();
		fileListView.invalidateViews();

		if (fileList.size() <= 0) {
			tvnoiteam.setVisibility(TextView.VISIBLE);
			fileListView.setVisibility(ListView.GONE);
		} else {
			tvnoiteam.setVisibility(TextView.GONE);
			fileListView.setVisibility(ListView.VISIBLE);
		}
	}

}
