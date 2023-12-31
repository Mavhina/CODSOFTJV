import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

/**
 * 
 * @author mavhinamulisa
 * @version TASK 1
 */
public class Game {
    private int targetNumber;
    private int maxAttempts = 10;
    private int attemptsLeft;
    private int rounds = 0;
    private int score = 0;

    private TextField guessField;
    private Button guessButton;
    private Label lblResult;
    private Label lblAttempts;
    private Label lblScore;
    private Stage primaryStage;

    /**
     * Constructor
     * @param primaryStage
     */
    public Game(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getStartScene() {
        Button btnStart = new Button("Start Game");
        Label lblInstruction = new Label("Enter your guess:");

        guessField = new TextField();
        guessButton = new Button("Submit Guess");

        lblResult = new Label();
        lblAttempts = new Label();
        lblScore = new Label();

        btnStart.setOnAction(e -> {
            startNewGame();
            primaryStage.setScene(getGameScene());
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(btnStart, lblInstruction, guessField, guessButton, lblResult, lblAttempts, lblScore);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        return new Scene(layout, 300, 250);
    }

    private Scene getGameScene() {
        guessButton.setOnAction(e -> {
            handleGuess(Integer.parseInt(guessField.getText()));
            guessField.clear();
        });

        return new Scene(new VBox(10, guessField, guessButton, lblResult, lblAttempts, lblScore), 400, 250);
    }

    //To generate a random number
    private void startNewGame() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
        attemptsLeft = maxAttempts;
        rounds++;
    }

    //It will compare the number and generate too low or too high based on the number provided
    private void handleGuess(int userGuess) {
        if (userGuess == targetNumber) {
        	lblResult.setText("Congratulations! You guessed the number!");
            score += attemptsLeft;
            lblScore.setText("Score: " + (score * 10) + " out of 100"); // the score will be out of 100
            displayEndOfRoundDialog();
        } else {
            attemptsLeft--;
            if (attemptsLeft > 0) {
                String feedback = (userGuess < targetNumber) ? "Too low!" : "Too high!";
                lblResult.setText(feedback);
                lblAttempts.setText("Attempts left: " + attemptsLeft);
            } else {
            	lblResult.setText("Sorry, you're out of attempts. The correct number was " + targetNumber);
                displayEndOfRoundDialog();
            }
        }
    }

    private void displayEndOfRoundDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End of Round");
        alert.setHeaderText("Round " + rounds + " completed!");
        alert.setContentText("Do you want to play again?");

        ButtonType playAgainButton = new ButtonType("Play Again");
        ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(playAgainButton, exitButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == playAgainButton) {
                startNewGame();
                primaryStage.setScene(getGameScene());
            } else {
                System.exit(0);
            }
        });
    }
}
