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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import java.util.*;

//TODO Make each screen to be a single method
//TODO Remove for loops inside of screen setup function 
// to give each component different name and corresponding event handler

public class UserInterface extends Application {
  private HashMap<String, BorderPane> screenMap = new HashMap<>();
  private Scene main; // Scene to display different panes
  BorderPane root; // the main menu
  private int loadNum = 0; //

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
  public HBox addButtonForRootScreen() {
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
        activate("load1");
        setupScreens("load1");
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
   * 
   * @return
   */
  public HBox addButtonForSaveScreen() {
    HBox hbox = new HBox();
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    hbox.getChildren().addAll(save, cancel);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setSpacing(10);

    save.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        // TODO save info to file
        main.setRoot(root);
        System.out.println("Saved successfully");
      }
    });

    cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        main.setRoot(root);
        System.out.println("Go back to root with out any action");
      }
    });

    return hbox;
  }

  /**
   * This method initializes all screens that we need
   */
  public void initializeScreens() {
    String[] screenNames = {"add", "load1", "load2", "next", "save"};
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

  public void generateQuiz() {
    // TODO generate quizs based on user's choice
  }

  public void showResult() {
    // TODO show score. Will be called once user submits or running out of questions
  }

  public void setupScreens(String name) {
    VBox vbox;
    HBox hbox;
    Text text;
    Insets insets = new Insets(10);
    TextField fileName;
    switch (name) {
      case "add":
        vbox = new VBox();
        TextField textField;
        // Set the text at the top
        text = new Text("Add new question");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        screenMap.get(name).setTop(text);

        // initialize a HBox for text of the question
        // and add to the vbox
        hbox = new HBox();
        textField = new TextField();
        textField.setPromptText("Type in question");
        hbox.getChildren().addAll(new Text("Text: "), textField);
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER); // align to the center
        // initialize a new HBox for topic of the question
        hbox = new HBox();
        textField = new TextField();
        textField.setPromptText("Type in topic");
        hbox.getChildren().addAll(new Text("Topic: "), textField);
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER);
        // initialize a new HBox for Image file name of the question
        hbox = new HBox();
        textField = new TextField();
        textField.setPromptText("Type in image name");
        hbox.getChildren().addAll(new Text("Image: "), textField);
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER);
        // initialize a new HBox for texts
        hbox = new HBox();
        hbox.getChildren().add(new Text("Choices: "));
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER);

        // toggle group of radio buttons so that only one selection can be chosen
        ToggleGroup group = new ToggleGroup();
        RadioButton button = new RadioButton();
        button.setToggleGroup(group);
        button.setSelected(true);
        hbox = new HBox();
        textField = new TextField();
        textField.setPromptText("Choice 1");
        hbox.getChildren().addAll(button, textField);
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER);
        for (int i = 1; i < 5; i++) {
          hbox = new HBox();
          button = new RadioButton();
          button.setToggleGroup(group);
          textField = new TextField();
          textField.setPromptText("Choice " + (i + 1));
          hbox.getChildren().addAll(button, textField);
          vbox.getChildren().add(hbox);
          hbox.setAlignment(Pos.CENTER);
        }
        vbox.setSpacing(10);

        Button saveButton = new Button("Add");
        Button cancelButton = new Button("Cancel");
        hbox = new HBox();
        hbox.getChildren().addAll(saveButton, cancelButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        screenMap.get(name).setBottom(hbox);
        screenMap.get(name).setAlignment(saveButton, Pos.CENTER_RIGHT);
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent me) {
            main.setRoot(root);
            // TODO add new question
            System.out.println("Go back to root");
          }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent me) {
            main.setRoot(root);
            System.out.println("Go back to root");
          }
        });

        screenMap.get(name).setCenter(vbox);
        screenMap.get(name).setAlignment(vbox, Pos.CENTER);
        break;
      case "load1":
        vbox = new VBox();
        BorderPane currScreen = screenMap.get(name);
        text = new Text("Load question");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        currScreen.setTop(text);

        ObservableList<String> topics =
            FXCollections.observableArrayList("Topic 1", "Topic 2", "Topic 3");
        final ComboBox topicComboBox = new ComboBox(topics);
        topicComboBox.getSelectionModel().selectFirst();
        hbox = new HBox(); // hbox for topic prompt
        hbox.getChildren().addAll(new Text("Topic: "), topicComboBox);
        HBox numberQuestionHBox = new HBox(); // hbox for number of question prompt
        numberQuestionHBox.getChildren().addAll(new Text("# of Questions: "), new TextField());
        hbox.setAlignment(Pos.CENTER);
        numberQuestionHBox.setAlignment(Pos.CENTER);


        vbox.getChildren().add(hbox);
        vbox.getChildren().add(numberQuestionHBox);
        hbox = new HBox();
        fileName = new TextField();
        fileName.setPromptText("Enter the file name");
        hbox.getChildren().addAll(new Text("Question file: "), fileName);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);

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
            setupScreens("load2");
            activate("load2");
            System.out.println("Test");
          }
        });

        buttons.getChildren().addAll(backButton, loadButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setSpacing(10);
        currScreen.setBottom(buttons);
        currScreen.setAlignment(buttons, Pos.CENTER_RIGHT);

        break;
      case "load2":
        vbox = new VBox();
        BorderPane currentScreen = screenMap.get(name);
        text = new Text("Quiz");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        currentScreen.setTop(text);
        hbox = new HBox();
        vbox.getChildren().add(new Text(
            "Question: (We will show question here. \nThe space below is for image. It will show image if there is.)"));
        // hbox.getChildren().addAll(new Text("Question Text: "), questionLabel);
        // currentScreen.setCenter(questionLabel);
        // the question image display
        // Image questionImage = new Image("question.jpg");
        ImageView myimage = new ImageView();
        myimage.setFitHeight(200);
        myimage.setFitWidth(400);
        vbox.getChildren().add(myimage);
        // currentScreen.setCenter(myimage);
        // the choice display
        ToggleGroup answergroup = new ToggleGroup();
        RadioButton answerbutton = new RadioButton();
        answerbutton.setToggleGroup(answergroup);
        answerbutton.setSelected(true);
        hbox = new HBox();
        hbox.getChildren().addAll(answerbutton, new Text("Choice 1"));
        vbox.getChildren().add(hbox);
        hbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < 4; i++) {
          hbox = new HBox();
          button = new RadioButton();
          button.setToggleGroup(answergroup);
          hbox.getChildren().addAll(button, new Text("Choice " + (i+2)));
          vbox.getChildren().add(hbox);
          hbox.setAlignment(Pos.CENTER);
        }
        vbox.setSpacing(10);

        currentScreen.setCenter(vbox);

        hbox = new HBox();
        // the submit button
        Button submit = new Button("Submit");
        Button next = new Button("Next");
        hbox.getChildren().addAll(submit, next);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        currentScreen.setBottom(hbox);
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
            setupScreens("next");
            activate("next");
          }
        });
        break;
      case "next":
        // TODO goes to final result only if run out of questions.
        text = new Text("Result");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        screenMap.get(name).setTop(text);
        hbox = new HBox();
        hbox.getChildren().addAll(new Text("Score:  "), new Text(
            "Will display score. According to our design, this screen will be activated \nif running out of questions or clicking on submit. \nFor now, we haven't set it up yet"));
        screenMap.get(name).setCenter(hbox);
        screenMap.get(name).setAlignment(hbox, Pos.CENTER);
        screenMap.get(name).setMargin(hbox, insets);

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
        screenMap.get(name).setBottom(resultChoice);
        resultChoice.setSpacing(10);

        break;
      case "save":
        vbox = new VBox();
        fileName = new TextField();
        fileName.setPromptText("Enter a valid file name");
        vbox.getChildren().addAll(new Text("Filename:"), fileName);
        text = new Text("Save");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        screenMap.get(name).setTop(text);
        screenMap.get(name).setCenter(vbox);
        screenMap.get(name).setBottom(this.addButtonForSaveScreen());
        break;
    }
    screenMap.get(name).setMargin(screenMap.get(name).getTop(), insets);
    screenMap.get(name).setMargin(screenMap.get(name).getCenter(), insets);
    screenMap.get(name).setMargin(screenMap.get(name).getBottom(), insets);
  }

  public void addScreen(String name) {
    BorderPane pane = new BorderPane();
    screenMap.put(name, pane);
  }

  protected void removeScreen(String name) {
    screenMap.remove(name);
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
      root.setCenter(this.addButtonForRootScreen());

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
