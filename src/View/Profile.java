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

public class Profile extends JDialog {
    private JPanel contentPane;
    private JButton backButton;
    private JButton editButton;
    private JButton editButton1;
    private JButton editButton2;
    private JButton editButton3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private String username;
    private String nama;
    private String alamat;
    private String noHp;
    private String password;

    Connection conn = sqlconnection.connectdb();

    public Profile() {
        createUIComponents();
        setContentPane(contentPane);
        setModal(true);
        getUserData(); // Call method to retrieve user data

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mp = new MainPage();
                mp.display(); // Display MainPage
                Profile.this.dispose(); // Close Profile dialog
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUsername(); // Call method to edit username
            }
        });

        editButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNama(); // Call method to edit nama
            }
        });

        editButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAlamat(); // Call method to edit alamat
            }
        });

        editButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNoHp(); // Call method to edit noHp
            }
        });
    }

    private void createUIComponents() {
        contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel profileLabel = new JLabel("Profile", JLabel.CENTER);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 20, 0);
        contentPane.add(profileLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(usernameLabel, gbc);

        label1 = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(label1, gbc);

        editButton = new JButton("Edit");
        gbc.gridx = 2;
        gbc.gridy = 1;
        contentPane.add(editButton, gbc);

        JLabel namaLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(namaLabel, gbc);

        label2 = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(label2, gbc);

        editButton1 = new JButton("Edit");
        gbc.gridx = 2;
        gbc.gridy = 2;
        contentPane.add(editButton1, gbc);

        JLabel alamatLabel = new JLabel("Alamat:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(alamatLabel, gbc);

        label3 = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(label3, gbc);

        editButton2 = new JButton("Edit");
        gbc.gridx = 2;
        gbc.gridy = 3;
        contentPane.add(editButton2, gbc);

        JLabel noHpLabel = new JLabel("No. HP:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(noHpLabel, gbc);

        label4 = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(label4, gbc);

        editButton3 = new JButton("Edit");
        gbc.gridx = 2;
        gbc.gridy = 4;
        contentPane.add(editButton3, gbc);

        backButton = new JButton("<-");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 10, 0);
        contentPane.add(backButton, gbc);
    }

    public void display() {
        JFrame frame = new JFrame("Profile Page");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
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
                label1.setText(username);
                label2.setText(nama);
                label3.setText(alamat);
                label4.setText(noHp);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to retrieve user data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editUsername() {
        String newUsername = JOptionPane.showInputDialog(this, "Enter new username:", username);

        if (newUsername != null && !newUsername.trim().isEmpty()) {
            String updateQuery = "UPDATE user SET username = ? WHERE username = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(updateQuery);
                pst.setString(1, newUsername);
                pst.setString(2, username);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Username updated successfully");
                    username = newUsername;
                    label1.setText(username);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editNama() {
        String newNama = JOptionPane.showInputDialog(this, "Enter new nama:", nama);

        if (newNama != null && !newNama.trim().isEmpty()) {
            String updateQuery = "UPDATE user SET nama = ? WHERE username = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(updateQuery);
                pst.setString(1, newNama);
                pst.setString(2, username);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Nama updated successfully");
                    nama = newNama;
                    label2.setText(nama);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update nama", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editAlamat() {
        String newAlamat = JOptionPane.showInputDialog(this, "Enter new alamat:", alamat);

        if (newAlamat != null && !newAlamat.trim().isEmpty()) {
            String updateQuery = "UPDATE user SET alamat = ? WHERE username = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(updateQuery);
                pst.setString(1, newAlamat);
                pst.setString(2, username);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Alamat updated successfully");
                    alamat = newAlamat;
                    label3.setText(alamat);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update alamat", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editNoHp() {
        String newNoHp = JOptionPane.showInputDialog(this, "Enter new No. HP:", noHp);

        if (newNoHp != null && !newNoHp.trim().isEmpty()) {
            String updateQuery = "UPDATE user SET no_hp = ? WHERE username = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(updateQuery);
                pst.setString(1, newNoHp);
                pst.setString(2, username);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "No. HP updated successfully");
                    noHp = newNoHp;
                    label4.setText(noHp);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update No. HP", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
