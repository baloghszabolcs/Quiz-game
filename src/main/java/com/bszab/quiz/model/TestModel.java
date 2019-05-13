package com.bszab.quiz.model;

import java.util.ArrayList;

/**
 * Class for filling a list with {@link QuestionModel}s which contains questions and answers.
 */
public class TestModel {
    //CHECKSTYLE:OFF
    private int numberOfQuestions;
    //CHECKSTYLE:ON
    /**
     * A constructor which creates a {@code TestModel} object.
     * This object contains an {@link QuestionModel} array with {@code answer1, answer2, correctAnswer, question} parameters
     * @param fileName path to xml database.
     * */
    public TestModel(String fileName) {
        String question = null;
        String answer1 = null;
        String answer2 = null;
        int correctAnswer = 0;
        ParserXml parser = new ParserXml(fileName);
        numberOfQuestions = parser.getNumberOfQuestions();
        for (int i = 0; i < numberOfQuestions; i++) {
            question = null;
            answer1 = null;
            answer2 = null;
            correctAnswer = 0;
            question = parser.getQuestionText(i + 1);
            answer1 = parser.getQuestionAnswerText(1, i + 1);
            answer2 = parser.getQuestionAnswerText(2, i + 1);
            if (parser.isQuestionAnswerCorrect(1, i + 1)) {
                correctAnswer = 1;
            }
            if (parser.isQuestionAnswerCorrect(2, i + 1)) {
                correctAnswer = 2;
            }
            QuestionModel questionModel = new QuestionModel(question, answer1, answer2, correctAnswer);
            this.addQuestion(questionModel);
        }
    }
    /**
     * An ArrayList which contains QuestionModel objects.
     * */
    private ArrayList<QuestionModel> questions = new ArrayList<QuestionModel>();

    /**
     * Returns a list with questions.
     * @return an ArrayList with QuestionModel objects.
     */
    public ArrayList<QuestionModel> getQuestions() {
        return questions;
    }

    /**
     * Adds a {@code QuestionModel} object to {@code questions} list.
     * @param question a {@code QuestionModel} object.
     */
    public void addQuestion(QuestionModel question) {
        this.questions.add(question);
    }



}
