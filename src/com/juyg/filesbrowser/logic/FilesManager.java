package com.juyg.filesbrowser.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


import android.webkit.MimeTypeMap;

import com.juyg.filesbrowser.model.FileData;
import com.juyg.filesbrowser.model.FileType;

public class FilesManager {

	public static List<FileData> listFiles(String path) {
		List<FileData> dirFiles = new ArrayList<FileData>();

		File dir = new File(path);

		for (String dirFile : dir.list()) {
			File file = new File(path+"/"+dirFile);
			if(!file.isHidden()){
				if(file.isDirectory()){
					dirFiles.add(new FileData(file.getName(),FileType.Directory));	
				}else{
					dirFiles.add(new FileData(file.getName(),FileType.Image));
				} 
			}
		}

		Collections.sort(dirFiles);
		return dirFiles ;
	}

	public static String mimeType(String filePath) {
		String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

		MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
 
		String mimeType = mimeTypeMap.getMimeTypeFromExtension(extension
				.toLowerCase(Locale.getDefault()));

		return mimeType;

	}

}
