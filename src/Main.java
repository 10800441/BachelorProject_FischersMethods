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

    final static String operator = "Shake";
    final static String fileId = "";
    final static int TOTALCITIES = 30;
    final static int XY_DISTANCE = 12;
    final static ArrayList<City> emptyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);
    final int timeBound  = 2000;

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


        ArrayList<ArrayList<Result>> matrixArray = reductionOperator(preservation);

    }
    private static ArrayList<ArrayList<Result>> reductionOperator(ArrayList<Integer> preservation){
        ArrayList<ArrayList<Result>> reductionResult = new ArrayList<>();

        for(int preservance: preservation) {

            ArrayList<Result> =
        }


        return reductionResult;


    }






    public static ArrayList<Result> solveBranchBound (int pertrubationLevel, int repeatingExperiments) {
        ArrayList<Result> res = new ArrayList<>();


        for (int y = 0; y < repeatingExperiments; y++) {

            ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, pertrubationLevel);
            CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
            System.out.println("30 " + CPU_BB_R.time);


            res.add(new Result(y, pertrubationLevel, CPU_BB_R.time, CPU_BB_R.score));
        }


        return res;
    }


    public static ArrayList<Result> solveGeneticAlgorithm (int pertrubationLevel, int repeatingExperiments, int cutOfAt ) {
        ArrayList<Result> res = new ArrayList<>();


        for (int y = 0; y < repeatingExperiments; y++) {

            ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, pertrubationLevel);
            CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
            System.out.println("30 " + CPU_BB_R.time);


            res.add(new Result(y, pertrubationLevel, CPU_BB_R.time, CPU_BB_R.score));
        }


        return res;
    }


    public static void sortByTime (ArrayList<Result> unSorted) {
        Collections.sort(unSorted , new Comparator<Result>() {

            @Override
            public int compare(Result z1, Result z2) {
                if (z1.time > z2.time)
                    return 1;
                if (z1.time < z2.time)
                    return -1;
                return 0;
            }
        });
    }

    public static void saveResult (ArrayList<Result> savable, String fileName) {
        ArrayList<Result> res = new ArrayList<>();



        File file = new File(fileName);

        try {// creates the file
            file.createNewFile();

            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);

            // Writes the content to the file
            for(int f= 1 ; f <= res.size(); f++){
                res.get(f-1).id = f;
                writer.write(res.get(f-1).id+","+res.get(f-1).preservation+","+res.get(f-1).time+","+res.get(f-1).score+"\n");
            }

            writer.flush();
            writer.close();
        } catch(IOException ex){
            System.out.print(ex);

        }
     }


}


