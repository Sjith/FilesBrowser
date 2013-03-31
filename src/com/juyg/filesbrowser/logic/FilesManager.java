package com.juyg.filesbrowser.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.webkit.MimeTypeMap;

import com.juyg.filesbrowser.model.FileData;
import com.juyg.filesbrowser.model.FileType;
import com.juyg.filesbrowser.utils.Utils;

public class FilesManager {

	public static List<FileData> listFiles(String path) {
		List<FileData> dirFiles = new ArrayList<FileData>();

		File dir = new File(path);

		for (String dirFile : dir.list()) {
			File file = new File(dir, dirFile);

			if (!file.isHidden()) {
				String size = Utils.formatFileSize(fileSize(file), false);
				String date = Utils.formatDate(file.lastModified(), false);

				if (file.isDirectory()) {
					dirFiles.add(new FileData(file.getName(), size, date,
							FileType.Directory));
				} else {
					dirFiles.add(new FileData(file.getName(), size, date,
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
			if (file.isDirectory()) {
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
