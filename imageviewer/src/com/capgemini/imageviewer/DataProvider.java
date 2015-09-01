package com.capgemini.imageviewer;

import javafx.scene.image.Image;

/*
 * REV: warstwa dostepu do danych powinna byc zdefiniownana w osobyn pakiecie.
 */
public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	/*
	 * REV: Image jest obiektem GUI i nie powiniem byc uzywany w warstwie dostepu do danych.
	 * Lepiej zwrocic dane obrazka (bajty, InputStream) albo sciezke do pliku.
	 */
	Image getImage(String path);
}
