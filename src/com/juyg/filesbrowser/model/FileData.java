package com.juyg.filesbrowser.model;

public class FileData implements Comparable<FileData> {
	private String name;
	private FileType type;
	
	public FileData(){
		
	}
	
	public FileData(String name,FileType type){
		this.name = name;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
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
