package AllForms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CustomerForm {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    // Constructors, getters, and setters

    public CustomerForm() {}

    // Add constructors, getters, and setters as needed

    public void insertData() {
        String host = "jdbc:mysql://localhost/customer_feedback_collection";
        String user = "root";
        String password = "";
        String sql = "INSERT INTO customer(FirstName, LastName, Email, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(host, user, password);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, this.firstName);
            preparedStatement.setString(2, this.lastName);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, this.phoneNumber);
            preparedStatement.setString(5, this.address);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
                JOptionPane.showMessageDialog(null, "Data inserted successfully!", "After insert", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data.!", "After insert", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readWithID(int inputId) {
        String url = "jdbc:mysql://localhost/customer_feedback_collection";
        String user = "root";
        String password = "";
        String sql = "SELECT * FROM customer WHERE customerId = ?";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, inputId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.setCustomerId(resultSet.getInt("customerId"));
                this.setFirstName(resultSet.getString("FirstName"));
                this.setLastName(resultSet.getString("LastName"));
                this.setEmail(resultSet.getString("Email"));
                this.setPhoneNumber(resultSet.getString("PhoneNumber"));
                this.setAddress(resultSet.getString("Address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int inputCustomerId) {
        String url = "jdbc:mysql://localhost/customer_feedback_collection";
        String user = "root";
        String password = "";
        String sql = "UPDATE customer SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ? WHERE customerId = ?";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, this.getFirstName());
            preparedStatement.setString(2, this.getLastName());
            preparedStatement.setString(3, this.getEmail());
            preparedStatement.setString(4, this.getPhoneNumber());
            preparedStatement.setString(5, this.getAddress());
            preparedStatement.setInt(6, inputCustomerId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Failed to update data. No matching record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int inputCustomerId) {
        String url = "jdbc:mysql://localhost/customer_feedback_collection";
        String user = "root";
        String password = "";
        String sql = "DELETE FROM customer WHERE customerId = ?";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, inputCustomerId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Failed to delete data. No matching record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}