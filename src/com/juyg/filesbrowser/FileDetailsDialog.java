package com.juyg.filesbrowser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import static com.juyg.filesbrowser.constants.FileDetailsKeys.*;
public class FileDetailsDialog extends DialogFragment{
	private String [] mDetails;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
		
		if(args.containsKey(CONTENTS_KEY)){
			mDetails = new String [4];
			mDetails[3] = args.getString(CONTENTS_KEY);
		}else{
			mDetails = new String [3];	
		}
		
		mDetails[0] = args.getString(NAME_KEY);
		mDetails[1] = args.getString(LAST_MODIFICATION_KEY);
		mDetails[2] = args.getString(SIZE_KEY);
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.detailsDialogTitle);		
		builder.setAdapter(new FileDetailsListAdapter(getActivity(), mDetails), null);
		builder.setPositiveButton(R.string.detailsDialogOkButton, null);
		
		return builder.create();
	}
}
