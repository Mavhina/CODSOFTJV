import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quizapp.QuizApplication;

/**
 * 
 * @author mavhinamulisa
 * @version Task 4
 * QUIZ APPLICATION WITH TIMER
 */

public class Main extends Application {
	
	private VBox root;
	private Label lblQuestion;
	private ToggleGroup optionsGroup;
	private VBox optionsBox;
	private Button btnSubmit;
	private Label lblResult;
	private ProgressBar countdownProgressBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        QuizApplication QA = new QuizApplication();

        //Set up UI components
        setGUI();

        //Load the first question
        QA.showQuestions(lblQuestion, optionsBox, optionsGroup, lblResult, countdownProgressBar);

        //Submit button action
        btnSubmit.setOnAction(event -> QA.submitAnswer(optionsGroup, lblResult));

        //Set up the stage
        Scene sc = new Scene(root, 450, 400);
        primaryStage.setTitle("Quiz App");
        primaryStage.setScene(sc);
        primaryStage.show();
    }
    
    //Set up UI components
    private void setGUI()
    {
        root = new VBox(10);
        root.setPadding(new Insets(10));

        lblQuestion = new Label();
        optionsGroup = new ToggleGroup();
        optionsBox = new VBox(5);

        countdownProgressBar = new ProgressBar();
        countdownProgressBar.setPrefSize(30, 30);
        countdownProgressBar.setProgress(1.0); 

        VBox timerBox = new VBox(countdownProgressBar);
        timerBox.setPadding(new Insets(10));

        btnSubmit = new Button("Submit");
        lblResult = new Label();

        root.getChildren().addAll(lblQuestion, optionsBox, timerBox, btnSubmit, lblResult);
    	
    }
}