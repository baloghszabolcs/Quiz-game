package com.bszab.quiz.controllers;

import com.bszab.quiz.model.TestModel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

//CHECKSTYLE:OFF
public class ViewController implements Initializable  {
    private static Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ido(false,true);
        randomArrayGenerator();
        questionIterator= randomSzamok.get(randomNumberIterator);
        rbGroup = new ToggleGroup();
        Question();
        Answer();
        Pb.setProgress(0);
        rb1.setToggleGroup(rbGroup);
        rb2.setToggleGroup(rbGroup);
        setScore(0);
        validateQuestionNumber();
    }


    @FXML
    private Pane firstPane;
    @FXML
    private Pane secondPane;
    @FXML
    private  Button button;
    @FXML
    private  Label alertText;
    @FXML
    private  Label TimeLbl;
    @FXML
    private RadioButton rb1;
    @FXML
    private ProgressBar Pb;
    @FXML
    private RadioButton rb2;
    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup rbGroup;

    private TestModel theTest = new TestModel("src/main/resources/QuizXml/quiz.xml");


    private  int questionIterator=0;
    private int randomNumberIterator=0;
    private int correctIndex= theTest.getQuestions().get(questionIterator).getCorrectAnswer();
    public int randomNumbersSize = 6;
    private final double progress = 1.0/randomNumbersSize;
    private double progressDinamic = progress;
    private boolean ok=false;
    private static int score;
    public int getRandomNumbersSize() {
        return randomNumbersSize;
    }

    private void validateQuestionNumber(){
        if (theTest.getQuestions().size() < randomNumbersSize ){
            logger.error("Too many questions");
            exit();
        }
    }

    public ArrayList<Integer> randomSzamok = new ArrayList<>(randomNumbersSize);
    public void randomArrayGenerator(){
        Random randomgen = new Random();
        while (randomSzamok.size() < randomNumbersSize) {
            int rand = randomgen.nextInt(41);
            if (!randomSzamok.contains(rand)) {
                randomSzamok.add(rand);
            }
        }
    }
    private int TIME_SEC = 2000; //in seconds

    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            final SimpleDateFormat simpDate;
            simpDate = new SimpleDateFormat("mm:ss");
            for ( ; TIME_SEC>=-1; TIME_SEC--) {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        TimeLbl.setText(""+simpDate.format(TIME_SEC*1000));

                        if (TIME_SEC == -1){
                            logger.info("Time is up !");
                            exit();
                            cancel(true);
                        }
                    }
                });
                Thread.sleep(1000);
            }
            return null;
        }

    };
    Thread t1 = new Thread(task);
    private void ido(boolean resumeTime ,boolean firstStart){
        if (firstStart == true && resumeTime == false){
            logger.info("The time is starting !");
            t1.setDaemon(true);
            t1.start();
        }
        if (resumeTime == true && firstStart == false){
            logger.info("Resuming time !");
            t1.resume();
        } else  if (resumeTime == false && firstStart == false){
            logger.info("The time was stopped !");
            t1.suspend();
        }
    }


    public static void setScore(int score) {
        ViewController.score = score;
    }
    public  int getScore(){
        return ViewController.score;
    }
    public void scorePlus() {
        logger.info("Score increase");
        ViewController.score++;
    }

    @FXML
    private void next(ActionEvent event) {
        logger.info("Next button pressed");
        if (rb1.isSelected() == false && rb2.isSelected() == false){
            logger.warn("No answer selected");
            ido(false,false);
            firstPane.setDisable(true);
            firstPane.setOpacity(0.3);
            secondPane.setVisible(true);
            alertText.setText("You must select at least one answer!");
            ok = false;
        }

        else if (rb1.isSelected() && (correctIndex == 1)) {
            if (randomNumberIterator >= getScore()) {
                scorePlus();
            }
            logger.info("Correct answer !");
            nextQuestion();
        } else if (rb2.isSelected() && (correctIndex == 2)) {
            if (randomNumberIterator >= getScore()) {
                scorePlus();
            }
            logger.info("Correct answer !");
            nextQuestion();
        }
        else {
            ok = false;
            rb1.setSelected(false);
            rb2.setSelected(false);
            logger.info("Wrong answer !");
            ido(false,false);
            firstPane.setDisable(true);
            firstPane.setOpacity(0.3);
            secondPane.setVisible(true);
            alertText.setText("Wrong answer! the quiz will restart!");

            randomNumberIterator = 0;
            questionIterator = randomSzamok.get(randomNumberIterator);
            progressDinamic = progress * randomNumberIterator;
            Pb.setProgress(progressDinamic);
            Question();
            Answer();
        }



    }

    @FXML
    private void returnToGame(ActionEvent event){
        logger.info("Resume game");
        ido(true,false);
        firstPane.setDisable(false);
        firstPane.setOpacity(1);
        secondPane.setVisible(false);
    }

    @FXML
    private void exitGame(ActionEvent event) {
        logger.info("Exit button pressed. Exiting game...");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StartScene.fxml"));
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/startStyle.css");
            stage.setResizable(false);
            stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            logger.error("Couldn't load the StartScene.fxml");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void nextQuestion() {
        if (randomNumberIterator +1 == randomNumbersSize) {
            logger.info("Game over !");
            exit();
        } else {
            logger.info("Next question !");
            randomNumberIterator++;
            progressDinamic = progress * randomNumberIterator;
            Pb.setProgress(progressDinamic);
            questionIterator = randomSzamok.get(randomNumberIterator);
            Question();
            Answer();
            if (rb1.isSelected()) {
                rb1.setSelected(false);
            } else if (rb2.isSelected()) {
                rb2.setSelected(false);
            }}
    }

    private  void exit(){
        logger.info("Finishing the game");
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/endScene.fxml"));
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/endStyle.css");
            stage.setResizable(false);
            stage.setTitle("End");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            logger.error("Couldn't load the endScene.fxml");
            e.printStackTrace();
            System.exit(1);
        }
    }
    //CHECKSTYLE:ON

    /**
     * Display the question on the screen.
     */
    private void Question(){
        questionLabel.setText(theTest.getQuestions().get(questionIterator).getQuestionText());
    }

    /**
     * Display the answers on the screen.
     */
    private void Answer() {
        rb1.setText(theTest.getQuestions().get(questionIterator).getAnswer1Text());
        rb2.setText(theTest.getQuestions().get(questionIterator).getAnswer2Text());
        correctIndex= theTest.getQuestions().get(questionIterator).getCorrectAnswer();
    }



}
