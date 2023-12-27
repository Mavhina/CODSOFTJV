import StudentInfo.Student;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author mavhinamulisa
 * @version Task02 STUDENT GRADE CALCULATOR
 */
public class Main extends Application{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Student student = new Student();
		//set the Scene
		Scene sc = new Scene(student, 400, 300);

        primaryStage.setTitle("Grade Calculator");
        primaryStage.setScene(sc);
        primaryStage.show();	
	}

}
