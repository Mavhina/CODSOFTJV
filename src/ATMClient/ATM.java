/**
 * 
 */
package ATMClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author mavhinamulisa
 * @version Task 3
 */
public class ATM extends GridPane{
	private Label lblAccHolder;
	private Label lblAccountNo;
	
	private Label lblUserName;
	private Label lblUserAcc;
	
	private Label lblAmount;
	private TextField txtAmount;
	private Button btnConfirm;
	
	private TextArea RespondArea;
	
	private Label lblTransactionType;
	private ComboBox<String> transactionTypeComboBox;
	
	//instance of BankAccount
    private BankAccount userAccount;
	
	
    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        
        //Initializing the ATM GUI.
        setGUI();
        
        //Add an event handler for the Confirm button
        btnConfirm.setOnAction(e -> {
            String selectedTransaction = transactionTypeComboBox.getValue();
            double amount = 0;
           
            if (selectedTransaction.equals("Withdraw") || selectedTransaction.equals("Deposit")) {
                try {
                    amount = Double.parseDouble(txtAmount.getText());
                } catch (NumberFormatException ex) {
                    amount = 0; // Set to 0 if parsing fails
                }
            }

            //Perform actions based on the selected transaction type
            switch (selectedTransaction) {
                case "Deposit":
                    userAccount.deposit(amount);
                    break;
                case "Withdraw":
                    userAccount.withdraw(amount);
                    break;
                case "Balance Inquiry":
                    userAccount.checkBalance();
                    break;
                default:
                	userAccount.getTransactionHistory().append("Invalid transaction type\n");
            }
            
            RespondArea.setText(userAccount.getTransactionHistory().toString());
            txtAmount.clear(); // Clear the amount TextField
            userAccount.clearTransactionHistory(); // clear the TextArea
        });
    }
	
    //Set up the GUI for ATM INTERFACE
	private void setGUI()
	{
		setVgap(10);
		setHgap(10);
		setAlignment(Pos.CENTER);
		
		lblAccHolder = new Label("Account Holder's Name:");
		add(lblAccHolder, 0,0);
		lblUserName = new Label(userAccount.getAccountHolderName());
		add(lblUserName,1,0);
		
		lblAccountNo = new Label("Account Number:");
		add(lblAccountNo, 0,1); //cols = 0 rows = 1
		lblUserAcc = new Label(userAccount.getAccountNumber());
		add(lblUserAcc,1,1);
		
		lblTransactionType = new Label("Transaction Type:");
		add(lblTransactionType, 0, 2);
		
		ObservableList<String> transactionTypes = FXCollections.observableArrayList(
                "Deposit", "Withdraw", "Balance Inquiry"
        );
		
		transactionTypeComboBox = new ComboBox<>(transactionTypes);
        add(transactionTypeComboBox, 1, 2);
        
        lblAmount = new Label("Enter Amount: R");
        add(lblAmount,0,3);
        txtAmount = new TextField();
        add(txtAmount,1,3);
        
        btnConfirm = new Button("Confirm");
        add(btnConfirm,1,4);
		
		RespondArea = new TextArea();
		RespondArea.setPrefHeight(100);
		add(RespondArea, 0, 5, 2,1);
		
	}

}
