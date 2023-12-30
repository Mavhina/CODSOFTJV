package quizapp;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author mavhinamulisa
 * @version Task 4
 * QUIZ APPLICATION WITH TIMER
 */

public class QuizApplication {

    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<QuizQuestions> questions = new ArrayList<>();
    private List<Boolean> isCorrectAnswers = new ArrayList<>(); // Store whether each answer was correct
    private Timer timer;
    private Label lblQuestion;
    private VBox optionsBox;
    private ProgressBar countdownProgressBar;

    //Constructor
    public QuizApplication() {
        //Initialize quiz questions
    	getQuestions();
    }

    public void showQuestions(Label lblQuestion, VBox optionsBox, ToggleGroup optionsGroup, Label lblResult, ProgressBar countdownProgressBar) {
        this.lblQuestion = lblQuestion;
        this.optionsBox = optionsBox;
        this.countdownProgressBar = countdownProgressBar;

        if (currentQuestionIndex < questions.size()) {
            QuizQuestions currentQuestion = questions.get(currentQuestionIndex);

            lblQuestion.setText(currentQuestion.getQuestion());
            optionsBox.getChildren().clear();

            for (String option : currentQuestion.getOptions()) {
                RadioButton radioButton = new RadioButton(option);
                radioButton.setToggleGroup(optionsGroup);
                optionsBox.getChildren().add(radioButton);
            }

            toStartTimer(optionsGroup, lblResult);
        } else {
        	//If there are no more questions, show the final result
            showSummary(lblResult);
        }
    }

    /**
     * submitAnswer that return nothing
     * @param optionsGroup
     * @param lblResult
     */
    public void submitAnswer(ToggleGroup optionsGroup, Label lblResult) {
    	// Cancel the timer when the answer is submitted and restart it if there is still more questions
        timer.cancel(); 

        RadioButton btnSelectedRadio = (RadioButton) optionsGroup.getSelectedToggle();

        if (btnSelectedRadio != null) {
            String selectedAnswer = btnSelectedRadio.getText();
            QuizQuestions currentQuestion = questions.get(currentQuestionIndex);

            boolean isCorrect = currentQuestion.isCorrectAnswer(selectedAnswer);
            isCorrectAnswers.add(isCorrect);

            if (isCorrect) {
                score++;
            }

            // Move to the next question
            moveToNextQuestion(optionsGroup, lblResult);
        }
    }

    private void toStartTimer(ToggleGroup optionsGroup, Label lblResult) {
        int timeLimitInSeconds = 10; // Set to 10 seconds for automatic transition

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            double progress = 1.0;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    countdownProgressBar.setProgress(progress);
                    progress -= 1.0 / (timeLimitInSeconds * 10); // Update progress
                });

                if (progress <= 0.0) {
                    Platform.runLater(() -> {
                    	moveToNextQuestion(optionsGroup, lblResult);
                    });
                    timer.cancel(); //Stop the timer when the countdown is complete
                }
            }
        }, 0, 100); //To update every 100 milliseconds
    }

    private void moveToNextQuestion(ToggleGroup optionsGroup, Label lblResult) {
        // If the timer is still active, cancel it
        if (timer != null) {
            timer.cancel();
        }

        //Move to the next question
        currentQuestionIndex++;

        //Load the next question only if there are more questions
        if (currentQuestionIndex < questions.size()) {
        	showQuestions(lblQuestion, optionsBox, optionsGroup, lblResult, countdownProgressBar);
        } else {
            // If there are no more questions, show the final result
            showSummary(lblResult);
        }
    }

    private void showSummary(Label lblResult) {
    	lblResult.setText("Quiz completed!\nYour score: " + score + "/" + questions.size() + "\n\nSummary of Correct Answers:");

        for (int i = 0; i < isCorrectAnswers.size(); i++) {
        	lblResult.setText(lblResult.getText() + "\nQuestion " + (i + 1) + ": " + (isCorrectAnswers.get(i) ? "Correct" : "Incorrect"));
        }

        lblResult.setVisible(true);
    }

    private void getQuestions() {
        questions.add(new QuizQuestions("What does the acronym 'HTML' stand for?", List.of("Hyper Text Markup Language", "Highly Typed Machine Learning", "Home Tool Markup Language", "Hyperlink and Text Markup Language"), "Hyper Text Markup Language"));
        questions.add(new QuizQuestions("Which programming language is often used for building web applications?", List.of("Java", "Python", "JavaScript", "C++"), "JavaScript"));
        questions.add(new QuizQuestions("What does 'CSS' stand for in web development?", List.of("Counter Strike: Source", "Cascading Style Sheets", "Computer Style System", "Creative Style Selector"), "Cascading Style Sheets"));
        questions.add(new QuizQuestions("In Java, what is the purpose of the 'final' keyword?", List.of("To indicate that a method should be final", "To declare a constant", "To define a final class", "To specify the final parameter in a method"), "To declare a constant"));
        questions.add(new QuizQuestions("What is the result of the expression '5 + 3 * 2' in most programming languages?", List.of("16", "11", "26", "13"), "11"));
    }
}