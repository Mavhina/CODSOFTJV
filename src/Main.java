import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author mavhinamulisa
 * @version TASK 1
 */

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Guessing Game");

        Game game = new Game(primaryStage);
        Scene sc = game.getStartScene();

        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
