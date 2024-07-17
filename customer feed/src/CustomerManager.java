import java.sql.*;
import java.util.Scanner;

public class CustomerManager {

    public void addCustomer(Scanner scanner) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Enter Customer Name:");
            String customerName = scanner.nextLine();
            System.out.println("Enter Customer Email:");
            String customerEmail = scanner.nextLine();

            String sql = "INSERT INTO Customers (customer_name, customer_email) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, customerName);
                stmt.setString(2, customerEmail);
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Customer added successfully.");
                } else {
                    System.out.println("Customer addition failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Customers";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Customer ID: " + rs.getInt("customer_id"));
                    System.out.println("Customer Name: " + rs.getString("customer_name"));
                    System.out.println("Customer Email: " + rs.getString("customer_email"));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

