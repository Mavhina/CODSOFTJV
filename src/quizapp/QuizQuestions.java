package quizapp;
import java.util.List;

/**
 * 
 * @author mavhinamulisa
 * @version Task 4
 * QUIZ APPLICATION WITH TIMER
 */


//Representing each question for Quiz application
public class QuizQuestions {

    private String question;
    private List<String> options;
    private String correctAnswer;

    /**
     * Contractor
     * @param question
     * @param options
     * @param correctAnswer
     */
    public QuizQuestions(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    //To access the variables from other classes
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(String selectedAnswer) {
        return correctAnswer.equals(selectedAnswer);
    }
}
