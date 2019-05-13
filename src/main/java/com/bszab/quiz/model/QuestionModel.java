package com.bszab.quiz.model;

/**
 * A class for representing the structure of a question.
 */

public class QuestionModel {

    //CHECKSTYLE:OFF
    private String questionText;
    private String answer1Text;
    private String answer2Text;
    private int correctAnswer;
    //CHECKSTYLE:ON
    /**
     * Returns the question.
     * @return the text of the question.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns the first answer.
     * @return the text of first answer of the question.
     */
    public String getAnswer1Text() {
        return answer1Text;
    }

    /**
     * Returns the second answer.
     * @return the text of second answer of the question.
     */
    public String getAnswer2Text() {
        return answer2Text;
    }

    /**
     * Constructor to create a {@link QuestionModel} object.
     *
     * @param question the text of the question.
     * @param answer1 the text of first answer.
     * @param answer2 the text of second answer
     * @param correctAnswer the correct answers index, which can be 1 or 2 ;
     */
    public QuestionModel(String question, String answer1, String answer2,int correctAnswer){
        this.questionText = question;
        this.answer1Text = answer1;
        this.answer2Text = answer2;
        this.correctAnswer = correctAnswer;
    }
    /**
     * Returns the correct answers index.
     * @return the correct answers index, which can be 1 or 2 .
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

}


