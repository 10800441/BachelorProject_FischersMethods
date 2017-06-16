import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ClusterProblem {

    final static String operator = "Reduce";
    final static String fileId = "";
    final static int TOTALCITIES =30;
    final static int XY_DISTANCE = 12;
    final static ArrayList<City> emptyGrid = makeCityList.makePerfectCityList(TOTALCITIES, XY_DISTANCE);

    public static void main(String[] args) {


       ArrayList<CPU_SCORE> resultArray = new ArrayList<>();

        try {
            FileReader fr = new FileReader("out/data/Systematic/ReductionCities30_Ex100bo100reduceSA30C.txt");
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
                resultArray.add(new CPU_SCORE(grid, Integer.valueOf(rawString[1]), Integer.valueOf(rawString[2]),Long.valueOf(rawString[3]),Double.valueOf(rawString[4]), Double.valueOf(rawString[5]), Integer.valueOf(rawString[6])));
            }

            fr.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        System.out.println("Loading data completed, drawing graph");




for(CPU_SCORE A : resultArray){
    double p = ((A.scoreGA / A.optScoreBB) * 100) - 100;
    if (p > 136 && p < 145){
        System.out.println("Cat Hard: "+ A.grid +"\nscoreSA: " +A.scoreGA + "\nopti: "+ A.optScoreBB + "\n");
        DrawGraph.createAndShowGui(new JFrame("hard"+ p), A.grid, new DrawGraph() );
    }

    if (p > 40 && p < 45){
        System.out.println("Cat Easy: "+ A.grid +"\nscoreSA: " +A.scoreGA + "\nopti: "+ A.optScoreBB+ "\n");
        DrawGraph.createAndShowGui(new JFrame("easy"+ p), A.grid, new DrawGraph());
    }
}

      //  Visualise2.main("out/data/Systematic/ReductionCities12_Ex100bo100reduceSA.txt",true,  true);


//        final int timeBound  = 600;
//
//        ArrayList<Integer> preservation = new ArrayList<>();
//        preservation.add(99);
//        preservation.add(98);
//        preservation.add(95);
//        preservation.add(90);
//        preservation.add(85);
//        preservation.add(75);
//        preservation.add(50);
//        preservation.add(25);
//
//        ArrayList<Integer> shaked = new ArrayList<>();
//        shaked.add(5);
//        shaked.add(10);
//        shaked.add(25);
//        shaked.add(50);
//        shaked.add(75);
//        shaked.add(100);
//        shaked.add(150);
//        shaked.add(200);
//        // Reduction
//
//
//
//        ArrayList<ArrayList<Result>> matrixArray = new ArrayList<>();
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
//      // Reduction
////        for (int preserve : preservation) {
//
////        for (int size = 4; size < 20; size++){
////            int count = 1;
////
//////            double avgBB_R_Time = 0.0;
//////            double avgGA_R_Time = 0.0;
//////            double avgSA_R_Time = 0.0;
////            double avgBBTime = 0.0;
////            double avgGATime = 0.0;
////
////
//////            System.out.println("PRESERVE: " + preserve);
////            while (count <= 100) {
////                System.out.println("C: " + count);
////                // ArrayList<City> reducedGrid = makeCityList.reduce(emptyGrid, preserve);
////                ArrayList<City> randomGrid = makeCityList.randomGrid(size, 1000);
////
//////                CPU_SCORE CPU_BB_R = BranchBound.main(reducedGrid);
//////                avgBB_R_Time += CPU_BB_R.time;
//////                CPU_SCORE CPU_SA_R = SA.SA(reducedGrid, CPU_BB_R.score, 10000);
//////                avgSA_R_Time += CPU_SA_R.time;
//////                CPU_SCORE CPU_GA_R = TSP_GA.TSP_GA(reducedGrid, CPU_BB_R.score, 10000);
//////                avgGA_R_Time += CPU_GA_R.time;
////
////
////                CPU_SCORE CPU_BB = BranchBound.main(randomGrid);
////                avgBBTime += CPU_BB.time;
////                System.out.println( size+ " < size BB time >  " +CPU_BB.time);
////
////                CPU_SCORE CPU_GA = TSP_GA.TSP_GA(randomGrid, CPU_BB.score, 100000);
////                avgGATime += CPU_GA.time;
////                System.out.println( size+ " < size GA time >  " +CPU_GA.time);
////
////                count++;
////
////            }
////
//////            performancearray.add(new Perform("avgBB_R_Time",preserve, avgBB_R_Time/100)) ;
//////            performancearray.add(new Perform("avgGA_R_Time", preserve , avgGA_R_Time/100)) ;
//////            performancearray.add(new Perform("avgSA_R_Time", preserve , avgSA_R_Time/100)) ;
////            performancearray.add(new Perform("avgBBTime", size,avgBBTime/100)) ;
////            performancearray.add(new Perform("avgGATime", size, avgGATime/100)) ;
////          //  performancearray.add(new Perform("avgSATime", size, avgSATime/100)) ;
////
////
////
////        }
//
//
//
//        File file = new File("out/data/performanceTestRandom20.txt");
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

    }

}


