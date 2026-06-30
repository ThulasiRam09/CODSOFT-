import java.util.Random;
import java.util.Scanner;

/**
 * NumberGuessingGame
 * --------------------
 * A simple console-based number guessing game.
 *
 * Rules:
 *  - The computer picks a random number within a user-defined range.
 *  - The player has a limited number of attempts to guess it.
 *  - After every guess, the player is told if it was too high, too low,
 *    or correct.
 *  - The player can play multiple rounds, and a running score is kept
 *    based on how many rounds were won and how many attempts were used.
 */
public class NumberGuessingGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    // Score tracking across rounds
    private static int roundsWon = 0;
    private static int roundsPlayed = 0;
    private static int totalScore = 0;

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        WELCOME TO THE NUMBER GAME       ");
        System.out.println("=========================================");

        boolean playAgain = true;

        while (playAgain) {
            playRound();
            playAgain = askToPlayAgain();
        }

        printFinalScore();
        scanner.close();
        System.out.println("Thanks for playing. Goodbye!");
    }

    /**
     * Handles the setup and execution of a single round of the game.
     */
    private static void playRound() {
        roundsPlayed++;

        int lowerBound = readInt("Enter the lower bound of the range: ");
        int upperBound = readInt("Enter the upper bound of the range: ");

        // Make sure the bounds are valid; swap them if needed
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        int maxAttempts = readInt("Enter the maximum number of attempts allowed: ");
        if (maxAttempts <= 0) {
            maxAttempts = 5; // sensible default if user enters something invalid
            System.out.println("Invalid attempts entered. Defaulting to " + maxAttempts + " attempts.");
        }

        int targetNumber = lowerBound + random.nextInt(upperBound - lowerBound + 1);

        System.out.println("\nI'm thinking of a number between " + lowerBound +
                " and " + upperBound + ". You have " + maxAttempts + " attempts.");

        boolean guessedCorrectly = false;
        int attemptsUsed = 0;

        while (attemptsUsed < maxAttempts && !guessedCorrectly) {
            int guess = readInt("Attempt " + (attemptsUsed + 1) + "/" + maxAttempts + " - Enter your guess: ");
            attemptsUsed++;

            if (guess == targetNumber) {
                guessedCorrectly = true;
                System.out.println("Correct! You guessed the number in " + attemptsUsed + " attempt(s).");
            } else if (guess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }

        if (guessedCorrectly) {
            roundsWon++;
            int pointsEarned = calculatePoints(maxAttempts, attemptsUsed);
            totalScore += pointsEarned;
            System.out.println("You earned " + pointsEarned + " points this round.");
        } else {
            System.out.println("Out of attempts! The number was " + targetNumber + ".");
        }

        System.out.println("Round summary -> Rounds played: " + roundsPlayed +
                " | Rounds won: " + roundsWon + " | Total score: " + totalScore);
    }

    /**
     * Calculates points for a round based on how many attempts were
     * left when the player guessed correctly. Fewer attempts used
     * means a higher score.
     */
    private static int calculatePoints(int maxAttempts, int attemptsUsed) {
        int attemptsRemaining = maxAttempts - attemptsUsed;
        int basePoints = 10;
        return basePoints + (attemptsRemaining * 2);
    }

    /**
     * Asks the player if they want to play another round.
     */
    private static boolean askToPlayAgain() {
        while (true) {
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * Prints the final score summary after the player decides to stop.
     */
    private static void printFinalScore() {
        System.out.println("\n=========================================");
        System.out.println("              FINAL RESULTS              ");
        System.out.println("=========================================");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Rounds won:    " + roundsWon);
        System.out.println("Total score:   " + totalScore);
        System.out.println("=========================================");
    }

    /**
     * Reads an integer from the user, re-prompting if the input is invalid.
     */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
