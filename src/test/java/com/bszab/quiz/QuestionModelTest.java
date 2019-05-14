package com.bszab.quiz;
import com.bszab.quiz.model.QuestionModel;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class QuestionModelTest {

    private static QuestionModel questionModel;
    public QuestionModelTest(){}

    @BeforeClass
    public static void setUpClass() {

        questionModel = new QuestionModel("question","answer1","answer2",1);
    }
    @AfterClass
    public static void tearDownClass() {
        questionModel = null;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getQuestionTextTest(){
        String expResult = "question";
        String result = questionModel.getQuestionText();
        assertEquals(expResult,result);
    }

    @Test
    public void getAnswer1TextTest(){
        String expResult = "answer1";
        String result = questionModel.getAnswer1Text();
        assertEquals(expResult,result);
    }
    @Test
    public void getAnswer2TextTest(){
        String expResult = "answer2";
        String result = questionModel.getAnswer2Text();
        assertEquals(expResult,result);
    }
    @Test
    public void getCorrectAnswerTest(){
        int expResult = 1;
        int result = questionModel.getCorrectAnswer();
        assertEquals(expResult,result);
    }
}
