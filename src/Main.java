import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.Comparator;


// StringLengthComparator.java
import java.util.Comparator;
/**
 * Created by marty_000 on 22-5-2017.
 */
public class Main {


    // Sizes 6 12 16 20 30 36 42


    // operators: reduction or shake
    final static String operator = "Shake";
    final static String fileId = "ShakeC16GA";
    final static int TOTALCITIES = 16;
    final static int XY_DISTANCE = 12;
    final static boolean drawScatter = true;
    final static boolean drawMeans = true;


    final static ArrayList<City> emptyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);
   static double[][] cm = makeCityList.calculateCostMatrix(emptyGrid);
    static int repeatingExperiments = 100;
    final static int iterationsBoundGA= 100;

    public static void main(String[] args) {


        ArrayList<Integer> preservation = new ArrayList<>();
        preservation.add(99);
        preservation.add(98);
        preservation.add(95);
        preservation.add(90);
        preservation.add(85);
        preservation.add(75);
        preservation.add(50);
        preservation.add(25);

        ArrayList<Integer> shaked = new ArrayList<>();
        shaked.add(5);
        shaked.add(10);
        shaked.add(25);
        shaked.add(50);
        shaked.add(75);
        shaked.add(100);
        shaked.add(150);
        shaked.add(200);
        // Reduction



        // System.out.println("Saving files as" + fileName);
        String fileName = "out/data/Systematic/" + operator + "Cities"+ TOTALCITIES + "_Ex" +repeatingExperiments +"bo"+ iterationsBoundGA + fileId+ ".txt";
        ArrayList<ArrayList<CPU_SCORE>> matrixArray = new ArrayList<>();

//        if(operator.equals("Shake")) {
//            matrixArray = reductionOperator(preservation);
//        } else {
            matrixArray = shakeOperator(shaked);
//        }

        System.out.println("Saving files as: " + fileName);
        saveResult(matrixArray, fileName);
        Visualise2.main(fileName, drawMeans, drawScatter);
    }

    private static ArrayList<ArrayList<CPU_SCORE>> reductionOperator(ArrayList<Integer> preservation) {
        ArrayList<ArrayList<CPU_SCORE>> reductionResult = new ArrayList<>();

        for (int preservance : preservation) {
            System.out.println("Preservance level: " + preservance);
            ArrayList<CPU_SCORE> a = new ArrayList<>();

            for (int no = 0; no < repeatingExperiments; no++) {

                ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, preservance);

                BBresult bbRes = BranchBound.main(reducedGrid);
             // double scoreGA = TSP_GA.TSP_GA(reducedGrid, iterationsBoundGA, bbRes.timeBB);

                double scoreSA = SA.SA(reducedGrid, iterationsBoundGA, bbRes.timeBB);
                a.add(new CPU_SCORE(reducedGrid, preservance,   bbRes.iterationsBB, bbRes.timeBB, bbRes.optScore, scoreSA, iterationsBoundGA));
            }
            reductionResult.add(a);
        }

        return reductionResult;
    }

    private static ArrayList<ArrayList<CPU_SCORE>> shakeOperator(ArrayList<Integer> shaked) {
        ArrayList<ArrayList<CPU_SCORE>> shakeResult = new ArrayList<>();

        for (int shake : shaked) {
            System.out.println("Shake level: " + shake);
            ArrayList<CPU_SCORE> a = new ArrayList<>();
            for (int no = 0; no < repeatingExperiments; no++) {

                ArrayList<City> shakeGrid = makeCityList.shake(emptyGrid, XY_DISTANCE, shake);

                System.out.println("A");
                BBresult bbRes = BranchBound.main(shakeGrid);

                System.out.println("BB: " + bbRes.optScore + " " + bbRes.timeBB);
                double scoreGA = TSP_GA.TSP_GA(shakeGrid, iterationsBoundGA, bbRes.timeBB);
                //double scoreSA = SA.SA(shakeGrid, iterationsBoundGA, bbRes.timeBB);

                System.out.println("SA: " + scoreGA);

                a.add(new CPU_SCORE(shakeGrid,  shake, bbRes.iterationsBB, bbRes.timeBB, bbRes.optScore, scoreGA, iterationsBoundGA));
            }
            shakeResult.add(a);
        }
        return shakeResult;
    }

    public static void sortByTime (ArrayList<CPU_SCORE> unSorted) {
        Collections.sort(unSorted , new Comparator<CPU_SCORE>() {

            @Override
            public int compare(CPU_SCORE z1, CPU_SCORE z2) {
                if (z1.timeBB > z2.timeBB)
                    return 1;
                if (z1.timeBB < z2.timeBB)
                    return -1;
                return 0;
            }
        });
    }

    public static void sortByIterations (ArrayList<CPU_SCORE> unSorted) {
        Collections.sort(unSorted , new Comparator<CPU_SCORE>() {

            @Override
            public int compare(CPU_SCORE z1, CPU_SCORE z2) {
                if (z1.iterationsBB > z2.iterationsBB)
                    return 1;
                if (z1.iterationsBB < z2.iterationsBB)
                    return -1;
                return 0;
            }
        });
    }


    public static void saveResult (ArrayList<ArrayList<CPU_SCORE>> savable, String fileName) {


        File file = new File(fileName);

        try {// creates the file
            file.createNewFile();

            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);

            // Writes the content to the file
            for(ArrayList<CPU_SCORE> savableList: savable){
                // format:  ArrayList<City> grid, int pertrubation, int iterationsBB, long timeBB, double optScore, double scoreGA, int maxIterGA
                for (CPU_SCORE res: savableList){
                    for (int h = 0; h < res.grid.size()-1; h++){
                        writer.write(res.grid.get(h).x+ "," + res.grid.get(h).y + "-");
                    }
                    writer.write(res.grid.get(res.grid.size()-1).x+ "," + res.grid.get(res.grid.size()-1).y);
                    writer.write( ";"+ res.pertrubation + ";"+res.iterationsBB + ";"+  res.timeBB + ";"+  res.optScoreBB+ ";"+  res.scoreGA+ ";"+  res.maxIterGA + "\n");
                }
            }

            writer.flush();
            writer.close();
        } catch(IOException ex){
            System.out.print(ex);

        }
    }
}


