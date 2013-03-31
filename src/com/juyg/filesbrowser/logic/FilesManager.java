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

		File[] files = dir.listFiles();
		
		for (File file : files) {
			
			if (!file.isHidden()) {
				long size = fileSize(file);
				long date = file.lastModified();

				if (file.isDirectory()) {
					dirFiles.add(new FileData(file.getName(),file.getAbsolutePath(), size, date,
							FileType.Directory));
				} else {
					dirFiles.add(new FileData(file.getName(),file.getAbsolutePath(), size, date,
							FileType.Image));
				}
			}
		}

		Collections.sort(dirFiles);
		return dirFiles;
	}

	public static long fileSize(File file) {
		long size = 0;

		if (!file.isHidden()) {
			if (file.isDirectory() && file.list().length != 0) {
				String[] dirFilesNames = file.list();

				for (String dirFilesName : dirFilesNames) {
					File dirFile = new File(file, dirFilesName);
					size += fileSize(dirFile);
				}

			} else {
				size = file.length();
			}
		}

		return size;
	}

	public static String mimeType(String filePath) {
		String extension = filePath.substring(filePath.lastIndexOf('.') + 1);

		MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

		String mimeType = mimeTypeMap.getMimeTypeFromExtension(extension
				.toLowerCase(Locale.getDefault()));

		return mimeType;

	}

}
