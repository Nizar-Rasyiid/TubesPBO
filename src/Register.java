import ClassModel.Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Register extends JDialog {
    private JLabel Register;
    private JTextField tfName;
    private JTextField tfUsername;
    private JTextField tfPhone;
    private JTextField pfPassword;
    private JTextField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel RegisterPanel;
    private JTextField tfAlamat;
    private JComboBox cbRole;

    public Register(JFrame parent){
        super(parent);
        JFrame frame = new JFrame("Register.form");
        frame.setPreferredSize(new Dimension(400,320));
        frame.setContentPane(new Register().RegisterPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public Register() {

    }

    private void RegisterUser() {
        String idUser = tfUsername.getText();
        String nama = tfName.getText();
        String alamat = tfAlamat.getText();
        String noHp = tfPhone.getText();
        String Password = String.valueOf(pfPassword.getText());
        String confirmPassword = String.valueOf(pfConfirmPassword.getText());
        String role = cbRole.getToolTipText();

        if (nama.isEmpty() || idUser.isEmpty() || alamat.isEmpty() || noHp.isEmpty() || noHp.isEmpty() || Password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tolong isi semua data",
                    "Coba lagi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Password dan Confirm Password tidak sesuai",
                    "Coba lagi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUsertoDatabase(nama, idUser, noHp, alamat, role, Password);
    }
    public User user;
    private User addUsertoDatabase(String idUser, String nama, String noHp, String alamat, String role, String Password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/sendit";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (idUser, nama, alamat, noHP, role, Password) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,idUser);
            preparedStatement.setString(2,nama);
            preparedStatement.setString(3,alamat);
            preparedStatement.setString(4,noHp);
            preparedStatement.setString(5,role);
            preparedStatement.setString(6,Password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){

                user.nama = nama;
                user.idUser = idUser;
                user.alamat = alamat;
                user.noHp = noHp;
                user.Password = Password;
                user.role = role;
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args){
        Register myform = new Register(null);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
