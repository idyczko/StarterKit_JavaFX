package com.capgemini.imageviewer;

import javafx.scene.image.Image;

public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	Image getImage(String path);
}
