import javax.swing.*;

public class MainPage {
    private JPanel panel1;

    public MainPage(){
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
