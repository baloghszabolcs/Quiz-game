package com.bszab.quiz.model;

/**
 * Interface for getting informations from database.
 **/
public interface Quiz {
    /**
     * Returns the number of the questions.
     * @return number of questions.
     * */
    int getNumberOfQuestions();
    /**
     * Returns a string array which contains the ID of every question in string.
     * @return string array which contains the ID of every question in string
     * */
    String[] getQuestionIDstrings();
    /**
     * Returns the question by index of the question.
     * @param questionNumber index of the question.
     * @return the text of the question.
     * */
    String getQuestionText(int questionNumber);
    /**
     * Returns the question by questions ID.
     * @param questionIDstring questions ID in string.
     * @return the text of the question.
     * */
    String getQuestionText(String questionIDstring);
    /**
     * Returns the answer.
     * @param answerNumber the correct answers index which can be 1 or 2.
     * @param questionNumber the index of the question.
     * @return the answer using the given parameters.
     * */
    String getQuestionAnswerText(int answerNumber, int questionNumber);
    /**
     * Returns true if the given answer is correct otherwise false.
     * @param answerNumber the correct answers index which can be 1 or 2.
     * @param questionNumber the index of the question.
     * @return true if the given answer is correct otherwise false.
     * */
    boolean isQuestionAnswerCorrect(int answerNumber, int questionNumber);
}