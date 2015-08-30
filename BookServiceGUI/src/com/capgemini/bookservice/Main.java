package com.capgemini.bookservice;
	
import java.io.IOException;
import java.util.ResourceBundle;

import com.capgemini.bookservice.view.BookServiceController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    private static final String BUNDLE_PATH = "com/capgemini/bookservice/view/bundle/bundle";
    
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Book Service");

        initBookServiceLayout();
    }


    public void initBookServiceLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/fxml/bookservice.fxml"));
            loader.setResources(ResourceBundle.getBundle(BUNDLE_PATH));
            BorderPane bookServiceLayout = (BorderPane) loader.load();

            Scene scene = new Scene(bookServiceLayout);
            scene.getStylesheets()
			.add(getClass().getResource("view/css/standard.css").toExternalForm());
            
            
            primaryStage.setScene(scene);
            primaryStage.show();

            BookServiceController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
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
