package com.bszab.quiz.controllers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class StartController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(StartController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    Button playButton;

    @FXML
    Button exitFxId;

    @FXML
    public void startGame(ActionEvent event){

        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/GameScene.fxml"));
            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StartScene.fxml"));
            logger.info("Start button pressed! The game is starting...");

            Stage stage = (Stage) playButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/gameStyle.css");
            stage.setResizable(false);
            stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            logger.error("Couldn't load the GameScene.fxml");
            ex.printStackTrace();
            System.exit(1);

        }

    }

    @FXML
    public void endGame(ActionEvent event){
        logger.info("Exit button pressed!");
        Platform.exit();
    }

    //CHECKSTYLE:ON
}
