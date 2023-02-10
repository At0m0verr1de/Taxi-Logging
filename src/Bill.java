import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Bill implements ActionListener, ItemListener {
    private JFrame f;
    private JButton b1, b2, b3, b4, b5, b6, b7;
    private JLabel l1, l2, l3, l4, l5, l6, l7;
    private JTextField t1, t2, t3, t4, t5, t6, t7;
    private JCheckBox cb3;
    private JComboBox<String> c1, c2;

    ConnectionClass obj;
    public Map<String, Integer> order = new HashMap<>();
    public int total = 0;

    final int pad = 50;

    final int tx = 200;
    final int ty = 25;
    final int tw = 150;
    final int th = 35;

    final int lx = 50;
    final int ly = 20;
    final int lw = 150;
    final int lh = 50;

    private int pay = 0;
    private String op = "";
    Order ord;

    Bill(String op, ConnectionClass obj) throws SQLException {
        this.op = op;
        this.obj = obj;
        ord = new Order(this, obj);

        f = new JFrame("User");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel("Name");
        l1.setBounds(lx, ly, lw, lh);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l1);

        l2 = new JLabel("Aadhaar");
        l2.setBounds(lx, ly + pad, lw, lh);
        l2.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l2);

        l3 = new JLabel("Phone");
        l3.setBounds(lx, ly + pad * 2, lw, lh);
        l3.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l3);

        l4 = new JLabel("Credits");
        l4.setBounds(lx, ly + pad * 3, lw, lh);
        l4.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l4);

        l5 = new JLabel("Payment");
        l5.setBounds(lx, ly + pad * 4, lw, lh);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l5);

        l6 = new JLabel("Passengers");
        l6.setBounds(lx, ly + pad * 5, lw, lh);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l6);

        l7 = new JLabel("Plate No.");
        l7.setBounds(lx, ly + pad * 6, lw, lh);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l7);

        t1 = new JTextField();
        t1.setBounds(tx, ty, tw, th);
        f.add(t1);

        t2 = new JTextField();
        t2.setBounds(tx, ty + pad, tw, th);
        f.add(t2);

        t3 = new JTextField();
        t3.setBounds(tx, ty + pad * 2, tw, th);
        f.add(t3);

        t4 = new JTextField();
        t4.setBounds(tx, ty + pad * 3, tw, th);
        t4.setEditable(false);
        f.add(t4);

        t5 = new JTextField();
        t5.setBounds(tx, ty + pad * 4, tw, th);
        t5.setEditable(false);
        f.add(t5);

        t6 = new JTextField();
        t6.setBounds(tx, ty + pad * 5, tw, th);
        f.add(t6);

        t7 = new JTextField();
        t7.setBounds(tx, ty + pad * 6, tw, th);
        f.add(t7);

        b1 = new JButton("Find");
        b1.setBounds(50, 390, 150, 100);
        f.add(b1);

        b2 = new JButton("Register");
        b2.setBounds(220, 390, 150, 100);
        f.add(b2);

        b3 = new JButton("Register Visit");
        b3.setBounds(220, 510, 150, 100);
        f.add(b3);

        b4 = new JButton("Clear");
        b4.setBounds(50, 510, 150, 100);
        f.add(b4);

        b5 = new JButton("Order");
        b5.setBounds(390, 390, 150, 100);
        f.add(b5);

        b6 = new JButton("Logout");
        b6.setBounds(390, 510, 150, 100);
        f.add(b6);

        b7 = new JButton("Data");
        b7.setBounds(220, 630, 150, 100);
        f.add(b7);

        // cb1 = new JCheckBox("Food Slip");
        // cb1.setBounds(tx + tw + pad, ly + 20, 150, 20);
        // f.add(cb1);

        // cb2 = new JCheckBox("Tea Slip");
        // cb2.setBounds(tx + tw + pad, ly + 95, 150, 20);
        // f.add(cb2);

        cb3 = new JCheckBox("Incentive");
        cb3.setBounds(tx + tw + pad, ly + 190, 150, 20);
        f.add(cb3);

        // incentive value drop down
        ArrayList<String> al1 = new ArrayList<String>();

        // incentive value drop down
        String query = "SELECT * FROM vehicle_category;";
        ResultSet rs = obj.stm.executeQuery(query);
        while (rs.next()) {
            al1.add(rs.getString(1));
        }
        c2 = new JComboBox(al1.toArray());
        c2.setBounds(tx + tw + pad - 10, ly + 110, 150, 30);
        f.add(c2);
        c2.addItemListener(this);

        query = "SELECT * FROM incentive where type like '" + c2.getSelectedItem() + "';";
        al1 = new ArrayList<String>();
        rs = obj.stm.executeQuery(query);
        while (rs.next()) {
            al1.add(rs.getString(1));
        }
        c1 = new JComboBox(al1.toArray());
        c1.setBounds(tx + tw + pad - 10, ly + 150, 150, 30);
        f.add(c1);
        c1.addItemListener(this);

        // Action listeners for all the buttons on screen
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(600, 800);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            Search s = new Search(t1.getText(), t2.getText(), t3.getText(), "driver", obj);
            if (!s.getAadhaar().equals("")) { // Aadhaar field cannot be empty if the driver is found
                pay = Integer.parseInt(s.getPay());
                t1.setText(s.getName());
                t2.setText(s.getAadhaar());
                t3.setText(s.getPhone());
                t4.setText(s.getCredits());
                t5.setText(s.getPay());
                t7.setText(s.getPlate());
                t1.setEditable(false);
                t2.setEditable(false);
                t3.setEditable(false);

            } else {
                JOptionPane.showMessageDialog(null, "Driver not registered.");
            }
        } else if (ee.getSource() == b2) {
            new DriverRegister(obj);
        } else if (ee.getSource() == b3) {
            String Name = t1.getText();
            String Aadhaar = t2.getText();
            String Phone = t3.getText();
            String Credits = t4.getText();
            int Pending = Integer.parseInt(t5.getText());
            String Passengers = t6.getText();
            String Plate = t7.getText();
            // if (cb3.isSelected()) {
            // new printBill().incentive(Pending);
            // }

            try {
                if (Name.isEmpty() || Aadhaar.isEmpty() || Phone.isEmpty() || Passengers.isEmpty() || Plate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now).substring(0, 10);
                    String time = dtf.format(now).substring(11);
                    String temp;

                    if (Pending - Integer.parseInt((String) c1.getSelectedItem()) == 0 && cb3.isSelected()
                            && total != 0) {
                        temp = "Single, Food";
                    } else if (Pending - Integer.parseInt((String) c1.getSelectedItem()) == 0 && cb3.isSelected()
                            && total == 0) {
                        temp = "Single";
                    } else if (Pending - Integer.parseInt((String) c1.getSelectedItem()) != 0 && cb3.isSelected()
                            && total != 0) {
                        temp = "Double, Food";
                    } else if (Pending - Integer.parseInt((String) c1.getSelectedItem()) != 0 && cb3.isSelected()
                            && total == 0) {
                        temp = "Double";
                    } else {
                        temp = "Food";
                    }

                    String q = "insert into data values('" + Aadhaar + "','" + Name + "','" + Phone + "',"
                            + Integer.parseInt(Passengers)
                            + ",'" + date + "','" + time + "', " + (cb3.isSelected() ? Pending : 0) + ",'"
                            + order.toString() + "',"
                            + total + ",'" + op + "', '" + c2.getSelectedItem() + "', '" + Plate + "', '" + temp
                            + "')";
                    int a = obj.stm.executeUpdate(q);
                    if (a == 1) {
                        String q1;
                        if (cb3.isSelected()) {
                            q1 = "update driver set pay=" + 0 + " where Aadhaar like '" + Aadhaar + "';";
                        } else {
                            q1 = "update driver set pay=" + Pending + " where Aadhaar like '" + Aadhaar + "';";
                        }
                        int aa, aaa;
                        String q2;
                        if (Integer.parseInt(Passengers) > 0) {
                            q2 = "update driver set credits=" + (Integer.parseInt(Credits) + 1)
                                    + " where Aadhaar like '" + Aadhaar + "';";
                            aaa = obj.stm.executeUpdate(q2);

                        }
                        aa = obj.stm.executeUpdate(q1);

                        if (aa > 0) {
                            JOptionPane.showMessageDialog(null, "Entry Saved!");
                            c1.setSelectedIndex(0);
                            c2.setSelectedIndex(0);
                            t1.setText("");
                            t2.setText("");
                            t3.setText("");
                            t4.setText("");
                            t5.setText("");
                            t6.setText("");
                            t7.setText("");
                            t1.setEditable(true);
                            t2.setEditable(true);
                            t3.setEditable(true);
                            ord = new Order(this, obj);
                            total = 0;
                            order = new HashMap<>();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                        f.setVisible(false);
                        f.setVisible(true);
                    }
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "User already exists.");
            } catch (Throwable e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Try Again.");
            }
        } else if (ee.getSource() == b4) {
            c1.setSelectedIndex(0);
            c2.setSelectedIndex(0);
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
            t7.setText("");
            t1.setEditable(true);
            t2.setEditable(true);
            t3.setEditable(true);
        } else if (ee.getSource() == b5) {
            ord.setVisible(true);
        } else if (ee.getSource() == b6) {
            f.dispose();
            new UserLogin(obj);
        } else if (ee.getSource() == b7) {
            Search s = new Search(t1.getText(), t2.getText(), t3.getText(), "driver", obj);
            if (!s.getAadhaar().equals("")) { // Aadhaar field cannot be empty if the driver is found
                // display table for the driver if found
                t1.setText(s.getName());
                t2.setText(s.getAadhaar());
                t3.setText(s.getPhone());
                t4.setText(s.getCredits());
                t5.setText(s.getPay());
                t6.setText(s.getPlate());
                new Table(s.getAadhaar(), obj, "");
            } else {
                JOptionPane.showMessageDialog(null, "Driver not registered.");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == c1) {
            t5.setText(((Integer.parseInt(c1.getSelectedItem().toString()) + pay) + ""));
            System.out.println("Done");
        } else if (e.getSource() == c2) {
            c1.removeItemListener(this);
            ArrayList<String> al1 = new ArrayList<String>();
            String query = "SELECT * FROM incentive where type= '" + c2.getSelectedItem() + "';";
            try {
                ResultSet rs = obj.stm.executeQuery(query);
                while (rs.next()) {
                    al1.add(rs.getString(1));
                }
                rs.close();
            } catch (SQLException ee) {
                // TODO Auto-generated catch block
                ee.printStackTrace();
            }
            c1.removeAllItems();
            for (Object s : al1.toArray())
                c1.addItem((String) s);
            c1.addItemListener(this);
            t5.setText(((Integer.parseInt(c1.getSelectedItem().toString()) + pay) + ""));
        }
    }

}
