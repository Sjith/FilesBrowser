package com.juyg.filesbrowser.model;

public class FileData implements Comparable<FileData> {
	private String name;
	private String filePath;
	private long size;
	private long date;
	private FileType type;
	
	public FileData(){
		
	}
	
	public FileData(String name,String filePath,long size, long date,FileType type){
		this.name = name;
		this.filePath = filePath;
		this.size = size;
		this.date = date;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public long getSize(){
		return size;
	}
	
	public void setSize(long size){
		this.size = size;
	}
	
	public long getDate(){
		return date;
	}
	
	public void setDate(long date){
		this.date = date;
	}
	

	@Override
	public int compareTo(FileData another) {
		return name.compareTo(another.name);
	}
	
	public FileType getType(){
		return type;
	}
	
	public void setFileType(FileType type){
		this.type = type;
	}
}
