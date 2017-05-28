import javax.swing.*;
import java.util.ArrayList;

public class SA {

    // Calculate the acceptance probability
    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static CPU_SCORE SA(ArrayList<City> emptyGrid, double optimalScore, long timeBound) {

       TourManager.clearAll();

        for(City c: emptyGrid){
            TourManager.addCity(c);
        }

        double currentBest = optimalScore+10;
        long endTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        // as long as the optimum score isnt reached or a time bound
        while((currentBest > optimalScore+optimalScore*0.01) && ((endTime - startTime) < timeBound )) {

            // Set initial temp
            double temp = 10000;

            // Cooling rate
            double coolingRate = 0.0003;

            // Initialize intial solution
            Tour currentSolution = new Tour();
            currentSolution.generateIndividual();


            // Set as current best
            Tour best = new Tour(currentSolution.getTour());

            // Loop until system has cooled
            while (temp > 1) {
                // Create new neighbour tour
                Tour newSolution = new Tour(currentSolution.getTour());

                // Get a random positions in the tour
                int tourPos1 = (int) (newSolution.tourSize() * Math.random());
                int tourPos2 = (int) (newSolution.tourSize() * Math.random());

                // Get the cities at selected positions in the tour
                City citySwap1 = newSolution.getCity(tourPos1);
                City citySwap2 = newSolution.getCity(tourPos2);

                // Swap them
                newSolution.setCity(tourPos2, citySwap1);
                newSolution.setCity(tourPos1, citySwap2);

                // Get energy of solutions
                int currentEnergy = currentSolution.getDistance();
                int neighbourEnergy = newSolution.getDistance();

                // Decide if we should accept the neighbour
                if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                    currentSolution = new Tour(newSolution.getTour());
                }

                // Keep track of the best solution found
                if (currentSolution.getDistance() < best.getDistance()) {
                    best = new Tour(currentSolution.getTour());
                }

                // Cool system
                temp *= 1 - coolingRate;
            }
            currentBest = (double) best.getDistance();

            endTime = System.currentTimeMillis();
        }
//        JFrame frame = new JFrame("SA");
//        makeCityList.printGrid( frame, best.getTour());
        return new CPU_SCORE(endTime - startTime,  currentBest);

    }
}
