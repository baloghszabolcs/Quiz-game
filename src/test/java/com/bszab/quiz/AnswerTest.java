package com.bszab.quiz;

import com.bszab.quiz.model.ParserXml;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnswerTest {
    private static ParserXml parser = new ParserXml("src/test/java/com/bszab/quiz/quiztest.xml");

    public AnswerTest(){}

    @BeforeClass
    public static void setUpClass() {
        parser = new ParserXml("src/test/java/com/bszab/quiz/quiztest.xml");
    }
    @AfterClass
    public static void tearDownClass() {
        parser = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    /**
     * Tests if an answer is true or false
     */

    @Test
    public void correctAnswerTest() {

        boolean expResult = true;
        boolean result = parser.isQuestionAnswerCorrect(1, 1);
        assertEquals(expResult,result);

        expResult = false;
        result = parser.isQuestionAnswerCorrect(2, 1);
        assertEquals(expResult,result);


    }

    /**
     * Tests that one of the answers is correct
     */
    @Test
    public void oneIsCorrectTest() {

        boolean expResult = true;
        boolean result = (parser.isQuestionAnswerCorrect(1, 1) != parser.isQuestionAnswerCorrect(2, 1));
        assertEquals(expResult,result);


    }

    @Test
    public void getQuestionAnswerTextTest(){
        String expResult = "Its a compiled language based off of Basic.";
        String result = parser.getQuestionAnswerText(1,1);
        assertEquals(expResult,result);

    }

}
