package com.virtual.drum;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class AnyDiaglogFragmentForMusic extends DialogFragment {

	private AlertDialog.Builder builder;
	private AnyDialogListenerMusic dialogListener;
	private ListView fileListView;
	private List<MusicModel> fileList;

	private View view;
	private QuickAdapter<MusicModel> fileAdapter;
	private TextView tvnoiteam;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dialogListener = (AnyDialogListenerMusic) getActivity();

	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater inflater = LayoutInflater.from(getActivity());
		view = inflater.inflate(R.layout.music_layout, null, false);
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
		getMusic();
		setAdapter();
		builder.setView(view);
		return builder.create();
	}

	private void setAdapter() {
		fileAdapter = new QuickAdapter<MusicModel>(getActivity(),
				R.layout.list_music_layout) {

			@Override
			protected void convert(BaseAdapterHelper v, final MusicModel model) {
				v.setText(R.id.tv_name_music, model.getName());
				v.getView(R.id.btn_play_record).setOnClickListener(
						new android.view.View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dismiss();
								dialogListener.onMusicFileName(model.getPath());

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

	private void getMusic() {
		final Cursor mCursor = getActivity().managedQuery(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA }, null,
				null, "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");
		int count = mCursor.getCount();

		fileList = new ArrayList<MusicModel>();

		if (mCursor.moveToFirst()) {
			do {
				fileList.add(new MusicModel(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)),mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))));


			} while (mCursor.moveToNext());
		}

		mCursor.close();

	}

}
