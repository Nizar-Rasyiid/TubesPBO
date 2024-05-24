package View;

import javax.swing.*;

public class MainPage extends JDialog {
    private JPanel panel1;
    private JButton profileButton;

    public MainPage(){
        profileButton.addActionListener(e -> {
            Profile profilePage = new Profile();
            profilePage.display();
            MainPage.this.dispose();
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
