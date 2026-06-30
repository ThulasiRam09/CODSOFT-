# Number Guessing Game

A console-based number guessing game written in Java. The program picks a random number within a range you choose, and you try to guess it within a limited number of attempts. Play as many rounds as you like and track your score along the way.

## Features

- Random number generation within a user-defined range (e.g. 1 to 100)
- Feedback after every guess — tells you if it was too high, too low, or correct
- Configurable attempt limit per round
- Multiple rounds — choose to play again after each round
- Score tracking based on rounds won and attempts used
- Input validation to handle invalid or out-of-range entries gracefully

## How It Works

1. Enter the lower and upper bounds for the number range.
2. Enter the maximum number of attempts you want for that round.
3. Start guessing — the game tells you after each guess whether to go higher or lower.
4. Keep guessing until you either get it right or run out of attempts.
5. Choose to play another round or end the game.
6. View your final score summary, including rounds played, rounds won, and total points.

## Scoring

Each correct guess earns points based on how many attempts you had left:

```
points = 10 + (attempts remaining * 2)
```

Guessing correctly on an earlier attempt earns a higher score for that round.

## Requirements

- Java JDK 8 or higher

## How to Run

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

## Example

```
=========================================
        WELCOME TO THE NUMBER GAME       
=========================================
Enter the lower bound of the range: 1
Enter the upper bound of the range: 100
Enter the maximum number of attempts allowed: 7

I'm thinking of a number between 1 and 100. You have 7 attempts.
Attempt 1/7 - Enter your guess: 50
Too high. Try again.
Attempt 2/7 - Enter your guess: 25
Too low. Try again.
Attempt 3/7 - Enter your guess: 37
Correct! You guessed the number in 3 attempt(s).
You earned 18 points this round.
```

## Project Structure

```
NumberGuessingGame.java   # Main and only source file
```

## Author
--N.Thulasi Ram
Created as part of an internship task assignment.

## License

This project is open source and available for personal or educational use.
