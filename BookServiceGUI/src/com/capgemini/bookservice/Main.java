package com.capgemini.bookservice;
	
import java.io.IOException;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private static final String FILENAME = "com/capgemini/bookservice/view/bundle/bundle";
    
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Book Service");

        initRootLayout();

        showBookService();
    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/fxml/rootLayout.fxml"));
            loader.setResources(ResourceBundle.getBundle(FILENAME));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets()
			.add(getClass().getResource("view/css/standard.css").toExternalForm());
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBookService() {
    	    try {
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(Main.class.getResource("view/fxml/bookservice.fxml"));
    	        loader.setResources(ResourceBundle.getBundle(FILENAME));
    	        AnchorPane bookService = (AnchorPane) loader.load();

    	        rootLayout.setCenter(bookService);

    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
