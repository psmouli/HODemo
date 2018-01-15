package com.ho.demo;

/**
 * A POJO that gives the information about a file
 * 
 * @author mouli
 *
 */
public class FileInfo {

	private String name;
	private String type;
	private String path;
	private String extension;
	private long size;
	
	public FileInfo(String name, String type, String extension, long size, String path) {
		setName(name);
		setType(type);
		setExtension(extension);
		setSize(size);
		setPath(path);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
