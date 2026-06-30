/**
 * Student
 * --------
 * Represents an individual student record with basic attributes.
 */
public class Student {

    private String rollNumber;
    private String name;
    private String grade;
    private String contactNumber;

    public Student(String rollNumber, String name, String grade, String contactNumber) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        this.contactNumber = contactNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Converts the student record into a single CSV line for file storage.
     * Format: rollNumber,name,grade,contactNumber
     */
    public String toCsvLine() {
        return rollNumber + "," + name + "," + grade + "," + contactNumber;
    }

    /**
     * Builds a Student object back from a CSV line read from the file.
     */
    public static Student fromCsvLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 4) {
            return null;
        }
        return new Student(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
    }

    @Override
    public String toString() {
        return String.format("Roll No: %-10s Name: %-20s Grade: %-8s Contact: %s",
                rollNumber, name, grade, contactNumber);
    }
}
