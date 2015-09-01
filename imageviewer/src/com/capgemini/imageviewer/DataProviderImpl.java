package com.capgemini.imageviewer;

import javafx.scene.image.Image;

/*
 * REV: warstwa dostepu do danych powinna byc zdefiniownana w osobyn pakiecie.
 */
public class DataProviderImpl implements DataProvider {

	@Override
	public Image getImage(String path) {
		return new Image("file:"+path);
	}


}
