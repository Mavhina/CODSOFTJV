import ATMClient.ATM;
import ATMClient.BankAccount;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author mavhinamulisa
 *
 */
public class Main extends Application{
	
	private ATM pane = null;
	private BankAccount bank = null;

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
		bank = new BankAccount("", "", 0);
		pane = new ATM(bank);
		Scene sc = new Scene(pane);
		primaryStage.setTitle("Mavhina Bank ATM");
		primaryStage.setWidth(600);
		primaryStage.setHeight(400);
		primaryStage.setScene(sc);
		primaryStage.show();
		
	}

}
