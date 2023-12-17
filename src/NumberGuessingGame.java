import java.util.Scanner;
import java.util.Random;

// codSoft: JAVA DEVELOPMENT by Kevin Bou Merheb
// TASK 1: NUMBER GAME

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1; 
        int maxRange = 100;
        int attemptsLimit = 5; 
        int score = 0; 

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("\nI've picked a number between " + minRange + " and " + maxRange + ". Guess it!");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < attemptsLimit) {
                System.out.print("Attempt " + (attempts + 1) + " - Enter your guess: ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); 

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number: " + randomNumber);
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                attempts++;
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The number was: " + randomNumber);
            }

            System.out.print("Your current score: " + score + "\nDo you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine().toLowerCase();

            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }
}
