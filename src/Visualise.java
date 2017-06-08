import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Visualise extends JPanel{
    private int width = 200;
    private int heigth = 200;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 8;
    static ArrayList<CPU_SCORE> resultArray = new ArrayList<>();
    private static boolean drawMean;
    private static boolean drawScatter;
    private static ArrayList<Double> statistics = new ArrayList<>();


    public static void main(String fileName, boolean drawMean2, boolean drawScatter2) {
        drawMean = drawMean2;
        drawScatter = drawScatter2;
        System.out.println("Visualising " + fileName);
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                ArrayList<City> grid = new ArrayList<>();
                // use semicolon as separator
                String[] rawString = s.split(";");

                // seperate all cities
                String[] rawCities = rawString[0].split("-");

                for(String cityStr :  rawCities){
                    String[] city = cityStr.split(",");
                    grid.add(new City(Integer.valueOf(city[0]), Integer.valueOf(city[1])));
                }



                // format:  ArrayList<City> grid, int pertrubation, int iterationsBB, long timeBB, double optScore, double scoreGA, int maxIterGA
                resultArray.add(new CPU_SCORE(grid, Integer.valueOf(rawString[1]), Integer.valueOf(rawString[2]), Long.valueOf(rawString[3]), Double.valueOf(rawString[4]), Double.valueOf(rawString[5]), Integer.valueOf(rawString[6])));
            }

            fr.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println("Loading data completed, drawing graph");

        JFrame frame = new JFrame("Reduction");
        createAndShowGui(frame);

    }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / getMaxX();
            double yScale = ((double) getHeight() - 2 * padding - labelPadding) / getMaxY();

            java.util.List<Point> graphPoints = new ArrayList<>();
            java.util.List<Point> graphMeanPoints = new ArrayList<>();
            ArrayList<Integer> pertrubation = new ArrayList<>();

            for (CPU_SCORE result : resultArray) {
                statistics.add((result.scoreGA/result.optScore)*100);
                System.out.println("value " + (result.scoreGA/result.optScore)*100);
                if (!pertrubation.contains(result.pertrubation)) pertrubation.add(result.pertrubation);
                // x axis iterationsBB
                int x1 = (int) (result.pertrubation * xScale + padding + labelPadding);
                // y axis
                int y1 =   getHeight() - (int) (((result.scoreGA/result.optScore)*100) * yScale + padding + labelPadding);
                graphPoints.add(new Point(x1, y1,result.pertrubation,  (int) (result.scoreGA/result.optScore)*100));
            }


            // draw white background
            g2.setColor(Color.WHITE);
            g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
            g2.setColor(Color.BLACK);

            // create hatch marks and grid lines for y axis.
            for (int i = 0; i < getMaxY() +1; i++) {
                int x0 = padding + labelPadding;
                int x1 = pointWidth + padding + labelPadding;
                int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / (int) getMaxY() + padding + labelPadding);
                int y1 = y0;
                if (resultArray.size() > 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(padding + labelPadding + pointWidth, y0, getWidth() - padding, y1);
                    g2.setColor(Color.BLACK);

                    if ((i % ((int) ((resultArray.size() / 7.0)) + 1)) == 0) {
                        String yLabel = "" + i;

                        FontMetrics metrics = g2.getFontMetrics();
                        int labelWidth = metrics.stringWidth(yLabel);
                        g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
                    }
                }
                g2.drawLine(x0, y0, x1, y1);
            }

            // and for x axis
            for (int i = 0; i < getMaxX(); i++) {
                if (resultArray.size() > 1) {
                    int x0 = padding + labelPadding + (i * (getWidth() - padding * 2 - labelPadding) / getMaxX());
                    int x1 = x0;
                    int y0 = getHeight() - padding - labelPadding;
                    int y1 = y0 - pointWidth;
                    if ((i % ((int) ((resultArray.size() / 20.0)) + 1)) == 0) {
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


            // drawing mlines (optional)

//            if(tour != null){
//                for (int i = 0; i < graphPoints.size()-1; i++) {
//
//                    int x1 = graphPoints.get(i).x;
//                    int y1 = graphPoints.get(i).y;
//                    int x2 = graphPoints.get(i+1).x;
//                    int y2 = graphPoints.get(i+1).y;
//                    g2.drawLine(x1, y1, x2, y2);
//                }
//            }

            g2.setStroke(oldStroke);
            g2.setColor(pointColor);
        if(drawScatter) {
            for (int i = 0; i < graphPoints.size(); i++) {
                int x = graphPoints.get(i).x - pointWidth / 2;
                int y = graphPoints.get(i).y - pointWidth / 2;
             int ovalW = pointWidth;
             int ovalH = pointWidth;
                g2.fillOval(x, y, ovalW, ovalH);
            }

        }
            if (drawMean) {
                for(int i = 0; i < getMaxX()+1; i++) {
                    int pointi = (int) (i * xScale + padding + labelPadding);
                    int mean = -1;

                    for (Point p : graphPoints) {
                        if (p.realx == i) {
                           if(mean == -1) mean = 0;
                            System.out.println(p.realx);
                            mean+=p.realy;
                        }
                    }
                    if(mean !=-1) {
                        int newy = getHeight() - ((int) ((mean / (graphPoints.size()/pertrubation.size()))*yScale + padding + labelPadding));
                        graphMeanPoints.add(new Point(pointi,newy, mean, mean));
                    }
                }

                // draw lines
                for (int i = 0; i < graphMeanPoints.size()-1; i++) {
                    int x1 = graphMeanPoints.get(i).x;
                    int y1 = graphMeanPoints.get(i).y;
                    int x2 = graphMeanPoints.get(i+1).x;
                    int y2 = graphMeanPoints.get(i+1).y;
                    g2.drawLine(x1, y1, x2, y2);
                }

            }
        }

    private int getMaxX() {
        int maxScore = 0;
        for (CPU_SCORE r : resultArray) {
            if(r.pertrubation > maxScore) maxScore = (int) r.pertrubation;
        }
        return maxScore;
    }

    private double getMaxY() {
        int maxScore = 0;
        for (CPU_SCORE r : resultArray) {
            if(r.timeBB > maxScore) maxScore = (int) r.timeBB ;
        }
        return maxScore;
    }

    public static void createAndShowGui(JFrame frame) {
         Visualise mainPanel = new Visualise();
        mainPanel.setPreferredSize(new Dimension(200, 200));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
 }
