import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1;
    JButton b1, b2;
    static ConnectionClass obj = new ConnectionClass();

    App() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        f = new JFrame("Taxi Service");
        f.setLayout(null);

        b1 = new JButton("Admin");
        b1.setBounds(40, 50, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("User");
        b2.setBounds(200, 50, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane();
        f.setVisible(true);
        f.setSize(350, 150);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.setVisible(false);
            new AdminLogin(obj);
        } else if (ae.getSource() == b2) {
            f.setVisible(false);
            new UserLogin(obj);
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_System.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_System.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_System.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_System.class.getName()).log(
                    java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}
