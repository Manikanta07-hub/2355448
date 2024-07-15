
import java.sql.*;
import java.util.Scanner;

public class SurveyManager {

    public void createSurvey() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Survey Name:");
            String surveyName = scanner.nextLine();
            System.out.println("Enter Survey Description:");
            String surveyDescription = scanner.nextLine();

            String sql = "INSERT INTO Survey (survey_name, survey_description, status) VALUES (?, ?, 'active')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, surveyName);
                stmt.setString(2, surveyDescription);
                stmt.executeUpdate();
                System.out.println("Survey created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewSurvey() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Survey ID to view:");
            int surveyId = scanner.nextInt();

            String sql = "SELECT * FROM Survey WHERE survey_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, surveyId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Survey ID: " + rs.getInt("survey_id"));
                        System.out.println("Survey Name: " + rs.getString("survey_name"));
                        System.out.println("Survey Description: " + rs.getString("survey_description"));
                        System.out.println("Status: " + rs.getString("status"));
                    } else {
                        System.out.println("Survey not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSurvey() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Survey ID to update:");
            int surveyId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter new Survey Name:");
            String surveyName = scanner.nextLine();
            System.out.println("Enter new Survey Description:");
            String surveyDescription = scanner.nextLine();

            String sql = "UPDATE Survey SET survey_name = ?, survey_description = ? WHERE survey_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, surveyName);
                stmt.setString(2, surveyDescription);
                stmt.setInt(3, surveyId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Survey updated successfully.");
                } else {
                    System.out.println("Survey not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSurvey() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Survey ID to delete:");
            int surveyId = scanner.nextInt();

            String sql = "DELETE FROM Survey WHERE survey_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, surveyId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Survey deleted successfully.");
                } else {
                    System.out.println("Survey not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
