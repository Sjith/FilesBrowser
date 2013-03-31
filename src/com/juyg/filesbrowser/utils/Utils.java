package com.juyg.filesbrowser.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class Utils {

	public static String formatDate(long milliseconds,boolean detailed){
		Date date = new Date(milliseconds);
		SimpleDateFormat format;
		
		if(detailed){
			format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault());	
		}else{
			format = new SimpleDateFormat("HH:mm dd/MM/yyyy",Locale.getDefault());
		}
		
		return format.format(date);
	}
	
	public static String formatFileSize(long bytes,boolean detailed){
		double size = bytes;
		int unit = 0;
		
		while(size >= 1024){
			size /= 1024;
			unit++;
		}
		
		String formattedSize = null;
		
		if(unit > 0){
			formattedSize = String.format(Locale.getDefault(),"%.1f", size);
		}
		
		switch(unit){
		case 0:
			formattedSize = bytes + " bytes";
			break;
		case 1:
			formattedSize = formattedSize + " KB";
			break;
		case 2:
			formattedSize = formattedSize + " MB";
			break;
		case 3:
			formattedSize = formattedSize + " GB";
			break;
		}
		
		if(unit > 0 && detailed){
			String formattedBytes = String.format(Locale.getDefault(),"%,d", bytes);
			formattedSize += " ( "+formattedBytes+" bytes )"; 
		}
		
		
		return formattedSize;
	}
}
