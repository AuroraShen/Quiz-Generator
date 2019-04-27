package application;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test to test QuizGenerator class
 * 
 * @author Jiawei Gu
 *
 */
public class QuizGeneratorTest {
  QuizGenerator qg; // quiz generator with json file "question.json"

  /**
   * set up
   * 
   * @throws Exception
   */
  @Before
  public void setup() throws Exception {
    qg = new QuizGenerator();
    qg.addQuestionFromFile("question.json");
  }

  /**
   * tear down
   * 
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    qg = null;
  }

  /**
   * This test checks if QuizGenerator gets the corrct question bank out of json file
   */
  @Test
  public void test00_question_bank_size() {
    try {
      if (qg.questionBank.size() != 2) {
        fail("The size of question bank should be 2, return value is: " + qg.questionBank.size());
      }
    } catch (Exception e) {
      fail("Unexpected exception: " + e.getStackTrace());
    }
  }

  /**
   * This test generates quiz with topic which will result only in quiz with only one question
   * @throws Exception
   */
  @Test
  public void test01_generator_quiz_with_one_question() throws Exception {
    qg.generateQuiz("linux", 1);
    Quiz quiz = qg.quiz;
    if (!quiz.getQuizQuestion().get(0).getTopic().equals("linux")) {
      fail("generate quiz function fails, topic does not match. \n" + "Excepted: linux, return: "
          + quiz.getQuizQuestion().get(0).getTopic());
    }
  }
  
  
}
