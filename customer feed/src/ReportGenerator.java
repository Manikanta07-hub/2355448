import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {

    public void generateFeedbackReport() {
        String sql = "SELECT * FROM Feedback";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("feedback_report.csv")) {

            writer.write("feedback_id,customer_id,feedback_date,feedback_text,rating\n");
            while (rs.next()) {
                writer.write(rs.getInt("feedback_id") + ","
                        + rs.getInt("customer_id") + ","
                        + rs.getDate("feedback_date") + ","
                        + rs.getString("feedback_text") + ","
                        + rs.getInt("rating") + "\n");
            }
            System.out.println("Feedback report generated successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateSurveyReport() {
        String sql = "SELECT * FROM Survey";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("survey_report.csv")) {

            writer.write("survey_id,survey_name,survey_description,status\n");
            while (rs.next()) {
                writer.write(rs.getInt("survey_id") + ","
                        + rs.getString("survey_name") + ","
                        + rs.getString("survey_description") + ","
                        + rs.getString("status") + "\n");
            }
            System.out.println("Survey report generated successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateSurveyResponseReport() {
        String sql = "SELECT * FROM SurveyResponse";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("survey_response_report.csv")) {

            writer.write("response_id,survey_id,customer_id,response_date,response_text\n");
            while (rs.next()) {
                writer.write(rs.getInt("response_id") + ","
                        + rs.getInt("survey_id") + ","
                        + rs.getInt("customer_id") + ","
                        + rs.getDate("response_date") + ","
                        + rs.getString("response_text") + "\n");
            }
            System.out.println("Survey response report generated successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDetailedFeedbackReport() {
        String sql = "SELECT f.feedback_id, f.customer_id, c.customer_name, f.feedback_date, f.feedback_text, f.rating " +
                     "FROM Feedback f " +
                     "JOIN Customer c ON f.customer_id = c.customer_id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("detailed_feedback_report.csv")) {

            writer.write("feedback_id,customer_id,customer_name,feedback_date,feedback_text,rating\n");
            while (rs.next()) {
                writer.write(rs.getInt("feedback_id") + ","
                        + rs.getInt("customer_id") + ","
                        + rs.getString("customer_name") + ","
                        + rs.getDate("feedback_date") + ","
                        + rs.getString("feedback_text") + ","
                        + rs.getInt("rating") + "\n");
            }
            System.out.println("Detailed customer feedback report generated successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
