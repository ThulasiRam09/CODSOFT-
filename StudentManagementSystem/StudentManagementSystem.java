import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentManagementSystem
 * -------------------------
 * Manages a collection of Student objects and handles persistence
 * of student data to a file so records survive between program runs.
 */
public class StudentManagementSystem {

    private static final String DATA_FILE = "students.txt";

    private final List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new student to the system, provided the roll number is unique.
     * Returns true if the student was added successfully.
     */
    public boolean addStudent(Student student) {
        if (findByRollNumber(student.getRollNumber()) != null) {
            return false; // roll number already exists
        }
        students.add(student);
        saveToFile();
        return true;
    }

    /**
     * Removes a student by roll number. Returns true if a student was removed.
     */
    public boolean removeStudent(String rollNumber) {
        Student student = findByRollNumber(rollNumber);
        if (student == null) {
            return false;
        }
        students.remove(student);
        saveToFile();
        return true;
    }

    /**
     * Searches for a student by roll number.
     */
    public Student findByRollNumber(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Searches for students by name (partial, case-insensitive match).
     */
    public List<Student> findByName(String name) {
        List<Student> matches = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(student);
            }
        }
        return matches;
    }

    /**
     * Updates an existing student's details. Returns true if the update succeeded.
     */
    public boolean updateStudent(String rollNumber, String newName, String newGrade, String newContact) {
        Student student = findByRollNumber(rollNumber);
        if (student == null) {
            return false;
        }
        if (newName != null && !newName.isEmpty()) {
            student.setName(newName);
        }
        if (newGrade != null && !newGrade.isEmpty()) {
            student.setGrade(newGrade);
        }
        if (newContact != null && !newContact.isEmpty()) {
            student.setContactNumber(newContact);
        }
        saveToFile();
        return true;
    }

    /**
     * Returns all students currently stored in the system.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public int getTotalStudents() {
        return students.size();
    }

    /**
     * Writes the current list of students to the data file.
     */
    private void saveToFile() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            for (Student student : students) {
                writer.write(student.toCsvLine());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    /**
     * Loads student records from the data file, if it exists.
     */
    private void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Student student = Student.fromCsvLine(line);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }
}
