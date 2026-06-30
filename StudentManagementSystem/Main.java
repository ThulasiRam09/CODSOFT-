package studentManagement;
import java.util.List;
import java.util.Scanner;

/**
 * Main
 * -----
 * Console-based entry point for the Student Management System.
 * Provides a menu-driven interface for adding, removing, searching,
 * updating, and displaying students.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem system = new StudentManagementSystem();

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("         STUDENT MANAGEMENT SYSTEM        ");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleRemoveStudent();
                    break;
                case 3:
                    handleSearchStudent();
                    break;
                case 4:
                    handleUpdateStudent();
                    break;
                case 5:
                    handleDisplayAllStudents();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 6.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- MENU -----------------");
        System.out.println("1. Add a new student");
        System.out.println("2. Remove a student");
        System.out.println("3. Search for a student");
        System.out.println("4. Edit an existing student's information");
        System.out.println("5. Display all students");
        System.out.println("6. Exit");
        System.out.println("-----------------------------------------");
    }

    private static void handleAddStudent() {
        System.out.println("\n--- Add New Student ---");

        String rollNumber = readNonEmptyString("Enter roll number: ");
        String name = readNonEmptyString("Enter name: ");
        String grade = readNonEmptyString("Enter grade: ");
        String contact = readValidContactNumber("Enter contact number (10 digits): ");

        Student student = new Student(rollNumber, name, grade, contact);
        boolean added = system.addStudent(student);

        if (added) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with roll number " + rollNumber + " already exists.");
        }
    }

    private static void handleRemoveStudent() {
        System.out.println("\n--- Remove Student ---");
        String rollNumber = readNonEmptyString("Enter the roll number to remove: ");

        boolean removed = system.removeStudent(rollNumber);
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("No student found with roll number " + rollNumber + ".");
        }
    }

    private static void handleSearchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.println("1. Search by roll number");
        System.out.println("2. Search by name");
        int option = readInt("Choose search method: ");

        if (option == 1) {
            String rollNumber = readNonEmptyString("Enter roll number: ");
            Student student = system.findByRollNumber(rollNumber);
            if (student != null) {
                System.out.println("Student found:");
                System.out.println(student);
            } else {
                System.out.println("No student found with roll number " + rollNumber + ".");
            }
        } else if (option == 2) {
            String name = readNonEmptyString("Enter name (or part of it): ");
            List<Student> matches = system.findByName(name);
            if (matches.isEmpty()) {
                System.out.println("No students found matching \"" + name + "\".");
            } else {
                System.out.println("Found " + matches.size() + " matching student(s):");
                for (Student student : matches) {
                    System.out.println(student);
                }
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void handleUpdateStudent() {
        System.out.println("\n--- Edit Student Information ---");
        String rollNumber = readNonEmptyString("Enter the roll number of the student to edit: ");

        Student existing = system.findByRollNumber(rollNumber);
        if (existing == null) {
            System.out.println("No student found with roll number " + rollNumber + ".");
            return;
        }

        System.out.println("Current details: " + existing);
        System.out.println("Leave a field blank to keep its current value.");

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine().trim();

        System.out.print("Enter new grade: ");
        String newGrade = scanner.nextLine().trim();

        String newContact = "";
        while (true) {
            System.out.print("Enter new contact number (10 digits): ");
            newContact = scanner.nextLine().trim();
            if (newContact.isEmpty() || isValidContactNumber(newContact)) {
                break;
            }
            System.out.println("Invalid contact number. It must be exactly 10 digits.");
        }

        boolean updated = system.updateStudent(rollNumber, newName, newGrade, newContact);
        if (updated) {
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Update failed. Student not found.");
        }
    }

    private static void handleDisplayAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> allStudents = system.getAllStudents();

        if (allStudents.isEmpty()) {
            System.out.println("No students have been added yet.");
            return;
        }

        for (Student student : allStudents) {
            System.out.println(student);
        }
        System.out.println("Total students: " + system.getTotalStudents());
    }

    /**
     * Reads a string from the user and ensures it is not empty.
     */
    private static String readNonEmptyString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    /**
     * Reads and validates a 10-digit contact number.
     */
    private static String readValidContactNumber(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (isValidContactNumber(input)) {
                return input;
            }
            System.out.println("Invalid contact number. It must be exactly 10 digits.");
        }
    }

    private static boolean isValidContactNumber(String contact) {
        return contact.matches("\\d{10}");
    }

    /**
     * Reads an integer from the user, re-prompting on invalid input.
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
