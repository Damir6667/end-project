import java.sql.*;
import java.util.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "115599";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Application started...");
        createTables();

        while (true) {
            System.out.println("\n--- School Management System ---");
            System.out.println("1. Add Student or Teacher");
            System.out.println("2. Delete Student or Teacher");
            System.out.println("3. View All Students and Teachers");
            System.out.println("4. Update Student or Teacher");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addEntity();
                case 2 -> deleteEntity();
                case 3 -> viewAll();
                case 4 -> updateEntity();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createTables() {
        String createStudentTable = "CREATE TABLE IF NOT EXISTS Student (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "age INT NOT NULL, " +
                "grade VARCHAR(20) NOT NULL, " +
                "major VARCHAR(50) NOT NULL, " +
                "exam_score INT NOT NULL)";

        String createTeacherTable = "CREATE TABLE IF NOT EXISTS Teacher (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "age INT NOT NULL, " +
                "subject VARCHAR(50) NOT NULL, " +
                "experience INT NOT NULL)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createStudentTable);
            statement.executeUpdate(createTeacherTable);
            System.out.println("Database tables created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEntity() {
        System.out.print("Do you want to add a 'student' or 'teacher'? ");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("student")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter grade: ");
            String grade = scanner.nextLine();
            System.out.print("Enter major: ");
            String major = scanner.nextLine();
            System.out.print("Enter exam score: ");
            int score = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String sql = "INSERT INTO Student (name, age, grade, major, exam_score) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, grade);
                preparedStatement.setString(4, major);
                preparedStatement.setInt(5, score);
                preparedStatement.executeUpdate();
                System.out.println("Student added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("teacher")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String sql = "INSERT INTO Teacher (name, age, subject, experience) VALUES (?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, subject);
                preparedStatement.setInt(4, experience);
                preparedStatement.executeUpdate();
                System.out.println("Teacher added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid type. Please enter 'student' or 'teacher'.");
        }
    }
    private static void deleteEntity() {
        System.out.print("Do you want to delete a 'student' or 'teacher'? ");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("student")) {
            System.out.print("Enter student name to delete: ");
            String name = scanner.nextLine();
            String sql = "DELETE FROM Student WHERE name = ?";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("Student not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("teacher")) {
            System.out.print("Enter teacher name to delete: ");
            String name = scanner.nextLine();
            String sql = "DELETE FROM Teacher WHERE name = ?";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Teacher deleted successfully.");
                } else {
                    System.out.println("Teacher not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid type. Please enter 'student' or 'teacher'.");
        }
    }


    private static void updateEntity() {
        System.out.print("Do you want to update a 'student' or 'teacher'? ");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("student")) {
            System.out.print("Enter student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            String query = "UPDATE Student SET name = ? WHERE id = ?";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, newName);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("Student not found.");
                }
            } catch (SQLException e) {
                System.err.println("Update failed: " + e.getMessage());
            }
        } else if (type.equals("teacher")) {
            System.out.print("Enter teacher ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            String query = "UPDATE Teacher SET name = ? WHERE id = ?";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, newName);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Teacher updated successfully!");
                } else {
                    System.out.println("Teacher not found.");
                }
            } catch (SQLException e) {
                System.err.println("Update failed: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid type. Please enter 'student' or 'teacher'.");
        }
    }

    private static void viewAll() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Student");
            System.out.println("\n--- Students ---");
            while (rs.next()) {
                System.out.println(rs.getString("name") + ", Age: " + rs.getInt("age") + ", Major: " + rs.getString("major"));
            }

            rs = statement.executeQuery("SELECT * FROM Teacher");
            System.out.println("\n--- Teachers ---");
            while (rs.next()) {
                System.out.println(rs.getString("name") + ", Age: " + rs.getInt("age") + ", Subject: " + rs.getString("subject"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


