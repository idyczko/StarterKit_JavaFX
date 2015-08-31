package com.capgemini.imageviewer;

import javafx.scene.image.Image;

public class DataProviderImpl implements DataProvider {

	@Override
	public Image getImage(String path) {
		return new Image("file:"+path);
	}

	
}
