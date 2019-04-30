/**
 * Filename: UserInterface.java Project: Quiz Generator Authors: Aaron Zhang, Aurora Shen, Tyler Gu,
 * Yixing Tu Group: A-Team 68
 * 
 * UserInterface class is the main GUI class for this project.
 * 
 */

package application;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.simple.parser.ParseException;

// TODO Make each screen to be a single method
// TODO Remove for loops inside of screen setup function
// to give each component different name and corresponding event handler

public class UserInterface extends Application {
  QuizGenerator quizGenerator = new QuizGenerator();
  boolean needQuit = false;
  Stage stage;
  private HashMap<String, BorderPane> screenMap = new HashMap<>();
  private Scene main; // Scene to display different panes
  BorderPane root; // the main menu
  Insets insets = new Insets(10);
  private int loadNum = 0; //
  String inputFileName = null;
  int count; // Count how many questions have been answered
  
  /**
   * 
   * @return
   */
  public BorderPane addPane() {
    return null;
  }

  /**
   * This method
   * 
   * @return
   */
  public HBox setUpRootScreen() {
    HBox hbox = new HBox();
    Button addButton = new Button("Add Question"); // add button for "add question" screen
    Button loadButton = new Button("Load Question"); // button for "load question" screen
    Button saveButton = new Button("Save"); // button for "save" screen
    // set preferred size
    addButton.setPrefSize(150, 60);
    loadButton.setPrefSize(150, 60);
    saveButton.setPrefSize(150, 60);
    // add buttons to hbox
    hbox.getChildren().addAll(addButton, loadButton, saveButton);
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(10);

    // EventHandler goes here
    addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("add"); // call activate method to set scene
        setupScreens("add");
        System.out.println("add new question");
      }
    });

    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("beforeLoading");
        setupScreens("beforeLoading");
        System.out.println("load questions");
      }
    });

    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("save"); // call activate method to set scene
        setupScreens("save");
        System.out.println("save questions");
      }
    });

    return hbox;
  }

  /**
   * This method initializes all screens that we need
   */
  public void initializeScreens() {
    String[] screenNames = {"add", "load1", "load2", "next", "save", "exit", "beforeLoading"};
    for (String name : screenNames) {
      this.addScreen(name);
    }
  }

  public void readAddedQuestion() {
    // TODO read information after adding question
  }

  public void readSaveFile() {
    // TODO Save current questions to a file
  }

  public void readLoadFunction() {
    // TODO read topic and number of questions from load1 screen
  }

  public void generateQuiz(String filePath, String topic, int amount)
      throws FileNotFoundException, IOException, ParseException {
    // TODO generate quizs based on user's choice
    try {
      quizGenerator.addQuestionFromFile(filePath);
    } catch (Exception e) {
      System.out.println("Unexpected exception occured");
    }

    quizGenerator.generateQuiz(topic, amount);
  }

  public double getResult() {
    // TODO show score. Will be called once user submits or running out of questions
    return quizGenerator.getScore();
  }

  public void setUpAddScreen(BorderPane pane) {
    VBox vbox = new VBox();
    TextField textField;
    // Set the text at the top
    Text text = new Text("Add new question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);

    // initialize a HBox for text of the question
    // and add to the vbox
    HBox hbox = new HBox();
    TextField question = new TextField();  // text field for question
    question.setPromptText("Type in question");
    hbox.getChildren().addAll(new Text("Text: "), question);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER); // align to the center
    // initialize a new HBox for topic of the question
    hbox = new HBox();
    TextField topic = new TextField();  // text field for topic
    topic.setPromptText("Type in topic");
    hbox.getChildren().addAll(new Text("Topic: "), topic);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    // initialize a new HBox for Image file name of the question
    hbox = new HBox();
    TextField image = new TextField();
    image.setPromptText("Type in image name");  // text field for image path
    hbox.getChildren().addAll(new Text("Image: "), image);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    // initialize a new HBox for texts
    hbox = new HBox();
    hbox.getChildren().add(new Text("Choices: "));
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);

    // toggle group of radio buttons so that only one selection can be chosen
    ToggleGroup group = new ToggleGroup();
    // five radio buttons corresponding to five text fields
    RadioButton button1 = new RadioButton();  
    RadioButton button2 = new RadioButton();
    RadioButton button3 = new RadioButton();
    RadioButton button4 = new RadioButton();
    RadioButton button5 = new RadioButton();
    // add buttons into togglegroup
    button1.setToggleGroup(group);
    button2.setToggleGroup(group);
    button3.setToggleGroup(group);
    button4.setToggleGroup(group);
    button5.setToggleGroup(group);
    button1.setSelected(true);
    
    TextField choice1 = new TextField();
    TextField choice2 = new TextField();
    TextField choice3 = new TextField();
    TextField choice4 = new TextField();
    TextField choice5 = new TextField();
    choice1.setPromptText("Choice 1");
    hbox = new HBox();
    hbox.getChildren().setAll(button1, choice1);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice2.setPromptText("Choice 2");
    hbox = new HBox();
    hbox.getChildren().setAll(button2, choice2);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice3.setPromptText("Choice 3");
    hbox = new HBox();
    hbox.getChildren().setAll(button3, choice3);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice4.setPromptText("Choice 4");
    hbox = new HBox();
    hbox.getChildren().setAll(button4, choice4);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    choice5.setPromptText("Choice 5");
    hbox = new HBox();
    hbox.getChildren().setAll(button5, choice5);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);

    Button saveButton = new Button("Add");
    Button cancelButton = new Button("Cancel");
    hbox = new HBox();
    hbox.getChildren().addAll(saveButton, cancelButton);
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.CENTER_RIGHT);

    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
        String[] choiceArray = new String[5];
        String answer;
        choiceArray[0] = choice1.getText();
        choiceArray[1] = choice2.getText();
        choiceArray[2] = choice3.getText();
        choiceArray[3] = choice4.getText();
        choiceArray[4] = choice5.getText();
        
        for (String txt: choiceArray) {
          System.out.println(txt);
          if (txt.isEmpty()) {
            System.out.println("a");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Empty Text Field!");
            alert.setContentText("choices can not be empty");
            activate("add");
            setupScreens("add");
            alert.showAndWait();
            return;
          }
        }
        
        if (question.getText().isEmpty() ||
            topic.getText().isEmpty()) {
          System.out.println("a");
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText("Empty Text Field!");
          alert.setContentText("choices can not be empty");
          activate("add");
          setupScreens("add");
          alert.showAndWait();
          return;
        }
        
        if (image.getText().isEmpty()) {
          image.setText("none");
        }
        // find the correct choice
        if (group.getSelectedToggle().equals(button1)) {
          answer = choice1.getText();
        } else if (group.getSelectedToggle().equals(button2)) {
          answer = choice2.getText();
        } else if (group.getSelectedToggle().equals(button3)) {
          answer = choice3.getText();
        } else if (group.getSelectedToggle().equals(button4)) {
          answer = choice4.getText();
        } else{
          answer = choice5.getText();
        }
        Question newQuestion = new Question(
            question.getText(),
            choiceArray,
            image.getText(),
            topic.getText(),
            answer);
        quizGenerator.addNewQuestion(newQuestion);
      }
    });
    cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
        System.out.println("Go back to root");
      }
    });

    pane.setBottom(hbox);
    pane.setAlignment(saveButton, Pos.CENTER_RIGHT);
    pane.setCenter(vbox);
    pane.setAlignment(vbox, Pos.CENTER);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }

  public void setUpLoad1Screen(BorderPane pane) {
    VBox vbox = new VBox();
    BorderPane currScreen = pane;
    Text text = new Text("Load question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    currScreen.setTop(text);

    Object[] topicArray = quizGenerator.getTopicList().toArray();

    ObservableList<String> topics = FXCollections.observableArrayList((String) topicArray[0]);

    if (topicArray.length > 1) {
      for (int i = 1; i < topicArray.length; i++) {
        topics.add((String) topicArray[i]);
      }
    }

    final ComboBox topicComboBox = new ComboBox(topics);
    topicComboBox.getSelectionModel().selectFirst();
    HBox hbox = new HBox(); // hbox for topic prompt
    hbox.getChildren().addAll(new Text("Topic: "), topicComboBox);
    HBox numberQuestionHBox = new HBox(); // hbox for number of question prompt
    TextField questionNum = new TextField();
    numberQuestionHBox.getChildren().addAll(new Text("# of Questions: "), questionNum);
    // TODO prevent user from selecting invalid amount of questions
    hbox.setAlignment(Pos.CENTER);
    numberQuestionHBox.setAlignment(Pos.CENTER);


    vbox.getChildren().add(hbox);
    vbox.getChildren().add(numberQuestionHBox);

    vbox.setSpacing(10);
    vbox.setAlignment(Pos.CENTER);
    currScreen.setCenter(vbox);

    HBox buttons = new HBox();

    Button backButton = new Button("Cancel");
    backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });

    Button loadButton = new Button("Start");
    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        int amountLimit =
            quizGenerator.getNumberOfQuestionsInTopic((String) topicComboBox.getValue());
        if (Integer.parseInt(questionNum.getText()) <= amountLimit) {
          try {
            generateQuiz(inputFileName, (String) topicComboBox.getValue(), amountLimit);
          } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          setupScreens("load2");
          activate("load2");
          System.out.println("Question loaded");
          count = 0;
        } else {
          questionNum.clear();
          questionNum.setPromptText("Maximum: " + amountLimit);
        }
      }
    });

    buttons.getChildren().addAll(loadButton, backButton);
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.setSpacing(10);
    currScreen.setBottom(buttons);
    currScreen.setAlignment(buttons, Pos.CENTER_RIGHT);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }

  public void setUpLoad2Screen(BorderPane pane) {
    count = 0;
    List<Question> quizQuestion = quizGenerator.getQuiz().getQuizQuestion();


    ToggleGroup answergroup = showQuestion(pane, quizQuestion);
    
    HBox hbox = new HBox();
    // the submit button
    Button submit = new Button("Submit");
    Button next = new Button("Next");
    hbox.getChildren().addAll(submit, next);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    pane.setBottom(hbox);
    hbox.setSpacing(10);

    submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("next");
        activate("next");
      }
    });

    next.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        RadioButton selectedRadioButton = (RadioButton) answergroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        grade(toogleGroupValue, quizQuestion.get(count));
        if (count == quizQuestion.size()-1) {
          setupScreens("next");
          activate("next");
        } else {
          count++;
          showQuestion(pane, quizQuestion);
        }

      }
    });
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }

  private void grade (String choice, Question question) {
    if (choice.equals(question.getCorrect())) {
      System.out.println("correct");
    } else {
      System.out.println("incorrect");
      System.out.println(choice);
      System.out.println(question.getCorrect());
    }
  }

  public void setUpNextScreen(BorderPane pane) {
    // TODO goes to final result only if run out of questions.
    Text text = new Text("Result");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    HBox hbox = new HBox();
    hbox.getChildren().addAll(new Text("Score:  "), new Text(
        "Will display score. According to our design, this screen will be activated \nif running out of questions or clicking on submit. \nFor now, we haven't set it up yet"));
    pane.setCenter(hbox);
    pane.setAlignment(hbox, Pos.CENTER);
    pane.setMargin(hbox, insets);

    HBox resultChoice = new HBox();
    Button changeSetting = new Button("Change setting");
    Button tryAgain = new Button("Try Again");
    Button quit = new Button("Quit");
    resultChoice.getChildren().addAll(changeSetting, tryAgain, quit);

    changeSetting.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("load1");
        activate("load1");
      }
    });

    tryAgain.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("load2");
        activate("load2");
      }
    });

    quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });

    resultChoice.setAlignment(Pos.CENTER_RIGHT);
    pane.setBottom(resultChoice);
    resultChoice.setSpacing(10);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }

  public void setUpSaveScreen(BorderPane pane) {
    HBox hbox = new HBox();
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    hbox.getChildren().addAll(save, cancel);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setSpacing(10);

    pane.setBottom(hbox);

    VBox vbox = new VBox();
    TextField fileName = new TextField();
    fileName.setPromptText("Enter a valid file name");
    vbox.getChildren().addAll(new Text("Filename:"), fileName);
    // TODO set action here
    Text text = new Text("Save");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    pane.setCenter(vbox);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);

    save.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        // TODO save info to file
        try {
          quizGenerator.save(fileName.getText());
        } catch (FileNotFoundException e) {
          System.out.println("File Not Found");
        } catch (Exception e) {
          System.out.println("Unexcepted exception occured in save screen");
        }

        main.setRoot(root);
        System.out.println("Saved successfully");
        if (needQuit) {
          stage.close();
        }
      }
    });

    cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
        System.out.println("Go back to root with out any action");
      }
    });
  }

  public void setUpExitScreen(BorderPane pane) {
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Text text = new Text("Quit");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    pane.setTop(text);
    Text exitMessage = new Text("Would you like to save questions?");
    exitMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    Button saveQuit = new Button("Save");
    Button noSaveQuit = new Button("Don't save");
    Button cancelQuit = new Button("Cancel");
    hbox.getChildren().addAll(saveQuit, noSaveQuit, cancelQuit);
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(10);
    vbox.getChildren().addAll(exitMessage, hbox);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);

    saveQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        setupScreens("save");
        activate("save");
        needQuit = true;
      }
    });

    noSaveQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        stage.close();
      }
    });

    cancelQuit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
      }
    });
    pane.setCenter(vbox);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
  }

  public void setUpBeforeLoadingScreen(BorderPane pane) {
    VBox vbox = new VBox();
    BorderPane currScreen = pane;
    Text text = new Text("Load question");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    currScreen.setTop(text);

    HBox hbox = new HBox();
    TextField fileName = new TextField();
    fileName.setPromptText("e.g. questions_001.json");
    hbox.getChildren().addAll(new Text("Question file: "), fileName);
    hbox.setAlignment(Pos.CENTER);
    currScreen.setCenter(hbox);

    HBox buttons = new HBox();

    Button loadButton = new Button("Load");
    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        inputFileName = fileName.getText();
        try {
          quizGenerator.addQuestionFromFile(inputFileName);
        } catch (IOException | ParseException e) {
          // TODO handle all exceptions properly
          System.out.println("Unexpected exception occured in beforeLoading screen");
        }
        setupScreens("load1");
        activate("load1");
        System.out.println("Test");
      }
    });

    Button cancelButton = new Button("Cancel");
    cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        System.out.println("go back");
        main.setRoot(root);
      }
    });

    buttons.getChildren().addAll(loadButton, cancelButton);
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.setSpacing(10);
    currScreen.setBottom(buttons);
    currScreen.setAlignment(buttons, Pos.CENTER_RIGHT);
    pane.setMargin(pane.getTop(), insets);
    pane.setMargin(pane.getCenter(), insets);
    pane.setMargin(pane.getBottom(), insets);
  }
  
  public void setupScreens(String name) {
    VBox vbox;
    HBox hbox;
    Text text;
    Insets insets = new Insets(10);
    TextField fileName;
    switch (name) {
      case "add":
        this.setUpAddScreen(screenMap.get(name));
        break;
      case "load1":
        this.setUpLoad1Screen(screenMap.get(name));
        break;
      case "load2":
        this.setUpLoad2Screen(screenMap.get(name));
        break;
      case "next":
        this.setUpNextScreen(screenMap.get(name));
        break;
      case "save":
        this.setUpSaveScreen(screenMap.get(name));
        break;
      case "exit":
        this.setUpExitScreen(screenMap.get(name));
        break;
      case "beforeLoading":
        this.setUpBeforeLoadingScreen(screenMap.get(name));
        break;
    }

  }

  public void addScreen(String name) {
    BorderPane pane = new BorderPane();
    screenMap.put(name, pane);
  }

  protected void removeScreen(String name) {
    screenMap.remove(name);
  }
  
  public ToggleGroup showQuestion(BorderPane pane, List<Question> quizQuestion) {
    VBox vbox = new VBox();
    BorderPane currentScreen = pane;
    Text text = new Text("Quiz");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    currentScreen.setTop(text);
    HBox hbox = new HBox();
//    List<Question> questionBank = quizGenerator.getQuestionBank();
//    System.out.println(questionBank.get(count).getQuestion());
    System.out.println("show question at index: "+count);
    Text questionText = new Text(10,20, quizQuestion.get(count).getQuestion());
    questionText.setWrappingWidth(500);
    vbox.getChildren().add(questionText);
    String[] choices = quizQuestion.get(count).getChoices();

    // Image part
    // TODO how does image path look like?
    String imagePath = quizQuestion.get(count).getImage();
    ImageView image;
    if(!imagePath.equals("none")) {
      image = new ImageView(imagePath);
    } else { // leave an empty frame
      image = new ImageView();
    }
    image.setFitHeight(200);
    image.setFitWidth(200);
    vbox.getChildren().add(image);
    // currentScreen.setCenter(myimage);
    // the choice display
    ToggleGroup answergroup = new ToggleGroup();
    RadioButton answerbutton = new RadioButton();
    answerbutton.setToggleGroup(answergroup);
//    hbox = new HBox();
//    hbox.getChildren().addAll(answerbutton, new Text(choices[0]));
//    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    for (int i = 0; i < 5; i++) {
      hbox = new HBox();
      RadioButton button = new RadioButton(choices[i]);
      button.setToggleGroup(answergroup);
      vbox.getChildren().add(button);
    }
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);

    // TODO grade
