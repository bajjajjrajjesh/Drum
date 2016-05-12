package com.virtual.drum;

import java.util.Comparator;

public class FileComparator implements Comparator<FileModel> {

	@Override
	public int compare(FileModel arg0, FileModel arg1) {
	
		return ((int) (arg1.getLast_modify()-arg0.getLast_modify()));
	}

}
