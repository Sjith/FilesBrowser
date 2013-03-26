package com.juyg.filesbrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.juyg.filesbrowser.logic.FilesManager;
import com.juyg.filesbrowser.model.FileData;

public class FilesActivity extends Activity implements OnItemClickListener,OnItemLongClickListener {
	private String mRootDirectory;
	private List<FileData> mDirFiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_files);
		mRootDirectory = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		mDirFiles = FilesManager.listFiles(mRootDirectory);
		
		ListView list = (ListView) findViewById(R.id.filesList);
		ListAdapter adapter = new FilesListAdapter(this, mDirFiles);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);
		
		refreshList();

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.files, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String filePath = mRootDirectory + "/"
				+ mDirFiles.get(position).getName();
		File file = new File(filePath);

		if (file.isDirectory()) {
			mRootDirectory = filePath;
			mDirFiles = FilesManager.listFiles(mRootDirectory);
			refreshList();
		}else{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), FilesManager.mimeType(filePath));
			
			try{
			startActivity(intent);
			}catch(ActivityNotFoundException e){
				Toast.makeText(getApplicationContext(), "No app to open file",Toast.LENGTH_LONG).show();
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
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Log.e("EE", "LONG PRESS");
		return false;
	}
}
