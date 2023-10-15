import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private Map<String, Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public Map<String, Double> getGrades() {
        return grades;
    }
}

class GradeTracker {
    private Map<String, Student> students;

    public GradeTracker() {
        students = new HashMap<>();
    }

    public void addStudent(String name) {
        students.put(name, new Student(name));
    }

    public void addGrade(String studentName, String subject, double grade) {
        Student student = students.get(studentName);
        if (student != null) {
            student.addGrade(subject, grade);
        } else {
            System.out.println("Student not found.");
        }
    }

    public double calculateAverage(String studentName) {
        Student student = students.get(studentName);
        if (student != null) {
            Map<String, Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                return 0.0;
            } else {
                double total = 0;
                for (Double grade : grades.values()) {
                    total += grade;
                }
                return total / grades.size();
            }
        } else {
            System.out.println("Student not found.");
            return 0.0;
        }
    }

    public void generateReport(String studentName) {
        Student student = students.get(studentName);
        if (student != null) {
            System.out.println("Report for " + student.getName());
            Map<String, Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                System.out.println("No grades available.");
            } else {
                for (Map.Entry<String, Double> entry : grades.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("Average Score: " + calculateAverage(studentName));
            }
        } else {
            System.out.println("Student not found.");
        }
    }
}

public class StudentGradeTrackerApp {
    public static void main(String[] args) {
        GradeTracker gradeTracker = new GradeTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Grade Tracker");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Calculate Average");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Select an option (1/2/3/4/5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.next();
                    gradeTracker.addStudent(studentName);
                    System.out.println("Student added: " + studentName);
                    break;
                case 2:
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter subject: ");
                    String subject = scanner.next();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    gradeTracker.addGrade(name, subject, grade);
                    System.out.println("Grade added for " + name);
                    break;
                case 3:
                    System.out.print("Enter student name: ");
                    String studentNameToCalculate = scanner.next();
                    System.out.println("Average Score: " + gradeTracker.calculateAverage(studentNameToCalculate));
                    break;
                case 4:
                    System.out.print("Enter student name: ");
                    String studentNameToReport = scanner.next();
                    gradeTracker.generateReport(studentNameToReport);
                    break;
                case 5:
                    System.out.println("Exiting Student Grade Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }
}
