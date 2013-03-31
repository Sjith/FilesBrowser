package com.juyg.filesbrowser.model;

public class FileData implements Comparable<FileData> {
	private String name;
	private String size;
	private String date;
	private FileType type;
	
	public FileData(){
		
	}
	
	public FileData(String name,String size, String date,FileType type){
		this.name = name;
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
	
	public String getSize(){
		return size;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
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
