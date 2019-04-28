/**
 * Filename: Quiz.java Project: Quiz Generator Authors: Aaron Zhang, Aurora Shen, Tyler Gu, Yixing
 * Tu Group: A-Team 68
 * 
 * Quiz class represents a created quiz. It stores selected questions.
 * 
 */

package application;

import java.util.List;

public class Quiz {

  List<Question> quizQuestion;
  double score;

  Quiz(List<Question> questions) {
    this.quizQuestion = questions;
    this.score = questions.size();
  }

  public List<Question> getQuizQuestion() {
    return quizQuestion;
  }

  // Set score 1 point off.
  public void pointDeduction() {
    score--;
  }

  // Convert score to percentage and return
  public double getScore() {
    return (score / quizQuestion.size()) * 100;
  }

}
