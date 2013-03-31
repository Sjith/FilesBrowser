package com.juyg.filesbrowser;

import static com.juyg.filesbrowser.constants.FileDetailsKeys.LAST_MODIFICATION_KEY;
import static com.juyg.filesbrowser.constants.FileDetailsKeys.NAME_KEY;
import static com.juyg.filesbrowser.constants.FileDetailsKeys.FILE_PATH_KEY;
import static com.juyg.filesbrowser.constants.FileDetailsKeys.SIZE_KEY;

import java.io.File;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.juyg.filesbrowser.FileOptionsDialog.FilesListOptionsDialogListener;
import com.juyg.filesbrowser.logic.FilesManager;
import com.juyg.filesbrowser.model.FileData;
import com.juyg.filesbrowser.utils.Utils;

public class FilesActivity extends FragmentActivity implements
		OnItemClickListener, OnItemLongClickListener,
		FilesListOptionsDialogListener {

	private static final int DELETE_OPTION = 0;
	private static final int MOVE_OPTION = 1;
	private static final int COPY_OPTION = 2;
	private static final int RENAME_OPTION = 3;
	private static final int DETAILS_OPTION = 4;

	private String mRootDirectory;
	private int mSelectedFileIndex;
	private List<FileData> mDirFiles;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_files);

		if (savedInstanceState == null) {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				mRootDirectory = Environment.getExternalStorageDirectory()
						.getAbsolutePath();
			} else {
				mRootDirectory = "/";
			}
			mDirFiles = FilesManager.listFiles(mRootDirectory);

		} else {
			mRootDirectory = savedInstanceState.getString("RootDirectory");
			mDirFiles = (List<FileData>) getLastCustomNonConfigurationInstance();
		}

		ListView list = (ListView) findViewById(R.id.filesList);
		ListAdapter adapter = new FilesListAdapter(this, mDirFiles);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);

		refreshList();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("RootDirectory", mRootDirectory);
	}

	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return mDirFiles;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		File file = new File(mRootDirectory, mDirFiles.get(position).getName());
		String filePath = file.getAbsolutePath();

		if (file.isDirectory()) {
			mRootDirectory = filePath;
			mDirFiles = FilesManager.listFiles(mRootDirectory);
			refreshList();
		} else {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file),
					FilesManager.mimeType(filePath));

			try {
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(getApplicationContext(), "No app to open file",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public void onBackPressed() {
		mRootDirectory = mRootDirectory.substring(0,
				mRootDirectory.lastIndexOf("/"));
		mDirFiles = FilesManager.listFiles(mRootDirectory);
		refreshList();
	}

	private void refreshList() {
		ListView list = (ListView) findViewById(R.id.filesList);
		FilesListAdapter adapter = (FilesListAdapter) list.getAdapter();
		adapter.setData(mDirFiles);
		adapter.notifyDataSetChanged();
		TextView path = (TextView) findViewById(R.id.path);
		path.setText(mRootDirectory);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		mSelectedFileIndex = position;
		FileOptionsDialog dialog = new FileOptionsDialog();
		dialog.show(getSupportFragmentManager(), "OptionsDialog");

		return true;
	}

	@Override
	public void dialogMenuPressed(int index) {
		FileData file = mDirFiles.get(mSelectedFileIndex);

		switch (index) {
		case DELETE_OPTION:
			Log.e("as", "EL INDICE 0");
			break;
		case MOVE_OPTION:
			Log.e("as", "EL INDICE 1");
			break;
		case COPY_OPTION:
			Log.e("as", "EL INDICE 2");
			break;
		case RENAME_OPTION:

			break;
		case DETAILS_OPTION:
			FileDetailsDialog dialog = new FileDetailsDialog();
			Bundle args = new Bundle();
			args.putString(NAME_KEY, file.getName());
			args.putString(FILE_PATH_KEY,file.getFilePath());
			args.putString(LAST_MODIFICATION_KEY,
					Utils.formatDate(file.getDate(), true));
			args.putString(SIZE_KEY, Utils.formatFileSize(file.getSize(), true));
			dialog.setArguments(args);
			dialog.show(getSupportFragmentManager(), "DetailsDialog");
			break;
		}
	}

}
