package JDBC;

import javax.swing.*;
import java.sql.*;

public class sqlconnection {

    static Statement stmt;
    static ResultSet rs;
    static Connection conn;  // Moved connection variable here

    public static Connection connectdb() {
        String dbUsername = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/sendit";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            stmt = conn.createStatement();  // Initialize statement after connection
            return conn;

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return null;
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();  // Close connection
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            rs = stmt.executeQuery(query);  // Execute query and store result set
            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return null;
    }

    public static int executeUpdate(String query) {
        try {
            int rowsAffected = stmt.executeUpdate(query);  // Execute update and get affected rows
            return rowsAffected;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return 0;
    }
}
