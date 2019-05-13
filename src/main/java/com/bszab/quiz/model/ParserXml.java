package com.bszab.quiz.model;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A class to parse the entire xml document to read the questions and the answers.
 */

public class ParserXml implements Quiz {

    //CHECKSTYLE:OFF
    private String xmlFile;
    private File file;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private NodeList nodeLst;
    private NodeList answerElementList;
    private NodeList theAnswer;
    private NodeList answerAttrValue;
    private Node fstNode;
    private Element questionNode;
    private Element answerElementOne;
    private Attr answerAttr;
    //CHECKSTYLE:ON

    /**
     * A constructor to create a {@link ParserXml} object which parses the xml file.
     * @param fileName path to xml database.
     */
    public ParserXml(String fileName) {
        xmlFile = fileName;
        file = new File(xmlFile);
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Exception found in ParserXml constructor: "
                    + e.getMessage());
        }
    }

    /**
     * Returns the number of questions.
     * @return number of questions.
     */
    public int getNumberOfQuestions() {
        int numberOfQuestions = 0;
        /**
         * Access to question list.
         */
        nodeLst = doc.getElementsByTagName("question");
        numberOfQuestions = nodeLst.getLength();
        if (numberOfQuestions < 0) {
            numberOfQuestions = 0;
        }
        return (numberOfQuestions);
    }


    /**
     * Returns a string array which contains the ID of every question in string.
     * @return string array which contains the ID of every question in string
     */
    public String[] getQuestionIDstrings() {


        int numberOfQuestions = this.getNumberOfQuestions();
        int i = 0;

        /**
         * array for storing questions
         */
        String idStrings[] = new String[numberOfQuestions];

        for (i = 0; i < numberOfQuestions; i++) {

            /**
             * Read <question> node with index "i"
             */
            fstNode = nodeLst.item(i);
            NamedNodeMap aNamedNodeMap = fstNode.getAttributes();
            Node anItem = aNamedNodeMap.item(0);
            NodeList aNodeList = anItem.getChildNodes();
            Node anItem2 = aNodeList.item(0);
            idStrings[i] = anItem2.getNodeValue();
        }

        return (idStrings);
    }


    /**
     * Returns the question by index of the question.
     * @param questionNumber index of the question.
     * @return the text of the question.
     */
    public String getQuestionText(int questionNumber) {
        String questionText = this
                .getQuestionText(this.getQuestionIDstrings()[questionNumber - 1]);
        return (questionText);
    }


    /**
     * Returns the question by questions ID.
     * @param questionIDstring questions ID in string.
     * @return the text of the question.
     */
    public String getQuestionText(String questionIDstring) {
        String questionText = "";
        String questionIdString_tmp = "";
        boolean found = false;
        int i = 0;
        for (; i < this.getNumberOfQuestions() && found == false; i++) {
            /**
             * Read <question> node with index "i"
             */
            fstNode = nodeLst.item(i);
            /**
             * Access to the ID string
             */
            questionIdString_tmp = fstNode.getAttributes().item(0)
                    .getChildNodes().item(0).getNodeValue();

            /**
             * checking if this is the ID string you are looking for.
             */
            if (questionIdString_tmp.equals(questionIDstring)) {
                found = true;
                /**
                 * Access to the question text
                 */
                questionText = fstNode.getChildNodes().item(0).getNodeValue();
            }
        }

        return (questionText);
    }

    /**
     * Returns the answer using the given parameters.
     * @param answerNumber the correct answers index which can be 1 or 2.
     * @param questionNumber the index of the question.
     * @return the answer using the given parameters.
     */
    public String getQuestionAnswerText(int answerNumber, int questionNumber) {
        String questionAnswerText = "";
        /**
         * Access to question list
         */
        nodeLst = doc.getElementsByTagName("question");

        /**
         * Read <question> node with index "i"
         */
        fstNode = nodeLst.item(questionNumber - 1);
        /**
         * Read the <answer> tag
         */
        questionNode = (Element) fstNode;
        answerElementList = questionNode.getElementsByTagName("answer");
        answerElementOne = (Element) answerElementList.item(answerNumber - 1);

        /**
         *  Read <answer> tag childrens
         */
        theAnswer = answerElementOne.getChildNodes();

        /**
         *  Print the first child value (the answer)
         */
        questionAnswerText = theAnswer.item(0).getNodeValue();

        return (questionAnswerText);
    }

    /**
     * Returns true if the given answer is correct otherwise false.
     * @param answerNumber the correct answers index which can be 1 or 2.
     * @param questionNumber the index of the question.
     * @return true if the given answer is correct otherwise false.
     */
    public boolean isQuestionAnswerCorrect(int answerNumber, int questionNumber) {

        boolean isCorrect = false;
        String answerState = "";
        /**
         *  Access to question list
         */
        nodeLst = doc.getElementsByTagName("question");

        /** Read <question> node with index "i"
         *
         */
        fstNode = nodeLst.item(questionNumber - 1);
        /** Read the <answer> tag
         *
         */
        questionNode = (Element) fstNode;
        answerElementList = questionNode.getElementsByTagName("answer");
        answerElementOne = (Element) answerElementList.item(answerNumber - 1);
        answerAttr = (Attr) answerElementOne.getAttributes().item(0);
        //CHECKSTYLE:OFF
        if (answerAttr != null) {
            answerAttrValue = answerAttr.getChildNodes();
            answerState = answerAttrValue.item(0).getNodeValue();
            if (answerState.trim().compareTo("true") == 0) {
                isCorrect = true;
            } else {
                isCorrect = false;
            }
        } else {
            System.out.println("Unknown state: true or false?");
        }
        //CHECKSTYLE:ON
        return (isCorrect);
    }

}
