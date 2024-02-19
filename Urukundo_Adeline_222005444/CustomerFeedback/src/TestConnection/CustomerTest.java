package TestConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerTest {

    public static void main(String[] args) {
        Connection co = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM customer ";
        String host = "jdbc:mysql://localhost/customer_feedback_collection";
        String user = "root";
        String pass = "";

        try {
            // Use the new driver class name
            Class.forName("com.mysql.cj.jdbc.Driver");

            co = DriverManager.getConnection(host, user, pass);
            stm = co.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String fn = rs.getString("Firstname");
                String ln = rs.getString("LastName");
                String em = rs.getString("Email");
                String Pn = rs.getString("PhoneNumber");
                String Ad = rs.getString("Address");
                System.out.println(id + "\t" + fn + "\t" + ln + "\t" + em + "\t" + Pn + "\t" + Ad + "\t");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("An error occurred while loading the JDBC driver");
        } catch (SQLException e) {
            System.out.println("There was an error accessing the database.");
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (SQLException e) {
                System.out.println("There was an error accessing the database.");
            }
        }
    }
}
		