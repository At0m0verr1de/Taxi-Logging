import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DriverRegister implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3, t4;
    JButton b1, b2;
    ConnectionClass obj;

    DriverRegister(ConnectionClass obj) {
        this.obj = obj;

        f = new JFrame("Register Driver");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Aadhaar");
        l1.setBounds(40, 20, 1000, 30);
        f.add(l1);

        l2 = new JLabel("Name");
        l2.setBounds(40, 70, 1000, 30);
        f.add(l2);

        l3 = new JLabel("Phone No");
        l3.setBounds(40, 120, 1000, 30);
        f.add(l3);

        l4 = new JLabel("Plate");
        l4.setBounds(40, 170, 1000, 30);
        f.add(l4);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        f.add(t1);

        t2 = new JTextField();
        t2.setBounds(150, 70, 150, 30);
        f.add(t2);

        t3 = new JTextField();
        t3.setBounds(150, 120, 150, 30);
        f.add(t3);

        t4 = new JTextField();
        t4.setBounds(150, 170, 150, 30);
        f.add(t4);

        b1 = new JButton("Back");
        b1.setBounds(40, 240, 120, 30);
        f.add(b1);

        b2 = new JButton("Register");
        b2.setBounds(200, 240, 120, 30);
        f.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 340);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String id = t1.getText();
            String name = t2.getText();
            String phone = t3.getText();
            String plate = t4.getText();
            try {
                if (id.equals("") || name.equals("") || phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                } else {
                    String q1 = "insert into driver values('" + id + "','" + name + "','" + phone + "'," + "0,0, '"
                            + plate + "')";
                    int aa = obj.stm.executeUpdate(q1);

                    if (aa == 1) {
                        JOptionPane.showMessageDialog(null, "Account Created Successfully");
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                        this.f.setVisible(false);
                        this.f.setVisible(true);
                    }
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "User already exists.");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.dispose();
        }
    }
}
