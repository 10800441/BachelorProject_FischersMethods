import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class RunSavedData extends JPanel{
    private int width = getWidth();
    private int heigth = 200;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(200, 200, 000, 180);

    private Color point2Color = new Color(00, 100, 00, 180);

    private Color point3Color = new Color(200, 00, 000, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 8;
    static ArrayList<CPU_SCORE> resultArray = new ArrayList<>();
    private static ArrayList<Stat> statIter = new ArrayList<>();
    private static ArrayList<Stat> statTime = new ArrayList<>();
    private static boolean drawMean;
    private static boolean drawScatter;
    private static boolean drawMeanT;
    private static boolean drawScatterT;
    private static boolean drawBoundTime;

    private static boolean drawBoundIter;


    public static void main(String[] args) {
        drawMean = true;
        drawMeanT = false;
        drawScatter = true;
        drawScatterT = false;
        drawBoundTime = false;
        drawBoundIter = true;
        String fileName = "out/data/FinalResults/ReductionGASAreduce.txt";
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
                resultArray.add(new CPU_SCORE(grid, Integer.valueOf(rawString[1]), Integer.valueOf(rawString[2]),Long.valueOf(rawString[3]),Double.valueOf(rawString[4]), Double.valueOf(rawString[5]),Double.valueOf(rawString[6]), Integer.valueOf(rawString[7])));
            }

            fr.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        System.out.println("Loading data completed, drawing graph BB");

        Visualise2.main("out/data/FinalResults/ShakeCities20_Ex100bo100GASAshake.txt", true,true);
    }

    private static void visualiseData(){


        ArrayList<Integer> pertrubation = new ArrayList<>();
        for (CPU_SCORE result : resultArray) {
            if (!pertrubation.contains(result.pertrubation)) pertrubation.add(result.pertrubation);
        }
        for(int i : pertrubation) {
            ArrayList<Double> calculateValuesIter = new ArrayList<>();
            ArrayList<Double> calculateValuesTime = new ArrayList<>();
            for (CPU_SCORE result : resultArray) {
                if (result.pertrubation == i) {
                    // percentage above the optimum

                    if(result.iterationsBB/1000000> 200)  {
                        makeCityList.printGrid(new JFrame(), result.grid);
                    }
                }
            }

            //
            double[] a = calculateLowerUpperConfidenceBoundary95Percent(calculateValuesIter);
            double[] b = calculateLowerUpperConfidenceBoundary95Percent(calculateValuesTime);

            statIter.add(new Stat(i, calculateValuesIter, a[0], a[1], a[2]));

            statTime.add(new Stat(i, calculateValuesTime, b[0], b[1], b[2]));
        }


        JFrame frame = new JFrame("Branch and Bound 30 cities");
        createAndShowGui2(frame);

    }


    // mean , lb, ub
    private static double[] calculateLowerUpperConfidenceBoundary95Percent(ArrayList<Double> givenNumbers) {

        // calculate the mean value (= average)
        double sum = 0.0;
        for (double num : givenNumbers) {
            sum += num;
        }
        double mean = sum / givenNumbers.size();

        // calculate standard deviation
        double squaredDifferenceSum = 0.0;
        for (double num : givenNumbers) {
            squaredDifferenceSum += (num - mean) * (num - mean);
        }
        double variance = squaredDifferenceSum / givenNumbers.size();
        double standardDeviation = Math.sqrt(variance);
        double confidenceLevel = 1.96;
        double temp = confidenceLevel * standardDeviation / Math.sqrt(givenNumbers.size());
        return new double[]{mean, mean - temp, mean + temp};
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / getMaxX();
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / getMaxY();

        java.util.List<Point> scatterPoints = new ArrayList<>();
        java.util.List<Point> meanPoints = new ArrayList<>();
        java.util.List<Point> lbPoints = new ArrayList<>();
        java.util.List<Point> ubPoints = new ArrayList<>();


        java.util.List<Point> scatterPointsT = new ArrayList<>();
        java.util.List<Point> meanPointsT = new ArrayList<>();
        java.util.List<Point> lbPointsT = new ArrayList<>();
        java.util.List<Point> ubPointsT = new ArrayList<>();

        ArrayList<Integer> pertrubation = new ArrayList<>();

        for (Stat data: statIter) {
            int x1 = (int) (data.pertrubation * xScale + padding + labelPadding);
            // y axis


            if(!pertrubation.contains(data.pertrubation)) pertrubation.add(data.pertrubation);
            if(drawScatter){
                for(double yCoord: data.yCoord){
                    int y =   getHeight() - (int) (yCoord * yScale + padding + labelPadding);
                    scatterPoints.add(new Point(x1, y));
                }
            }
            if(drawMean) {
                int y =   getHeight() - (int) (data.mean * yScale + padding + labelPadding);
                meanPoints.add(new Point(x1, y));
            }
            if(drawBoundIter) {
                int yub =   getHeight() - (int) (data.ub * yScale + padding + labelPadding);
                int ylb =   getHeight() - (int) (data.lb * yScale + padding + labelPadding);
                ubPoints.add(new Point(x1, yub));
                lbPoints.add(new Point(x1, ylb));


            }
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


            int x2 = width - padding - labelPadding;
            int x3 = width - padding;

            if (resultArray.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + pointWidth, y0, getWidth()- padding, y1);
                g2.setColor(Color.BLACK);

                if ((i % 5)  == 0) {
                    String yLabel = "" + i;
                    String yLabel2 = "" + i*100;
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
                if (i % 10 == 0 ) {

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
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth()- padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);




        if(drawScatter) {
            //yellow bb
            g2.setColor(point2Color);
            for (int i = 0; i < scatterPoints.size(); i++) {
                int x = scatterPoints.get(i).x - pointWidth / 2;
                int y = scatterPoints.get(i).y - pointWidth / 2;
                int ovalW = pointWidth;
                int ovalH = pointWidth;
                g2.fillOval(x, y, ovalW, ovalH);
            }
        }
        g2.setStroke(oldStroke);
        if(drawMean) {
            //yellow
            g2.setColor(Color.black);
            for (int i = 0; i < meanPoints.size()-1; i++) {
                int x1 = meanPoints.get(i).x;
                int y1 = meanPoints.get(i).y;
                int x2 = meanPoints.get(i+1).x;
                int y2 = meanPoints.get(i+1).y;
                g2.drawLine(x1, y1, x2, y2);
            }

            //green

        }
        if(drawBoundIter)
            g2.setColor(Color.gray);
        for (int i = 0; i < lbPoints.size()-1; i++) {
            int x1 = lbPoints.get(i).x;
            int y1 = lbPoints.get(i).y;
            int x2 = lbPoints.get(i+1).x;
            int y2 = lbPoints.get(i+1).y;
            Graphics2D g2d = (Graphics2D) g2.create();

            //set the stroke of the copy, not the original
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);

            //gets rid of the copy
            g2d.dispose();

            //g2.drawLine(x1, y1, x2, y2);
        }
        for (int i = 0; i < ubPoints.size()-1; i++) {
            int x1 = ubPoints.get(i).x;
            int y1 = ubPoints.get(i).y;
            int x2 = ubPoints.get(i+1).x;
            int y2 = ubPoints.get(i+1).y;
            // g2.drawLine(x1, y1, x2, y2);
            Graphics2D g2d = (Graphics2D) g2.create();
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);

            //gets rid of the copy
            g2d.dispose();
        }

        if(drawScatterT) {
            //yellow bb
            g2.setColor(point3Color);
            for (int i = 0; i < scatterPointsT.size(); i++) {
                int x = scatterPointsT.get(i).x - pointWidth / 2;
                int y = scatterPointsT.get(i).y - pointWidth / 2;
                int ovalW = pointWidth;
                int ovalH = pointWidth;
                g2.fillOval(x, y, ovalW, ovalH);
            }
        }
        g2.setStroke(oldStroke);
        if(drawMeanT) {
            //yellow
            g2.setColor(Color.blue);
            for (int i = 0; i < meanPointsT.size()-1; i++) {
                int x1 = meanPointsT.get(i).x;
                int y1 = meanPointsT.get(i).y;
                int x2 = meanPointsT.get(i+1).x;
                int y2 = meanPointsT.get(i+1).y;
                g2.drawLine(x1, y1, x2, y2);
            }

            //green

        }
        if(drawBoundTime)
            g2.setColor(Color.pink);
        for (int i = 0; i < lbPointsT.size()-1; i++) {
            int x1 = lbPointsT.get(i).x;
            int y1 = lbPointsT.get(i).y;
            int x2 = lbPointsT.get(i+1).x;
            int y2 = lbPointsT.get(i+1).y;
            Graphics2D g2d = (Graphics2D) g2.create();

            //set the stroke of the copy, not the original
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);

            //gets rid of the copy
            g2d.dispose();

            //g2.drawLine(x1, y1, x2, y2);
        }
        for (int i = 0; i < ubPointsT.size()-1; i++) {
            int x1 = ubPointsT.get(i).x;
            int y1 = ubPointsT.get(i).y;
            int x2 = ubPointsT.get(i+1).x;
            int y2 = ubPointsT.get(i+1).y;
            // g2.drawLine(x1, y1, x2, y2);
            Graphics2D g2d = (Graphics2D) g2.create();
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);

            //gets rid of the copy
            g2d.dispose();
        }




    }
    private int getMaxX() {
        int maxScore = 10;
        for (Stat s : statIter) {
            if ((int) s.pertrubation > maxScore) maxScore = (int) s.pertrubation;
        }
        return maxScore;
    }

    private double getMaxY() {
        int maxScore = 40;
        for (Stat s : statIter) {

                if ( s.ub > maxScore ) maxScore =  (int) s.ub;
        }
        return maxScore;
    }

    public static void createAndShowGui2(JFrame frame) {
        RunSavedData mainPanel = new RunSavedData();
        mainPanel.setPreferredSize(new Dimension(200, 200));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
