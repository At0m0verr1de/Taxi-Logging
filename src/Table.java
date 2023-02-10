import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class Table {

    JFrame f;
    JTable jTable1;
    ConnectionClass obj;

    Table(String Aadhaar, ConnectionClass obj, String table) {
        this.obj = obj;

        f = new JFrame("User");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        jTable1 = new JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Aadhaar", "Name", "Phone", "Date", "Time",
                        "Plate", "Vehicle Cat.", "Passengers", "Payment", "Food", "Cost", "Op" }) {
            Class[] types = new Class[] {
                    String.class, String.class, String.class,
                    String.class, String.class, String.class,
                    String.class, String.class, String.class,
                    String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        // adding it to JScrollPane
        JScrollPane scrollPane = new JScrollPane(jTable1);
        jTable1.setFillsViewportHeight(true);

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Frame Size
        f.getContentPane();
        f.setVisible(true);
        f.setSize(1500, 650);

        String query;

        if (table.equals("")) {
            query = "SELECT Aadhaar, Name, Phone, Date, Time, Plate, vehicle_category, Passengers, Payment, food, cost, op FROM data where Aadhaar like '"
                    + Aadhaar + "';";
        } else {
            query = "SELECT Aadhaar, Name, Phone, Date, Time, Plate, vehicle_category, Passengers, Payment, food, cost, op FROM data;";
        }

        ResultSet rs;

        try {
            rs = obj.stm.executeQuery(query);
            Vector data = new Vector();
            ResultSetMetaData md = rs.getMetaData();
            int columns;
            columns = md.getColumnCount();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            while (rs.next()) {
                Vector row = new Vector<>(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                model.addRow(row);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
