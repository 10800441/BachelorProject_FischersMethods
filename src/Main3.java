import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Main3 {

    final static String operator = "Reduce";
    final static String fileId = "";
    final static int TOTALCITIES = 20;
    final static int XY_DISTANCE = 6;
    final static ArrayList<City> emptyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);

    public static void main(String[] args) {

        final int timeBound = 600;

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
        ArrayList<City> emtyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);
  emtyGrid = makeCityList.shake(emptyGrid, 6, 150);
makeCityList.printGrid(new JFrame("b"), emtyGrid);
    }
}//        ArrayList<ArrayList<Result>> matrixArray = new ArrayList<>();
//        // Reduction
////        for (int shake : shaked) {
////            System.out.println("Shake: " + shake);
////            matrixArray.add(S_BB (shake, 100));
////        }
//
////        for (int shake : shaked) {
////            System.out.println("shake: " + shake);
////            matrixArray.add(S_BB (shake, 100));
////        }
//
//
//        ArrayList<Perform> performancearray= new ArrayList<>();
//        // Reduction
////        for (int preserve : preservation) {
//
//        for (int red:preservation){
//
//            int count = 1;
//
////            double avgBB_R_Time = 0.0;
////            double avgGA_R_Time = 0.0;
////            double avgSA_R_Time = 0.0;
//            double avgBBTime = 0.0;
//            double avgGATime = 0.0;
//            System.out.println("C: " + count);
//
////            System.out.println("PRESERVE: " + preserve);
//            while (count <= 1000) {
//                 ArrayList<City> redGrid = makeCityList.reduce(emtyGrid, red);
//
//
////                CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
////                avgBB_R_Time += CPU_BB_R.time;
////                CPU_SCORE CPU_SA_R = SA.SA(reducedGrid, CPU_BB_R.score, 10000);
////                avgSA_R_Time += CPU_SA_R.time;
////                CPU_SCORE CPU_GA_R = TSP_GA.TSP_GA(reducedGrid, CPU_BB_R.score, 10000);
////                avgGA_R_Time += CPU_GA_R.time;
//
//
//                CPU_SCORE CPU_BB = BranchBound.main(redGrid);
//                avgBBTime += CPU_BB.time;
//                System.out.println(red+ " , " + CPU_BB.time);
//                CPU_SCORE CPU_GA = TSP_GA.TSP_GA(redGrid, CPU_BB.score, 100000);
//                avgGATime += CPU_GA.time;
//
//                count++;
//
//            }
//
////            performancearray.add(new Perform("avgBB_R_Time",preserve, avgBB_R_Time/100)) ;
////            performancearray.add(new Perform("avgGA_R_Time", preserve , avgGA_R_Time/100)) ;
////            performancearray.add(new Perform("avgSA_R_Time", preserve , avgSA_R_Time/100)) ;
//            performancearray.add(new Perform("avgBBTime", red,avgBBTime/100)) ;
//            performancearray.add(new Perform("avgGATime", red, avgGATime/100)) ;
//
//
//
//        }
//
//
//
//        File file = new File("out/data/performanceTest20ReduceStep100.txt");
//
//        try {// creates the file
//            file.createNewFile();
//
//            // creates a FileWriter Object
//            FileWriter writer = new FileWriter(file);
//
//            // Writes the content to the file
//
//            for(Perform p : performancearray) {
//                writer.write(p.id + "," + p.preservation + "," + p.avgTime+"\n");
//
//            }
//
//            writer.flush();
//            writer.close();
//        } catch(IOException ex){
//            System.out.print(ex);
//
//        }
//
//        System.out.println("visualising");
//
//        JavaFX2.main(performancearray,""+ TOTALCITIES, operator);
//
//    }
//
//    public static ArrayList<Result> R_BB (int preserve, int iterations ) {
//        ArrayList<Result> res = new ArrayList<>();
//        String  fileName = "out/data/"+operator + "_"+TOTALCITIES+"_"+preserve+ fileId+ ".txt";
//
//        for (int y = 0; y < iterations; y++) {
//
//            ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, preserve);
//            CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
//            System.out.println("30 " + CPU_BB_R.time);
//
//
//            res.add(new Result(y, preserve, CPU_BB_R.time, CPU_BB_R.score));
//        }
//
//
//
//
//        Collections.sort(res , new Comparator<Result>() {
//
//            @Override
//            public int compare(Result z1, Result z2) {
//                if (z1.time > z2.time)
//                    return 1;
//                if (z1.time < z2.time)
//                    return -1;
//                return 0;
//            }
//        });
//        File file = new File(fileName);
//
//        try {// creates the file
//            file.createNewFile();
//
//            // creates a FileWriter Object
//            FileWriter writer = new FileWriter(file);
//
//            // Writes the content to the file
//            for(int f= 1 ; f <= res.size(); f++){
//                res.get(f-1).id = f;
//                writer.write(res.get(f-1).id+","+res.get(f-1).preservation+","+res.get(f-1).time+","+res.get(f-1).score+"\n");
//            }
//
//            writer.flush();
//            writer.close();
//        } catch(IOException ex){
//            System.out.print(ex);
//
//        }
//
//
//        return res;
//
//    }
//
//    public static ArrayList<Result> S_BB (int shake, int iterations ) {
//        ArrayList<Result> res = new ArrayList<>();
//        for (int y = 0; y < iterations; y++) {
//
//            ArrayList<City> shakeGrid = makeCityList.shake(emptyGrid, XY_DISTANCE, shake);
//
//
//            CPU_SCORE CPU_BB_S = BranchBound.main(shakeGrid);
//            System.out.println(CPU_BB_S.time);
//            res.add(new Result(y,shake, CPU_BB_S.time, CPU_BB_S.score));
//        }
//
//
//        Collections.sort(res , new Comparator<Result>() {
//
//            @Override
//            public int compare(Result z1, Result z2) {
//                if (z1.time > z2.time)
//                    return 1;
//                if (z1.time < z2.time)
//                    return -1;
//                return 0;
//            }
//        });
//
//
//        for(int f= 1 ; f <= res.size(); f++){
//            res.get(f-1).id = f;
//        }
//        String  fileName = "out/data/"+operator + "_"+TOTALCITIES+"_"+shake+ fileId+ ".txt";
//        File file = new File(fileName);
//
//        try {// creates the file
//            file.createNewFile();
//
//            // creates a FileWriter Object
//            FileWriter writer = new FileWriter(file);
//
//            // Writes the content to the file
//            for(int f= 1 ; f <= res.size(); f++){
//                res.get(f-1).id = f;
//                writer.write(res.get(f-1).id+","+res.get(f-1).preservation+","+res.get(f-1).time+","+res.get(f-1).score+"\n");
//            }
//
//            writer.flush();
//            writer.close();
//        } catch(IOException ex){
//            System.out.print(ex);
//
//        }
//
//        return res;
//
//    }
//}