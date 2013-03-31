package com.juyg.filesbrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FileDetailsListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private String[] mData;

	public FileDetailsListAdapter(Context context, String[] data) {
		mInflater = LayoutInflater.from(context);
		mData = data;
	}
	
	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.details_list_item, null);
			
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		switch(position){
		case 0:
			holder.title.setText(R.string.detailsDialogFileName);
			holder.content.setText(mData[0]);
			break;
		case 1:
			holder.title.setText(R.string.detailsDialogFileDate);
			holder.content.setText(mData[1]);
			break;
		case 2:
			holder.title.setText(R.string.detailsDialogFileSize);
			holder.content.setText(mData[2]);
			break;
		case 3:
			holder.title.setText(R.string.detailsDialogFileContent);
			holder.content.setText(mData[3]);
			break;
		}
		
		
		return convertView;
	}
	
	 static class ViewHolder {
         TextView title;
         TextView content;
     }

}
