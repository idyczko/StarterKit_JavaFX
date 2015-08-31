package com.capgemini.imageviewer;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage primaryStage = new Stage();
	
	@Override 
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/fxml/ImageViewer.fxml"));
			BorderPane root= loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("view/css/application.css").toExternalForm());
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
