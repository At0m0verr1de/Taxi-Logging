import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

    private static final int COUNT = 100;
    private static final int UNITS = 5;

    Graph() {
        ArrayList<Integer> moneySpent = new ArrayList<Integer>();
        // populate moneySpent with data
        ConnectionClass obj = new ConnectionClass();
        String query = "SELECT * FROM data;";
        ResultSet rs;
        try {
            rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                moneySpent.add(Integer.parseInt(rs.getString(9)));
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < moneySpent.size(); i++) {
            series.add(i, moneySpent.get(i));
        }
        XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart("TickUnits",
                "Days", "Money Spent", data, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();

        final NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(365));

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(500, 270));

        JFrame frame = new JFrame("Money Spent per Day");
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}