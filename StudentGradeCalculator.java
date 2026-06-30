import java.util.Scanner;

/**
 * StudentGradeCalculator
 * -----------------------
 * Takes marks (out of 100) for a number of subjects entered by the user,
 * then calculates:
 *   - Total marks
 *   - Average percentage
 *   - Final grade based on the average percentage
 *
 * Grading scale used:
 *   90 - 100  -> A+
 *   80 - 89   -> A
 *   70 - 79   -> B
 *   60 - 69   -> C
 *   50 - 59   -> D
 *   Below 50  -> F
 */
public class StudentGradeCalculator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("          STUDENT GRADE CALCULATOR        ");
        System.out.println("=========================================");

        int numberOfSubjects = readNumberOfSubjects();
        double[] marks = readMarks(numberOfSubjects);

        double totalMarks = calculateTotal(marks);
        double averagePercentage = calculateAverage(totalMarks, numberOfSubjects);
        String grade = calculateGrade(averagePercentage);

        displayResults(numberOfSubjects, marks, totalMarks, averagePercentage, grade);

        scanner.close();
    }

    /**
     * Asks the user how many subjects they want to enter marks for,
     * validating that the number is positive.
     */
    private static int readNumberOfSubjects() {
        int count;
        while (true) {
            System.out.print("Enter the number of subjects: ");
            String input = scanner.nextLine().trim();
            try {
                count = Integer.parseInt(input);
                if (count > 0) {
                    return count;
                }
                System.out.println("Please enter a number greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /**
     * Collects marks (out of 100) for each subject from the user.
     */
    private static double[] readMarks(int numberOfSubjects) {
        double[] marks = new double[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++) {
            marks[i] = readSingleMark(i + 1);
        }

        return marks;
    }

    /**
     * Reads a single subject's marks, ensuring the value is between 0 and 100.
     */
    private static double readSingleMark(int subjectNumber) {
        double mark;
        while (true) {
            System.out.print("Enter marks obtained in Subject " + subjectNumber + " (out of 100): ");
            String input = scanner.nextLine().trim();
            try {
                mark = Double.parseDouble(input);
                if (mark >= 0 && mark <= 100) {
                    return mark;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    /**
     * Sums up the marks obtained across all subjects.
     */
    private static double calculateTotal(double[] marks) {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    /**
     * Calculates the average percentage based on total marks
     * and the number of subjects.
     */
    private static double calculateAverage(double totalMarks, int numberOfSubjects) {
        return totalMarks / numberOfSubjects;
    }

    /**
     * Determines the letter grade based on the average percentage.
     */
    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
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

    /**
     * Displays the final results in a clean, readable format.
     */
    private static void displayResults(int numberOfSubjects, double[] marks, double totalMarks,
                                        double averagePercentage, String grade) {
        System.out.println("\n=========================================");
        System.out.println("                 RESULTS                  ");
        System.out.println("=========================================");

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.printf("Subject %d: %.2f%n", (i + 1), marks[i]);
        }

        System.out.println("-----------------------------------------");
        System.out.printf("Total Marks:        %.2f / %d%n", totalMarks, numberOfSubjects * 100);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Final Grade:        " + grade);
        System.out.println("=========================================");
    }
}
