package com.bszab.quiz.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for starting JavaFx application.
 */
public class MainApp extends Application {

    //CHECKSTYLE:OFF
    private static Logger logger = LoggerFactory.getLogger(MainApp.class);
    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StartScene.fxml"));
            logger.info("The application is starting...");
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Quiz_game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex){
            logger.error("Couldn't find the StartScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This method is only required to call {@code launch(args} so the JavaFx
     * application can start.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //CHECKSTYLE:ON
}
