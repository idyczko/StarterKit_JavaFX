package com.capgemini.imageviewer.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImageViewerModel {

	private final IntegerProperty selectedFileIndex = new SimpleIntegerProperty();
	
	private final StringProperty folderPath = new SimpleStringProperty();

	private final ListProperty<String> files = new SimpleListProperty<String>(
			FXCollections.observableList(new ArrayList<String>()));

	public String getFolderPath() {
		return folderPath.get();
	}

	public ObservableList<String> getFiles() {
		return files.get();
	}

	public StringProperty getFolderPathProperty() {
		return folderPath;
	}
	
	public ObservableValue<? extends ObservableList<String>> getFilesProperty() {
		return files;
	}

	public IntegerProperty getSelectedFileIndex() {
		return selectedFileIndex;
	}

}
