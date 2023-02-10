import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class VehicleCategory implements ActionListener, ItemListener {
    ConnectionClass obj;

    static JFrame f;
    JTable jTable1 = new JTable();

    static JButton b1, b2, b3, b4, b5, b6;
    static JLabel l1, l2, l3, l4, l5, l6;
    static JTextField t1, t2, t3, t4, t5, t6;
    static JComboBox<String> c1, c2;

    final int pad = 50;

    final int tx = 200;
    final int ty = 25;
    final int tw = 150;
    final int th = 35;

    final int lx = 50;
    final int ly = 20;
    final int lw = 150;
    final int lh = 50;

    public int pay = 0;

    VehicleCategory(ConnectionClass obj) {
        this.obj = obj;

        f = new JFrame("Edit Vehicle Category");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel("Category");
        l1.setBounds(lx, ly, lw, lh);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l1);

        Set<String> hs1 = new HashSet<String>();
        String query = "SELECT * FROM vehicle_category;";

        try {
            ResultSet rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                hs1.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        c1 = new JComboBox(hs1.toArray());
        c1.setBounds(lx, ly + pad, 100, 30);
        f.add(c1);
        c1.addItemListener(this);

        ArrayList<String> al1 = new ArrayList<String>();
        query = "SELECT * FROM incentive where type= '" + c1.getSelectedItem() + "';";

        try {
            ResultSet rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                al1.add(rs.getString(1));
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        c2 = new JComboBox(al1.toArray());
        c2.setBounds(tx, ly + pad, 100, 30);
        f.add(c2);
        c2.addItemListener(this);

        t1 = new JTextField();
        t1.setBounds(tx, ty, tw, th);
        f.add(t1);

        b1 = new JButton("Add");
        b1.setBounds(lx, ty + pad * 2, 150, 100);
        f.add(b1);

        b2 = new JButton("Delete");
        b2.setBounds(tx, ty + pad * 2, 150, 100);
        f.add(b2);

        b3 = new JButton("Back");
        b3.setBounds((lx + tx) / 2, ty + pad * 4, 150, 100);
        f.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Frame Size
        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == b1) {
            if (!t1.getText().equals("")) { // Aadhaar field cannot be empty if the driver is found
                // display table for the driver if found
                String q = "insert into vehicle_category values('" + t1.getText() + "');";
                int a;
                try {
                    a = obj.stm.executeUpdate(q);
                    if (a == 1) {
                        JOptionPane.showMessageDialog(null, "Category Added!");
                        c1.addItem(t1.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Enter a value.");
            }
        } else if (e.getSource() == b2) {
            String query = "Delete from vehicle_category WHERE category like '" + c1.getSelectedItem() + "';";
            String query2 = "Delete from incentive WHERE type like '" + c1.getSelectedItem() + "';";
            int aa, aaa;
            try {
                aa = obj.stm.executeUpdate(query);
                aaa = obj.stm.executeUpdate(query2);

                if (aa > 0 && aaa > 0) {
                    JOptionPane.showMessageDialog(null, "Category removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "User not found. Try Again.");
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == b3) {
            f.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == c1) {
            ArrayList<String> al1 = new ArrayList<String>();
            String query = "SELECT * FROM incentive where type= '" + c1.getSelectedItem() + "';";
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
            c2.removeAllItems();
            for (Object s : al1.toArray())
                c2.addItem((String) s);
        }
    }
}
