package com.capgemini.imageviewer;

import java.io.File;
import java.io.FilenameFilter;

public class ImageFileFilter implements FilenameFilter {
	private final String[] okFileExtensions = new String[] { "jpg", "png", "bmp" };

	public boolean accept(File directory, String fileName) {
		for (String extension : okFileExtensions) {
			if (fileName.toLowerCase().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
}
