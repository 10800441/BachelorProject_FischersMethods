//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
///**
// *
// */
//public class Visualise extends JPanel{
//    private int width = 200;
//    private int heigth = 200;
//    private int padding = 25;
//    private int labelPadding = 25;
//    private Color lineColor = new Color(44, 102, 230, 180);
//    private Color pointColor = new Color(200, 200, 000, 180);
//
//    private Color point2Color = new Color(00, 100, 00, 180);
//
//    private Color point3Color = new Color(200, 00, 000, 180);
//    private Color gridColor = new Color(200, 200, 200, 200);
//    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
//    private int pointWidth = 8;
//    static ArrayList<CPU_SCORE> resultArray = new ArrayList<>();
//    private static boolean drawMean;
//    private static boolean drawScatter;
//    private static ArrayList<Double> statistics = new ArrayList<>();
//
//
//    public static void main(String fileName, boolean drawMean2, boolean drawScatter2) {
//        drawMean = drawMean2;
//        drawScatter = drawScatter2;
//        System.out.println("Visualising " + fileName);
//        try {
//            FileReader fr = new FileReader(fileName);
//            BufferedReader br = new BufferedReader(fr);
//            String s;
//            while ((s = br.readLine()) != null) {
//                ArrayList<City> grid = new ArrayList<>();
//                // use semicolon as separator
//                String[] rawString = s.split(";");
//
//                // seperate all cities
//                String[] rawCities = rawString[0].split("-");
//
//                for(String cityStr :  rawCities){
//                    String[] city = cityStr.split(",");
//                    grid.add(new City(Integer.valueOf(city[0]), Integer.valueOf(city[1])));
//                }
//
//
//
//                // format:  ArrayList<City> grid, int pertrubation, int iterationsBB, long timeBB, double optScore, double scoreGA, int maxIterGA
//                resultArray.add(new CPU_SCORE(grid, Integer.valueOf(rawString[1]), Integer.valueOf(rawString[2]),Integer.valueOf(rawString[3]),Double.valueOf(rawString[4]), Integer.valueOf(rawString[5]), Long.valueOf(rawString[6]), Double.valueOf(rawString[7]), Double.valueOf(rawString[8]), Integer.valueOf(rawString[9])));
//            }
//
//            fr.close();
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        System.out.println("Loading data completed, drawing graph");
//
//        JFrame frame = new JFrame("Reduction");
//        createAndShowGui(frame);
//
//    }
//
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / getMaxX();
//            double yScale = ((double) getHeight() - 2 * padding - labelPadding) / getMaxY();
//
//            java.util.List<Point> graphBBPoints = new ArrayList<>();
//
//            java.util.List<Point> graphHKPoints = new ArrayList<>();
//
//            java.util.List<Point> graphGAPoints = new ArrayList<>();
//            java.util.List<Point> graphMeanPoints = new ArrayList<>();
//            ArrayList<Integer> pertrubation = new ArrayList<>();
//
//            for (CPU_SCORE result : resultArray) {
//                statistics.add((result.scoreGA/result.optScoreBB)*100);
//
//                if (!pertrubation.contains(result.pertrubation)) pertrubation.add(result.pertrubation);
//                // x axis iterationsBB
//                int x1 = (int) (result.pertrubation * xScale + padding + labelPadding);
//                // y axis
//                int yBB =   getHeight() - (int) ((result.optScoreBB) * yScale + padding + labelPadding);
//                int yHK =   getHeight() - (int) ((result.optScoreHK) * yScale + padding + labelPadding);
//                int yGA =   getHeight() - (int) ((result.scoreGA) * yScale + padding + labelPadding);
//                graphBBPoints.add(new Point(x1, yBB,result.pertrubation,  (int) result.optScoreBB));
//                graphHKPoints.add(new Point(x1, yHK,result.pertrubation,  (int) result.optScoreHK));
//                graphGAPoints.add(new Point(x1, yGA,result.pertrubation,  (int) result.scoreGA));
//            }
//
//
//            // draw white background
//            g2.setColor(Color.WHITE);
//            g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
//            g2.setColor(Color.BLACK);
//
//            // create hatch marks and grid lines for y axis.
//            for (int i = 0; i < getMaxY() +1; i++) {
//                int x0 = padding + labelPadding;
//                int x1 = pointWidth + padding + labelPadding;
//                int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / (int) getMaxY() + padding + labelPadding);
//                int y1 = y0;
//                if (resultArray.size() > 0) {
//                    g2.setColor(gridColor);
//                    g2.drawLine(padding + labelPadding + pointWidth, y0, getWidth() - padding, y1);
//                    g2.setColor(Color.BLACK);
//
//                    if ((i % ((int) ((resultArray.size() / 7.0)) + 1)) == 0) {
//                        String yLabel = "" + i;
//
//                        FontMetrics metrics = g2.getFontMetrics();
//                        int labelWidth = metrics.stringWidth(yLabel);
//                        g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
//                    }
//                }
//                g2.drawLine(x0, y0, x1, y1);
//            }
//
//            // and for x axis
//            for (int i = 0; i < getMaxX(); i++) {
//                if (resultArray.size() > 1) {
//                    int x0 = padding + labelPadding + (i * (getWidth() - padding * 2 - labelPadding) / getMaxX());
//                    int x1 = x0;
//                    int y0 = getHeight() - padding - labelPadding;
//                    int y1 = y0 - pointWidth;
//                    if ((i % ((int) ((resultArray.size() / 20.0)) + 1)) == 0) {
//                        g2.setColor(gridColor);
//                        g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
//                        g2.setColor(Color.black);
//                        String xLabel = i + "";
//                        FontMetrics metrics = g2.getFontMetrics();
//                        int labelWidth = metrics.stringWidth(xLabel);
//                        g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
//                    }
//                    g2.drawLine(x0, y0, x1, y1);
//                }
//            }
//
//            // create x and y axes
//            g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
//            g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
//
//            Stroke oldStroke = g2.getStroke();
//            g2.setColor(lineColor);
//            g2.setStroke(GRAPH_STROKE);
//
//
//            // drawing mlines (optional)
//
////            if(tour != null){
////                for (int i = 0; i < graphPoints.size()-1; i++) {
////
////                    int x1 = graphPoints.get(i).x;
////                    int y1 = graphPoints.get(i).y;
////                    int x2 = graphPoints.get(i+1).x;
////                    int y2 = graphPoints.get(i+1).y;
////                    g2.drawLine(x1, y1, x2, y2);
////                }
////            }
//
//            g2.setStroke(oldStroke);
//
//        if(drawScatter) {
//            //yellow bb
//            g2.setColor(pointColor);
//            for (int i = 0; i < graphBBPoints.size(); i++) {
//                int x = graphBBPoints.get(i).x - pointWidth / 2;
//                int y = graphBBPoints.get(i).y - pointWidth / 2;
//                int ovalW = pointWidth;
//                int ovalH = pointWidth;
//                g2.fillOval(x, y, ovalW, ovalH);
//            }
//            //green hk
//            g2.setColor(point2Color);
//            for (int i = 0; i < graphHKPoints.size(); i++) {
//                int x = graphHKPoints.get(i).x - pointWidth / 2;
//                int y = graphHKPoints.get(i).y - pointWidth / 2;
//                int ovalW = pointWidth;
//                int ovalH = pointWidth;
//                g2.fillOval(x, y, ovalW, ovalH);
//            }
//            //red ga
//            g2.setColor(point3Color);
//            for (int i = 0; i < graphGAPoints.size(); i++) {
//                int x = graphGAPoints.get(i).x - pointWidth / 2;
//                int y = graphGAPoints.get(i).y - pointWidth / 2;
//                int ovalW = pointWidth;
//                int ovalH = pointWidth;
//                g2.fillOval(x, y, ovalW, ovalH);
//            }
//
//        }
//            if (drawMean) {
//                java.util.List<Point> meangraphBBPoints = new ArrayList<>();
//
//                java.util.List<Point> meangraphHKPoints = new ArrayList<>();
//
//                java.util.List<Point> meangraphGAPoints = new ArrayList<>();
//                for(int i = 0; i < getMaxX()+1; i++) {
//                    int pointi = (int) (i * xScale + padding + labelPadding);
//                    int meanBB = -1;
//                    int meanHK = -1;
//                    int meanGA = -1;
//
//                    for (Point p : graphBBPoints) {
//                        if (p.realx == i) {
//                            if(meanBB == -1) meanBB = 0;
//                            System.out.println(p.realx);
//                            meanBB +=p.realy;
//                        }
//                    }
//                    for (Point p : graphHKPoints) {
//                        if (p.realx == i) {
//                            if(meanHK == -1) meanHK = 0;
//                            System.out.println(p.realx);
//                            meanHK+=p.realy;
//                        }
//                    }
//                    for (Point p : graphGAPoints) {
//                        if (p.realx == i) {
//                            if(meanGA == -1) meanGA = 0;
//                            System.out.println(p.realx);
//                            meanGA+=p.realy;
//                        }
//                    }
//
//                    if(meanBB !=-1) {
//                        int newy = getHeight() - ((int) ((meanBB / (graphBBPoints.size()/pertrubation.size()))*yScale + padding + labelPadding));
//                        meangraphBBPoints.add(new Point(pointi,newy, meanBB, meanBB));
//                    }
//                    if(meanHK !=-1) {
//                        int newy = getHeight() - ((int) ((meanHK / (graphHKPoints.size()/pertrubation.size()))*yScale + padding + labelPadding));
//                        meangraphHKPoints.add(new Point(pointi,newy, meanHK, meanHK));
//                    }
//                    if(meanGA !=-1) {
//                        int newy = getHeight() - ((int) ((meanGA / (graphGAPoints.size()/pertrubation.size()))*yScale + padding + labelPadding));
//                        meangraphGAPoints.add(new Point(pointi,newy, meanGA, meanGA));
//                    }
//                }
//
//                // draw lines
//                //yellow
//                g2.setColor(pointColor);
//                for (int i = 0; i < meangraphBBPoints.size()-1; i++) {
//                    int x1 = meangraphBBPoints.get(i).x;
//                    int y1 = meangraphBBPoints.get(i).y;
//                    int x2 = meangraphBBPoints.get(i+1).x;
//                    int y2 = meangraphBBPoints.get(i+1).y;
//                    g2.drawLine(x1, y1, x2, y2);
//                }
////                //green
////                g2.setColor(point2Color);
////                for (int i = 0; i < meangraphHKPoints.size()-1; i++) {
////                    int x1 = meangraphHKPoints.get(i).x;
////                    int y1 = meangraphHKPoints.get(i).y;
////                    int x2 = meangraphHKPoints.get(i+1).x;
////                    int y2 = meangraphHKPoints.get(i+1).y;
////                    g2.drawLine(x1, y1, x2, y2);
////                }
//                //red
//                g2.setColor(point3Color);
//                for (int i = 0; i < meangraphGAPoints.size()-1; i++) {
//                    int x1 = meangraphGAPoints.get(i).x;
//                    int y1 = meangraphGAPoints.get(i).y;
//                    int x2 = meangraphGAPoints.get(i+1).x;
//                    int y2 = meangraphGAPoints.get(i+1).y;
//                    g2.drawLine(x1, y1, x2, y2);
//                }
//
//            }
//        }
//
//    private int getMaxX() {
//        int maxScore = 0;
//        for (CPU_SCORE r : resultArray) {
//            if(r.pertrubation > maxScore) maxScore = (int) r.pertrubation;
//        }
//        return maxScore;
//    }
//
//    private double getMaxY() {
//        int maxScore = 0;
//        for (CPU_SCORE r : resultArray) {
//            if(r.scoreGA > maxScore) maxScore = (int) r.scoreGA ;
//
//            if(r.optScoreBB > maxScore) maxScore = (int) r.optScoreBB ;
//
//            if(r.optScoreHK > maxScore) maxScore = (int) r.optScoreHK ;
//        }
//        return maxScore;
//    }
//
//    public static void createAndShowGui(JFrame frame) {
//         Visualise mainPanel = new Visualise();
//        mainPanel.setPreferredSize(new Dimension(200, 200));
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(mainPanel);
//        frame.setSize(300,300);
//        frame.setVisible(true);
//    }
// }