//    RadioButton selectedRadioButton = (RadioButton) answergroup.getSelectedToggle();
//    String toogleGroupValue = selectedRadioButton.getText();
//    System.out.println(toogleGroupValue);

    currentScreen.setCenter(vbox);
    
    return answergroup;
  }

  /**
   * This method changes scene to desired pane
   * 
   * @param name
   */
  protected void activate(String name) {
    main.setRoot(screenMap.get(name));
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      this.stage = primaryStage;
      primaryStage.setTitle("Quiz Generator");
      root = new BorderPane();
      main = new Scene(root, 600, 600);


      Text title = new Text("Quiz Generator");
      // title.setFont(Font.font("Courier", 26));
      title.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
      root.setTop(title);
      Insets inset = new Insets(30);
      root.setMargin(root.getTop(), inset);
      root.setAlignment(title, Pos.CENTER);
      root.setCenter(this.setUpRootScreen());
      Button exitButton = new Button("Exit");
      exitButton.setPrefSize(150, 60);
      HBox hbox = new HBox();
      hbox.getChildren().add(exitButton);
      hbox.setAlignment(Pos.TOP_CENTER);
      hbox.setPadding(new Insets(30));
      root.setBottom(hbox);
      root.setAlignment(exitButton, Pos.CENTER);

      exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
          activate("exit"); // call activate method to set scene
          setupScreens("exit");
          System.out.println("Exit warning page");
        }
      });

      initializeScreens();

      main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(main);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    launch(args);
  }

}
