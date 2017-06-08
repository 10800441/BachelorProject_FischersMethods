/*
* TSP_GA.java
* Create a tour and evolve a solution
*/


import javax.swing.*;
import java.util.ArrayList;



public class TSP_GA {


    public static double TSP_GA(ArrayList<City> emptyGrid, double iterations, long timeBound) {
        TourManager.clearAll();

        for(City c:  emptyGrid){
            TourManager.addCity(c);
        }

        long endTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();


            // Initialize population
            Population pop = new Population(50, true);

        // Evolve population for 100 generations


            pop = GA.evolvePopulation(pop);
            for (int i = 0; i < iterations; i++) {
                pop = GA.evolvePopulation(pop);
            }
            endTime = System.currentTimeMillis();
            double currentBest = (double) pop.getFittest().getDistance();

        //if((endTime - startTime) >= timeBound) return new CPU_SCORE(1000, currentBest);
        // Print final results
//     makeCityList.printGrid(new JFrame("GA"), pop.getFittest().getTour());


        return currentBest;
    }
}