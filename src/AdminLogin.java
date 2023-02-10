import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AdminLogin implements ActionListener {
    JPanel panel;
    JFrame f;
    JLabel l1, l2, l3, l4, l5;
    JTextField t1;
    JPasswordField pf1;
    JButton b1, b2;
    ConnectionClass obj;

    AdminLogin(ConnectionClass obj) {
        this.obj = obj;

        f = new JFrame("Login Account");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 580, 350);
        l1.setLayout(null);

        l2 = new JLabel("Login Account");
        l2.setBounds(190, 30, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l2);
        f.add(l1);

        l3 = new JLabel("Username : ");
        l3.setBounds(120, 120, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l3);

        l4 = new JLabel("Password : ");
        l4.setBounds(120, 170, 150, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l4);

        t1 = new JTextField();
        t1.setBounds(320, 120, 150, 30);
        l1.add(t1);

        pf1 = new JPasswordField();
        pf1.setBounds(320, 170, 150, 30);
        l1.add(pf1);

        b1 = new JButton("Login");
        b1.setBounds(320, 220, 150, 40);

        b2 = new JButton("Back");
        b2.setBounds(120, 220, 150, 40);

        l1.add(b1);
        l1.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(580, 350);
        f.setResizable(false);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            try {
                String username1 = t1.getText();
                String password1 = pf1.getText();

                String query = "select * from login where Name = '" + username1 + "' and password = '" + password1
                        + "' and admin='YES';";
                ResultSet rs = obj.stm.executeQuery(query);

                if (rs.next()) {
                    f.dispose();
                    new Admin(obj);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details");
                    f.dispose();
                    new AdminLogin(obj);
                }
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ee.getSource() == b2) {
            f.dispose();
            new App();
        }
    }
}
