import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JDialog{
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPanel loginPanel;
    private JButton loginButton;
    Connection conn = sqlconnection.connectdb();
    PreparedStatement pst = null;
    ResultSet res = null;

    public Login(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = String.valueOf(pfPassword.getPassword());
                try {
                    String sqlquery = "SELECT * FROM `user` WHERE username =  '"+username+"' AND password = '"+password+"'" ;
                    pst = conn.prepareStatement(sqlquery);
                    res = pst.executeQuery();
                    if (res.next()){
                        JOptionPane.showMessageDialog(null,"Welcome! " + res.getString(2));
                    }else {
                        JOptionPane.showMessageDialog(null,"Invalid Login");
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Login.form");
        frame.setPreferredSize(new Dimension(300,200));
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