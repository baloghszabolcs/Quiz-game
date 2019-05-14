package com.bszab.quiz;

import com.bszab.quiz.controllers.ViewController;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class QuizTest {
    private static ViewController quiz;

    public QuizTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        quiz = new ViewController();
    }
    @AfterClass
    public static void tearDownClass() {
        quiz = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void scoreTest() {
        quiz.setScore(10);
        quiz.scorePlus();
        int expected = 11;
        assertEquals(expected, quiz.getScore());

        quiz.setScore(20);
        quiz.scorePlus();
        expected = 21;
        assertEquals(expected, quiz.getScore());
    }

    @Test
    public void randomArrayTest(){
        quiz.randomNumbersSize=10;
        quiz.randomArrayGenerator();
        int expected = 10;
        int result = quiz.randomSzamok.size();
        assertEquals(expected,result);
    }

}
