import java.sql.*;
import javax.swing.*;

public class Search {
    // centralised searching for driver data
    private String n;
    private String a;
    private String p;
    private String c;
    private String pay;
    private String plate;
    ConnectionClass obj;

    Search(String Name, String Aadhaar, String Phone, String tName, ConnectionClass obj) {
        this.obj = obj;
        String query = "SELECT * FROM " + tName + " where Name like '" +
                Name + "' or Aadhaar like '" +
                Aadhaar + "' or Phone like '" +
                Phone + "';";
        ResultSet rs;
        try {
            rs = obj.stm.executeQuery(query);
            if (rs.next()) {
                pay = rs.getString(5);
                n = rs.getString(2);
                a = rs.getString(1);
                p = rs.getString(3);
                c = rs.getString(4);
                plate = rs.getString(6);
            } else {
                JOptionPane.showMessageDialog(null, "Driver not registered.");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getName() {
        return n;
    }

    public String getAadhaar() {
        return a;
    }

    public String getPhone() {
        return p;
    }

    public String getCredits() {
        return c;
    }

    public String getPay() {
        return pay;
    }

    public String getPlate() {
        return plate;
    }
}
