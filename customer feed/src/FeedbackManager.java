
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class FeedbackManager {

    public void submitFeedback() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter Customer ID:");
            int customerId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter Feedback Text:");
            String feedbackText = scanner.nextLine();
            System.out.println("Enter Rating:");
            int rating = scanner.nextInt();

            String sql = "INSERT INTO Feedback (customer_id, feedback_date, feedback_text, rating) VALUES (?, NOW(), ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                stmt.setString(2, feedbackText);
                stmt.setInt(3, rating);
                stmt.executeUpdate();
                System.out.println("Feedback submitted successfully.");
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Error: Customer ID does not exist.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewFeedback() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Feedback ID to view:");
            int feedbackId = scanner.nextInt();

            String sql = "SELECT * FROM Feedback WHERE feedback_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, feedbackId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Feedback ID: " + rs.getInt("feedback_id"));
                        System.out.println("Customer ID: " + rs.getInt("customer_id"));
                        System.out.println("Feedback Date: " + rs.getDate("feedback_date"));
                        System.out.println("Feedback Text: " + rs.getString("feedback_text"));
                        System.out.println("Rating: " + rs.getInt("rating"));
                    } else {
                        System.out.println("Feedback not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFeedback() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Feedback ID to update:");
            int feedbackId = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("Enter new Feedback Text:");
            String feedbackText = scanner.nextLine();
            System.out.println("Enter new Rating:");
            int rating = scanner.nextInt();

            String sql = "UPDATE Feedback SET feedback_text = ?, rating = ? WHERE feedback_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, feedbackText);
                stmt.setInt(2, rating);
                stmt.setInt(3, feedbackId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Feedback updated successfully.");
                } else {
                    System.out.println("Feedback not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFeedback() {
        try (Connection conn = DBConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Feedback ID to delete:");
            int feedbackId = scanner.nextInt();

            String sql = "DELETE FROM Feedback WHERE feedback_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, feedbackId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Feedback deleted successfully.");
                } else {
                    System.out.println("Feedback not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
