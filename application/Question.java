/**
 * Filename:   Question.java
 * Project:    Quiz Generator
 * Authors:    Aaron Zhang, Aurora Shen, Tyler Gu, Yixing Tu
 * Group:      A-Team 68
 * 
 * Question class presents a single question object.
 * 
 */

package application;

public class Question {
  public String questionText;
  public String imageFile;
  public String[] choiceArray;
  public String topic;
  private String correctAnswer;

  // Constructor without image input
  public Question(String questionText, String[] choiceArray, String topic, String answer) {
    this.questionText = questionText;
    this.choiceArray = choiceArray;
    this.topic = topic;
    this.correctAnswer = answer;
  }

  // Constructor with image input
  public Question(String questionText, String[] choiceArray, String imagePath, String topic,
      String answer) {
    this.questionText = questionText;
    this.choiceArray = choiceArray;
    this.topic = topic;
    this.imageFile = imagePath;
    this.correctAnswer = answer;
  }

  public String getQuestion() {
    return questionText;
  }

  // Return image path
  public String getImage() {
    return imageFile;
  }

  public String getTopic() {
    return topic;
  }

  public String[] getChoices() {
    return choiceArray;
  }
  
  public String getCorrect() {
    return correctAnswer;
  }

}
