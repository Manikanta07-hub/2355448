import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerManager {
    public void addCustomer(Scanner scanner) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Enter Customer Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Customer Email:");
            String email = scanner.nextLine();

            String sql = "INSERT INTO Customer (customer_name, customer_email) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.executeUpdate();
                System.out.println("Customer added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Customer";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("customer_id"));
                    System.out.println("Customer Name: " + rs.getString("customer_name"));
                    System.out.println("Customer Email: " + rs.getString("customer_email"));
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
