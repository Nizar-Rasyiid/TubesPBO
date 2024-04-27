import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPage extends JDialog {
    private JPanel panel1;
    private JButton profileButton;

    public MainPage(){
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile profilePage = new Profile();
                profilePage.display();
                dispose();
            }
        });
    }

    // Metode untuk menampilkan halaman utama
    public void display() {
        JFrame frame = new JFrame("Main Page");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Agar frame muncul di tengah layar
        frame.setVisible(true);
    }
}

// Metode untuk menyembunyikan halaman utama (tidak diperlukan dalam kasus ini)
