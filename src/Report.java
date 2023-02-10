import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;

public class Report implements ActionListener {
    static JFrame f;
    JTable jTable1 = new JTable();
    static JButton b1, b2, b3, b4, b5, b6;
    static JLabel l1, l2, l3, l4, l5, l6;
    static JTextField t1, t2, t3, t4, t5, t6;
    ConnectionClass obj;

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

    public Report(ConnectionClass obj) {
        this.obj = obj;

        f = new JFrame("Report");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel("7 days");
        l1.setBounds(lx, ly, lw, lh);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l1);

        l2 = new JLabel("1 month");
        l2.setBounds(lx, ly + pad, lw, lh);
        l2.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l2);

        l3 = new JLabel("3 months");
        l3.setBounds(lx, ly + pad * 2, lw, lh);
        l3.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l3);

        l4 = new JLabel("6 months");
        l4.setBounds(lx, ly + pad * 3, lw, lh);
        l4.setFont(new Font("Arial", Font.BOLD, 20));

        f.add(l4);

        l5 = new JLabel("1 year");
        l5.setBounds(lx, ly + pad * 4, lw, lh);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(l5);

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
        f.add(t4);

        t5 = new JTextField();
        t5.setBounds(tx, ty + pad * 4, tw, th);
        f.add(t5);

        b1 = new JButton("Full Data");
        b1.setBounds(lx, ty + pad * 5, 150, 100);
        f.add(b1);

        b2 = new JButton("Graph");
        b2.setBounds(tx, ty + pad * 5, 150, 100);
        f.add(b2);

        b3 = new JButton("Back");
        b3.setBounds((lx + tx) / 2, ty + pad * 7, 150, 100);
        f.add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);

        LocalDate sevenDaysAgo = LocalDate.now().minus(Period.ofDays(7));
        String sql = "SELECT SUM(cost) FROM data WHERE date >= '" + sevenDaysAgo + "'";
        ResultSet rs;
        try {
            rs = obj.stm.executeQuery(sql);
            if (rs.next()) {
                t1.setText(rs.getDouble(1) + "");
            }

            // Get money spent in the past 1 month
            LocalDate oneMonthAgo = LocalDate.now().minus(Period.ofMonths(1));
            sql = "SELECT SUM(cost) FROM data WHERE date >= '" + oneMonthAgo + "'";
            rs = obj.stm.executeQuery(sql);
            if (rs.next()) {
                t2.setText(rs.getDouble(1) + "");
            }

            // Get money spent in the past 3 months
            LocalDate threeMonthsAgo = LocalDate.now().minus(Period.ofMonths(3));
            sql = "SELECT SUM(cost) FROM data WHERE date >= '" + threeMonthsAgo + "'";
            rs = obj.stm.executeQuery(sql);
            if (rs.next()) {
                t3.setText(rs.getDouble(1) + "");
            }

            // Get money spent in the past 6 months
            LocalDate sixMonthsAgo = LocalDate.now().minus(Period.ofMonths(6));
            sql = "SELECT SUM(cost) FROM data WHERE date >= '" + sixMonthsAgo + "'";
            rs = obj.stm.executeQuery(sql);
            if (rs.next()) {
                t4.setText(rs.getDouble(1) + "");
            }

            // Get money spent in the past 1 year
            LocalDate oneYearAgo = LocalDate.now().minus(Period.ofYears(1));
            sql = "SELECT SUM(cost) FROM data WHERE date >= '" + oneYearAgo + "'";
            rs = obj.stm.executeQuery(sql);
            if (rs.next()) {
                t5.setText(rs.getDouble(1) + "");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Frame Size
        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 550);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == b1) {
            new Table("", obj, "all");
        } else if (e.getSource() == b2) {
            new Graph();
        } else if (e.getSource() == b3) {
            f.dispose();
        }
    }
}
