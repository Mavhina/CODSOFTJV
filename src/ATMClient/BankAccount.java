package ATMClient;

/**
 * @author mavhinamulisa
 * @version Task 3
 * A class that represents the user's bank account. which stores the account information
 */
public class BankAccount {
	
	private String accountHolderName;
    private String accountNumber;
    private double balance;
    private StringBuilder transactionHistory = new StringBuilder();

    //The Constructor
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    //Setting the Getter methods
    public String getAccountHolderName() {
    	accountHolderName = "MR M MAVHINA";
        return accountHolderName;
    }

    public String getAccountNumber() {
    	accountNumber = "144 792 1234";
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    
    //Method to deposit money into the User's bank account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposit of R" + amount + " successful.\nNew balance: R" + balance + "\n");
        } else {
        	transactionHistory.append("Invalid deposit amount. Amount must be greater than 0.\n");
        }
    }

    //Method to withdraw money from the User's bank account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrawal of R" + amount + " successful.\nNew balance: R" + balance + "\n");
        } else {
        	transactionHistory.append("Invalid withdrawal amount or insufficient funds.\nAvailable Balance Is: R" + balance+ "\n");
        }
    }

    //Method to check the User's bank account balance
    public void checkBalance() {
    	transactionHistory.append("Available Balance: R" + balance + "\n");
    }
    
    //Method to clear the TextArea 
    //It will set the length of the StringBuilder to 0, effectively clearing it
    public void clearTransactionHistory() {
        transactionHistory.setLength(0);
    }
    
    //Getter method for transaction history
    public StringBuilder getTransactionHistory() {
        return transactionHistory;
    }

}
