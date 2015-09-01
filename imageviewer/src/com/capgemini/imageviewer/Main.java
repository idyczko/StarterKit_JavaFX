package com.capgemini.imageviewer;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static final String BUNDLE_PATH = "com/capgemini/imageviewer/view/bundle/bundle";

	/*
	 * primaryStage jest przekazywany przez JavaFX do metody start().
	 * Ta zmienna moze byc null'em.
	 */
	private Stage primaryStage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Image Viewer");

		initImageViewerLayout();
	}

	private void initImageViewerLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/fxml/ImageViewer.fxml"));
			loader.setResources(ResourceBundle.getBundle(BUNDLE_PATH));
			BorderPane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("view/css/application.css").toExternalForm());
			this.primaryStage.setScene(scene);
			this.primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
