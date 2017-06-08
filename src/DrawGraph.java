import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/****************************************************
 **                Bachelor project                **
 ** DrawGraph.java                                 **
 ** Source: http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
 ** Adjusted to work for a TSP instantiation       **
 ****************************************************
 */

public class DrawGraph extends JPanel {

    private int width = 200;
    private int heigth = 200;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 8;
    private static ArrayList<City> tour;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / getMaxX();
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / getMaxY();

        List<Point> graphPoints = new ArrayList<>();
        for (City city: tour){
            int x1 = (int) (city.x * xScale + padding + labelPadding);
            int y1 = (int) (city.y * yScale + padding);
          //  graphPoints.add(new Point(x1, y1));
        }


        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.


        for (int i = 0; i < getMaxY() + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / (int) getMaxY() + padding + labelPadding);
            int y1 = y0;
            if (tour.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = "" + i;
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < getMaxX(); i++) {
            if (tour.size() > 1) {
                int x0 = padding + labelPadding + (i * (getWidth() - padding * 2 - labelPadding)/getMaxX());
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((tour.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.black);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        // Closing the tour
        int startx = (int) graphPoints.get(graphPoints.size()-1).x;
        int starty = (int) graphPoints.get(graphPoints.size()-1).y;
        int endx = (int) graphPoints.get(0).x;
        int endy = (int) graphPoints.get(0).y;
         g2.drawLine(startx, starty, endx, endy);

        if(tour != null){
            for (int i = 0; i < graphPoints.size()-1; i++) {

                int x1 = graphPoints.get(i).x;
                int y1 = graphPoints.get(i).y;
                int x2 = graphPoints.get(i+1).x;
                int y2 = graphPoints.get(i+1).y;
                g2.drawLine(x1, y1, x2, y2);
            }
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    private int getMaxX() {
        int maxScore = 0;
        for (City city: tour) {
            if(city.x > maxScore) maxScore = city.x;
        }
        return maxScore+1;
    }

    private double getMaxY() {
        int maxScore = 0;
        for (City city: tour) {
            if(city.y > maxScore) maxScore = city.y;
        }
        return maxScore+1;
    }

    public static void createAndShowGui(JFrame frame, ArrayList<City> solGrid) {
        tour = solGrid;

        DrawGraph mainPanel = new DrawGraph();
        mainPanel.setPreferredSize(new Dimension(200, 200));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setSize(300,300);
        frame.setVisible(true);
    }




}