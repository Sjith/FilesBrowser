package com.juyg.filesbrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.juyg.filesbrowser.model.FileData;

public class FilesListAdapter extends BaseAdapter{
	private List<FileData> mData;
	private LayoutInflater mInflater;
	
	
	public FilesListAdapter(Context context,List<FileData> data){
		 // Cache the LayoutInflate to avoid asking for a new one each time.
        mInflater = LayoutInflater.from(context);
		this.mData = data;
	}
	
	public List<FileData> getData(){
		return mData;
	}
	
	public void setData(List<FileData> data){
		this.mData = data;
	}
	
	@Override
	public int getCount() {
		return mData.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		 // A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        FileData fileData = mData.get(position);
        
        // Bind the data efficiently with the holder.
        holder.text.setText(fileData.getName());
        
        switch(fileData.getType()){
        case Directory:
        	holder.text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.folder, 0, 0, 0);
        	break;
        case Image:
        	holder.text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher, 0, 0, 0);
        	break;
        }
        
        

        return convertView;
	}

	
	 static class ViewHolder {
         TextView text;
         ImageView icon;
     }
}
