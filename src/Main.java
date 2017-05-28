import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.Comparator;


// StringLengthComparator.java
import java.util.Comparator;
/**
 * Created by marty_000 on 22-5-2017.
 */
public class Main {
    final static int TOTALCITIES = 20;
    final static int XY_DISTANCE = 12;
    final static ArrayList<City> emptyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);

    public static void main(String[] args) {



        final int timeBound  = 600;

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



        ArrayList<ArrayList<Result>> matrixArray = new ArrayList<>();
        // Reduction
//        for (int shake : shaked) {
//            System.out.println("Shake: " + shake);
//            matrixArray.add(S_BB (shake, 100));
//        }

        for (int redu : preservation) {
            System.out.println("PRES: " + redu);
            matrixArray.add(R_BB (redu, 100));
        }



//      // Reduction
//        for (int preserve : preservation) {
//            int count = 0;
//
//            System.out.println("PRESERVE: " + preserve);
//            while (count < 100) {
//                ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, preserve);
//                CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
//
//                reductionResult.add(new Result(0, preserve, CPU_BB_R.time, CPU_BB_R.score));
//                CPU_SCORE CPU_SA_R = SA.SA(reducedGrid, CPU_BB_R.score,  600);
//                reductionResult.add(new Result(1, preserve, CPU_SA_R.time, CPU_SA_R.score));
//                CPU_SCORE CPU_GA_R = TSP_GA.TSP_GA(reducedGrid, CPU_BB_R.score,  600);
//                reductionResult.add(new Result(2, preserve, CPU_GA_R.time, CPU_GA_R.score));
//                count ++;
//
//            }
//
//        }
//System.out.println("just aboiut halfway trough");

        // Shake


//        for (int shake: shaked) {
//            int count = 0;
//            System.out.println("SHAKE: " + shake + "\n");
//            while (count < 2) {
//                ArrayList<City> shakeGrid = makeCityList.shake(emptyGrid, XY_DISTANCE, shake);
//
//                CPU_SCORE CPU_BB_S = BranchBound.main(shakeGrid);
//                reductionResult.add(new Result(1, shake, CPU_BB_S.time, CPU_BB_S.score));
//
//                CPU_SCORE CPU_GA_S = TSP_GA.TSP_GA(shakeGrid, CPU_BB_S.score, 600);
//                reductionResult.add(new Result(2, shake, CPU_GA_S.time, CPU_GA_S.score));
//                CPU_SCORE CPU_SA_S = SA.SA(shakeGrid, CPU_BB_S.score, 600);
//                reductionResult.add(new Result(3, shake, CPU_SA_S.time, CPU_SA_S.score));
//
//
//                count++;
//            }
////        }
//
//        final int repeatsPerClass = 10;
//
//
//
//
//            int count = 10;
//            while (count < 20) {
//                System.out.println("\n "+ count+"\n");
//                for(int t = 0; t < repeatsPerClass; t ++) {
//                    emptyGrid = makeCityList.makePerfectCityList(count, XY_DISTANCE);
//
//                    CPU_SCORE CPU_BB_S = BranchBound.main(emptyGrid);
//                    reductionResult.add(new Result(1, count, CPU_BB_S.time, count));
//
//                    CPU_SCORE CPU_GA_S = TSP_GA.TSP_GA(emptyGrid, CPU_BB_S.score, 6000);
//                    reductionResult.add(new Result(2, count, CPU_GA_S.time, count));
//                    CPU_SCORE CPU_SA_S = SA.SA(emptyGrid, CPU_BB_S.score, 6000);
//                    reductionResult.add(new Result(3, count, CPU_SA_S.time, count));
//                }
//
//                count= count+10;
//            }
//

        System.out.println("visualising");

        JavaFX.main(preservation, matrixArray);

        }

    public static ArrayList<Result> R_BB (int preserve, int iterations ) {
        ArrayList<Result> res = new ArrayList<>();
        for (int y = 0; y < iterations; y++) {

            ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, preserve);
            CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);


            res.add(new Result(y, preserve, CPU_BB_R.time, CPU_BB_R.score));
        }




        Collections.sort(res , new Comparator<Result>() {

            @Override
            public int compare(Result z1, Result z2) {
                if (z1.time > z2.time)
                    return 1;
                if (z1.time < z2.time)
                    return -1;
                return 0;
            }
        });


        for(int f= 1 ; f <= res.size(); f++){
            res.get(f-1).id = f;
        }
        return res;

    }

    public static ArrayList<Result> S_BB (int shake, int iterations ) {
        ArrayList<Result> res = new ArrayList<>();
        for (int y = 0; y < iterations; y++) {

            ArrayList<City> shakeGrid = makeCityList.shake(emptyGrid, XY_DISTANCE, shake);


            CPU_SCORE CPU_BB_S = BranchBound.main(shakeGrid);
            res.add(new Result(y,shake, CPU_BB_S.time, CPU_BB_S.score));
        }




        Collections.sort(res , new Comparator<Result>() {

            @Override
            public int compare(Result z1, Result z2) {
                if (z1.time > z2.time)
                    return 1;
                if (z1.time < z2.time)
                    return -1;
                return 0;
            }
        });


        for(int f= 0 ; f < res.size(); f++){
            res.get(f).id = f;
        }
        return res;

    }
}


