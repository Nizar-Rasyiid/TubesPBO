package View;

import JDBC.sqlconnection;
import Model.Auth.Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JDialog {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPanel loginPanel;
    private JButton loginButton;
    Connection conn = sqlconnection.connectdb();
    PreparedStatement pst = null;
    ResultSet res = null;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = String.valueOf(pfPassword.getPassword());
                try {
                    String sqlquery = "SELECT * FROM `user` WHERE username = ? AND password = ?";
                    pst = conn.prepareStatement(sqlquery);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    res = pst.executeQuery();
                    if (res.next()) {
                        JOptionPane.showMessageDialog(null, "Welcome! " + res.getString(2));
                        // Simpan username pengguna yang berhasil login
                        Authentication.setLoggedInUsername(username);
                        // Pindah ke halaman profil
                        MainPage mp = new MainPage();
                        mp.display();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

    public static void display() {
        JFrame frame = new JFrame("Login");
        frame.setPreferredSize(new Dimension(300, 200));
        frame.setContentPane(new Login().loginPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setPreferredSize(new Dimension(300, 200));
        frame.setContentPane(new Login().loginPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
