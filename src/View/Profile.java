package View;

import JDBC.sqlconnection;
import Model.Auth.Authentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile extends JDialog {
    private JPanel contentPane;
    private JButton backButton;
    private JButton editButton;
    private JButton editButton1;
    private JButton editButton2;
    private JButton editButton3;
    private JLabel label4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private String username;
    private String nama;
    private String alamat;
    private String noHp;
    private String password;

    Connection conn = sqlconnection.connectdb();

    public Profile() {
        setContentPane(contentPane);
        setModal(true);
        createUIComponents();
        getUserData(); // Call method to retrieve user data

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mp = new MainPage();
                mp.display(); // Display MainPage
               Profile.this.dispose(); // Close Profile dialog
            }
        });
    }



    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        label1 = new JLabel("Username: ");
        label2 = new JLabel("Nama: ");
        label3 = new JLabel("Alamat: ");
        label4 = new JLabel("No. HP: ");

        editButton = new JButton("Edit");
        editButton1 = new JButton("Edit");
        editButton2 = new JButton("Edit");
        editButton3 = new JButton("Edit");
        backButton = new JButton("<-");

        // Tambahkan komponen ke contentPane
        contentPane.add(label1);
        contentPane.add(editButton);
        contentPane.add(label2);
        contentPane.add(editButton1);
        contentPane.add(label3);
        contentPane.add(editButton2);
        contentPane.add(label4);
        contentPane.add(editButton3);
        contentPane.add(new JLabel("Profile"));
        contentPane.add(backButton);
    }

    public void display() {
        JFrame frame = new JFrame("Profile Page");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Agar frame muncul di tengah layar
        frame.setVisible(true);
    }

    private void getUserData() {
        String loggedInUsername = Authentication.getLoggedInUsername(); // Get the logged-in username

        if (loggedInUsername == null || loggedInUsername.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No logged-in user", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT * FROM user WHERE username = ?";
        System.out.println("Executing query: " + query + " with username: " + loggedInUsername);

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, loggedInUsername);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
                nama = rs.getString("nama");
                alamat = rs.getString("alamat");
                noHp = rs.getString("no_hp");
                password = rs.getString("password");  // Consider not displaying password

                // Update labels with retrieved data
                label1.setText("Username: " + username);
                label2.setText("Nama: " + nama);
                label3.setText("Alamat: " + alamat);
                label4.setText("No. HP: " + noHp);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to retrieve user data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Profile dialog = new Profile();
        dialog.pack();
        dialog.setVisible(true);
    }
}
