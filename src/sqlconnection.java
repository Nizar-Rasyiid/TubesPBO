import javax.swing.*;
import java.sql.*;

public class sqlconnection {
    public static Connection connectdb(){
        String dbUsername = "root";
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/sendit";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            return  conn;

        }catch (SQLException|ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,e);

        }
        return null;
    }
}
