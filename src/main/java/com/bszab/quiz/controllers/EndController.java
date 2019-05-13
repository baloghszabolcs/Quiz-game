package com.bszab.quiz.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

//CHECKSTYLE:OFF
public class EndController implements Initializable {

    private static Logger logger = LoggerFactory.getLogger(EndController.class);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreLabel.setText("Your best score was: " + quiz.getScore()+"/"+quiz.getRandomNumbersSize() );
    }

    @FXML
    Button againButton;


    @FXML
    Label scoreLabel;


    @FXML
    public void endGame(ActionEvent event){
        logger.info("Exit button pressed!");
        Platform.exit();
    }

    ViewController quiz = new ViewController();


    @FXML
    public void again(ActionEvent event){
        try {
            logger.info("Play again button pressed!");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));
            Stage stage = (Stage) againButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/startStyle.css");
            stage.setResizable(false);
            stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            logger.error("Couldn't load the startScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    //CHECKSTYLE:ON

}
