import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import java.io.IOException;

public class Profile extends JDialog {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
    }
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
    String password;

    Connection conn = sqlconnection.connectdb();
    public Profile() {
        setContentPane(contentPane);
        setModal(true);
        createUIComponents();
    }

    public static void main(String[] args) {
        Profile dialog = new Profile();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setContentPane(new Profile().contentPane);

//        JFrame frame = new JFrame("Profile.form");
//        frame.setPreferredSize(new Dimension(257,239));
//        frame.setContentPane(new Profile().contentPane);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.pack();
//        frame.setVisible(true);
    }
    private void createUIComponents() {
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        label1 = new JLabel("Label");
        label2 = new JLabel("Label");
        label3 = new JLabel("Label");
        label4 = new JLabel("Password : " + password);

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
        JFrame frame = new JFrame("Main Page");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Agar frame muncul di tengah layar
        frame.setVisible(true);
    }
}