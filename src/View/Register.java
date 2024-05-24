package View;

import JDBC.sqlconnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JDialog {
    private JPanel panel1;
    private JTextField noHpField;
    private JTextField alamatField;
    private JTextField namaField;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton loginButton;

    Connection conn = sqlconnection.connectdb();
    PreparedStatement pst = null;

    public Register() {
        setContentPane(panel1);
        setModal(true);
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();


            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pindahLogin();
            }
        });
    }
    private void pindahLogin(){
        Login log = new Login();
        Login.display();
        dispose();
    }



    private void registerUser() {
        String nama = namaField.getText();
        String hp = noHpField.getText();
        String alamat = alamatField.getText();
        String uname = usernameField.getText();
        String pwd = String.valueOf(passwordField1.getPassword());

        if (nama.isEmpty() || hp.isEmpty() || alamat.isEmpty() || uname.isEmpty() || pwd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO user (nama, no_hp, alamat, username, password) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, nama);
            pst.setString(2, hp);
            pst.setString(3, alamat);
            pst.setString(4, uname);
            pst.setString(5, pwd);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Login login = new Login();
                Login.display();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database connection closing error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        Register dialog = new Register();
        dialog.setVisible(true);
    }
}
