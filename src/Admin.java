import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Admin implements ActionListener {
    JFrame f;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
    ConnectionClass obj;

    Admin(ConnectionClass obj) {
        this.obj = obj;

        f = new JFrame("Admin");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b1 = new JButton("Lookup Driver");
        b1.setBounds(50, 50, 140, 120);
        f.add(b1);

        b2 = new JButton("Change Incentive");
        b2.setBounds(200, 50, 140, 120);
        f.add(b2);

        b3 = new JButton("Remove User");
        b3.setBounds(50, 180, 140, 120);
        f.add(b3);

        b4 = new JButton("Report");
        b4.setBounds(350, 180, 140, 120);
        f.add(b4);

        b5 = new JButton("Menu Edit");
        b5.setBounds(200, 180, 140, 120);
        f.add(b5);

        b6 = new JButton("Add admin");
        b6.setBounds(350, 50, 140, 120);
        f.add(b6);

        b7 = new JButton("Vehicle Category");
        b7.setBounds(50, 310, 140, 120);
        f.add(b7);

        b8 = new JButton("Food Category");
        b8.setBounds(200, 310, 140, 120);
        f.add(b8);

        b9 = new JButton("New User");
        b9.setBounds(350, 310, 140, 120);
        f.add(b9);

        b10 = new JButton("Log Out");
        b10.setBounds(50, 440, 140, 120);
        f.add(b10);

        b11 = new JButton("Remove Admin");
        b11.setBounds(50, 440, 140, 120);
        f.add(b11);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(550, 650);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            new Lookup(obj);
        } else if (ee.getSource() == b2) {
            new IncentiveEdit(obj);
        } else if (ee.getSource() == b3) {
            new RemoveUser(obj);
        } else if (ee.getSource() == b4) {
            new Report(obj);
        } else if (ee.getSource() == b5) {
            new Menu_System(obj).setVisible(true);
        } else if (ee.getSource() == b6) {
            new AdminSignUp(obj);
        } else if (ee.getSource() == b7) {
            new VehicleCategory(obj);
        } else if (ee.getSource() == b8) {
            new FoodCategory(obj);
        } else if (ee.getSource() == b10) {
            new App();
            f.dispose();
        } else if (ee.getSource() == b9) {
            new UserSignUp(obj);
        } else if (ee.getSource() == b11) {

        }
    }
}
