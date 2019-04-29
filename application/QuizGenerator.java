/**
 * Filename: QuizGenerator.java 
 * Project: Quiz Generator 
 * Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu 
 * Group: A-Team 68
 *
 * QuizGenerator class is the main driver class to generate a quiz with given number of questions
 * and topic. It reads questions from JSON file and can add new question to JSON file as well.
 *
 */

package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuizGenerator {

  private Quiz quiz;  // current quiz
  private ArrayList<Question> questionBank;  // all the questions that we have
  private Set<String> topicList;  // set of topics in question bank
  
  /**
   * non-param constructor
   */
  QuizGenerator(){
    questionBank = new ArrayList<Question>();
    topicList = new HashSet<String>();
  }
  
  // Read in all questions
  public void addQuestionFromFile(String filePath) 
      throws FileNotFoundException, IOException, ParseException {
    
    Object input = new JSONParser().parse(new FileReader(filePath));
    JSONObject jo = (JSONObject) input; // Typecast
    // Get question array
    JSONArray ja = (JSONArray) jo.get("questionArray");
    // Iterate JSONArray
    Iterator itr = ja.iterator();
    Question q;

    while (itr.hasNext()) {
      JSONObject obj = (JSONObject) itr.next();
      String questionText = (String) obj.get("name");
      String topic = (String) obj.get("topic");
      String imagePath = (String) obj.get("image");
      // Read through choices
      JSONArray choiceArray = (JSONArray) obj.get("choiceArray");
      Iterator itr2 = choiceArray.iterator();
      String[] choices = new String[5];
      String correctAnswer = "";  // correct answer is set to empty initially
      int index = 0;
      for (int i = 0; i < choiceArray.size(); i++) {
        JSONObject currChoice = (JSONObject) choiceArray.get(i);
        choices[i] = (String) currChoice.get("choice");
        if (((String) currChoice.get("isCorrect")).equals("T")) {
          correctAnswer = choices[index];
        }
      }
      if (imagePath.equals("none")) {
        // call corresponding question constructor
        q = new Question(questionText, choices, topic, correctAnswer);
      } else {
        // call corresponding question constructor
        q = new Question(questionText, choices, imagePath, topic, correctAnswer);
      }
      topicList.add(topic);
      questionBank.add(q);
    }
  }

  // Create a Quiz object with questions with selected topic and given amount
  public void generateQuiz(String topic, int amount) {
    ArrayList<Question> quizQuestions = new ArrayList<>();
    for (Question question : questionBank) {
      if (question.getTopic().equals(topic)) {
        quizQuestions.add(question);
      }
    }
    Collections.shuffle(quizQuestions);
    
    quiz = new Quiz(quizQuestions.subList(0, amount));
  }

  /**
   * convert current quiz bank to a json file and save it to a specific filePath
   * 
   * @param filePath
   */
  public void save(String filePath) throws IOException {
    JSONObject quiz = new JSONObject(); // the entire quiz JSONObject
    JSONArray questionArray = new JSONArray();

    // generate each question to JSONObject and add to question array
    for (Question question : questionBank) {
      JSONObject jsonQuestion = new JSONObject();
      jsonQuestion.put("questionText", question.getQuestion());
      jsonQuestion.put("topic", question.getTopic());
      jsonQuestion.put("image", question.getImage());
      JSONArray choiceArray = new JSONArray();

      // create choice array for this question
      for (String choice : question.getChoices()) {
        JSONObject choiceComb = new JSONObject();
        if (choice.equals(question.getCorrect())) {
          choiceComb.put("isCorrect", "T");
        } else {
          choiceComb.put("isCorrect", "F");
        }
        choiceComb.put("choice", choice);
      }
      jsonQuestion.put("choiceArray", choiceArray);
      questionArray.add(jsonQuestion); // add this question to JSONArray
    }

    quiz.put("questionArray", questionArray); // add question array to the quiz object
    FileWriter outFile = new FileWriter(filePath); // open file write according to the parameter
    outFile.write(quiz.toJSONString());
    outFile.flush();
  }

  /**
   * add a new question to the question bank
   * @param newQuestion
   */
  public void addNewQuestion(Question newQuestion) {
    questionBank.add(newQuestion);
  }

  /**
   * getter for quiz
   * @return
   */
  public Quiz getQuiz() {
    return quiz;
  }
  
  /**
   * getter for topic list
   * @return
   */
  public Set<String> getTopicList() {
    return topicList;
  }
  
  /**
   * This method returns number of questions in a
   * specific topic
   * @param topic is the topic that we want to find
   * @return number of questions
   */
  public int getNumberOfQuestionsInTopic(String topic) {
    if (!topicList.contains(topic)) {
      return 0;  // if topic is not in the set, simply return 0
    }
    else {
      int number = 0;
      for (Question question : questionBank) {
        if (question.getTopic().equals(topic)) {
          number++;
        }
      }
      return number;
    }
  }
  
  /**
   * getter for question bank
   * @return
   */
  public List<Question> getQuestionBank() {
    return questionBank;
  }
  
  public double getScore() {
    return quiz.getScore();
  }
}
