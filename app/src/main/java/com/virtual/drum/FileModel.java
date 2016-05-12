package com.virtual.drum;

import java.io.File;

public class FileModel {

	private String file_name;
	private long last_modify;
	private File file;
	
	public FileModel(File f){
		this.file=f;
		this.file_name=file.getName();
		this.last_modify=file.lastModified();
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public long getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(long last_modify) {
		this.last_modify = last_modify;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
