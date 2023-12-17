import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// codSoft: JAVA DEVELOPMENT by Kevin Bou Merheb
// TASK 4: QUIZ APPLICATION WITH TIMER  

class Question {
    private String question;
    private ArrayList<String> options;
    private char correctAnswer;

    public Question(String question, ArrayList<String> options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizApp {
    private ArrayList<Question> quizQuestions;
    private int userScore;
    private final Scanner scanner = new Scanner(System.in);
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public QuizApp() {
        quizQuestions = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        ArrayList<String> options1 = new ArrayList<>();
        options1.add("A. 1");
        options1.add("B. 2");
        options1.add("C. 4");
        options1.add("D. 8");

        quizQuestions.add(new Question("What is 2 + 2?", options1, 'C'));

        ArrayList<String> options2 = new ArrayList<>();
        options2.add("A. Paris");
        options2.add("B. Berlin");
        options2.add("C. Moscow");
        options2.add("D. Beirut");

        quizQuestions.add(new Question("Which is the capital of Lebanon?", options2, 'D'));
    }

    public void displayQuestion(int questionNumber) {
        Question currentQuestion = quizQuestions.get(questionNumber);
        System.out.println("Question " + (questionNumber + 1) + ": " + currentQuestion.getQuestion());

        ArrayList<String> options = currentQuestion.getOptions();
        for (String option : options) {
            System.out.println(option);
        }
    }

    public void submitAnswer(int questionNumber, char userAnswer) {
        Question currentQuestion = quizQuestions.get(questionNumber);
        if (userAnswer == currentQuestion.getCorrectAnswer()) {
            userScore++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }
    }
    

    public void displayResult() {
        System.out.println("Quiz ended! Your Final Score: " + userScore);
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        for (int i = 0; i < quiz.quizQuestions.size(); i++) {
            quiz.displayQuestion(i);
            System.out.print("Enter your answer (A, B, C, D): ");

            char userAnswer = quiz.getUserInputWithTimeout(10); 
            if (userAnswer == '\0') {
                System.out.println("Time's up! Moving to the next question.");
                continue;
            }

            quiz.submitAnswer(i, userAnswer);
        }
        quiz.displayResult();
        quiz.scanner.close();
        quiz.executorService.shutdown();
    }

    public char getUserInputWithTimeout(int timeoutSeconds) {
        Future<Character> future = executorService.submit(() -> {
            try {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine().toUpperCase();
                    if (!input.isEmpty()) {
                        return input.charAt(0);
                    }
                }
            } catch (Exception ignored) {
            }
            return '\0'; 
        });

        try {
            return future.get(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true); 
            return '\0';
        }
    }
}

