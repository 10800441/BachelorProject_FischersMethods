/*
* TSP_GA.java
* Create a tour and evolve a solution
*/


import javax.swing.*;
import java.util.ArrayList;



public class TSP_GA {


    public static CPU_SCORE TSP_GA(ArrayList<City> emptyGrid, double optimalScore, long timeBound) {
        TourManager.clearAll();

        for(City c:  emptyGrid){
            TourManager.addCity(c);
        }

        long endTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();


        double currentBest = optimalScore+10;
        // as long as the optimum score isnt reached or a time bound
        while((currentBest > optimalScore+1) && ((endTime - startTime) < timeBound )) {

            // Initialize population
            Population pop = new Population(50, true);

        // Evolve population for 100 generations


            pop = GA.evolvePopulation(pop);
            for (int i = 0; i < 100; i++) {
                pop = GA.evolvePopulation(pop);
            }
            endTime = System.currentTimeMillis();
            currentBest = (double) pop.getFittest().getDistance();
        }

        if((endTime - startTime) >= timeBound) return new CPU_SCORE(1000, currentBest);
        // Print final results
//     makeCityList.printGrid(new JFrame("GA"), pop.getFittest().getTour());


        return new CPU_SCORE(endTime - startTime, currentBest);
    }
}