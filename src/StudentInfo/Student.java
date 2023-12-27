package StudentInfo;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
/**
 * 
 * @author mavhinamulisa
 * @version Task02 STUDENT GRADE CALCULATOR
 */
public class Student extends GridPane {

    private Label lblMarks;
    private TextField txtMarks;
    private Button btnCalculate;
    private int numSubjects;

    //A constructor
    public Student() {
    	
    	//To set up the user interface
        setGUI();
       
        //For functionality of the calculate button
        btnCalculate.setOnAction(e -> calculateResults());

        add(btnCalculate, 0, numSubjects);
    }

    //To setup the GUI for user interaction
    private void setGUI() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));

        //To get the number of Subjects from the student
        TextInputDialog options = new TextInputDialog();
        options.setHeaderText("Enter the number of subjects enrolled for");
        options.setContentText("Number of Subjects");
        numSubjects = Integer.parseInt(options.showAndWait().orElse("0"));

        //To loop around the number of subjects given by the user and generate the label and TextField
        for (int i = 0; i < numSubjects; i++) {
            lblMarks = new Label("Subject " + (i + 1) + " Marks");
            txtMarks = new TextField();
            addRow(i, lblMarks, txtMarks);
        }

        btnCalculate = new Button("Calculate Results");
  
    }

    /**
     * To calculate and display all the results
     */
    private void calculateResults() {
        int totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) {
            TextField textField = (TextField) getChildren().get(i * 2 + 1);
            
            //Total Marks calculation
            totalMarks += Integer.parseInt(textField.getText());
        }

        //Average percentage calculations
        double averagePercentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results");
        alert.setHeaderText("Results:");
        alert.setContentText("Total Marks: " + totalMarks + "\n"
                + "Average Percentage: " + averagePercentage + "%\n"
                + "Grade: " + grade);
        alert.showAndWait();
    }

    //Grading based on South Africa education system
    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
