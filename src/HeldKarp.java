
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/** The Held Karp algorithm:
 *
 * There are 2 possible cases in each iteration:
 *
 * A) A base case where we already know the answer. (Stopping condition)
 * B) Decreasing the number of considered vertices and calling our algorithm again. (Recursion)
 *
 * Explanation of every case:
 *
 * A) If the list of vertices is empty, return the distance between starting point and vertex.
 * B) If the list of vertices is not empty, lets decrease our problem space:
 *
 *      1) Consider each vertex in vertices as a starting point ("initial")
 *      2) Since we are considering "initial" as a starting point, we have to adjust the list of vertices, by removing "initial"
 *      3) Calculate the cost of visiting "initial" (costCurrentNode) + cost of visiting rest of vertices starting from there ("costChildren")
 *      4) Return the minimum result from step 3
 */

public class HeldKarp {

    /* ----------------------------- GLOBAL VARIABLES ------------------------------ */
    public static double[][] distances;
    public static double optimalDistance = 0.0;
    public static String optimalPath = "";
    public static int iterations = 0;
    public static long time = 0 ;


    /* ------------------------------ MAIN FUNCTION -------------------------------- */

    public static BBresult main(ArrayList<City> grid, double[][] cm) throws IOException{
        distances = cm;

        /* ----------------------------- IO MANAGEMENT ----------------------------- */


int size = grid.size();


        /* ------------------------- ALGORITHM INITIALIZATION ----------------------- */

        // Initial variables to start the algorithm
        String path = "";
        int[] vertices = new int[size - 1];

        // Filling the initial vertices array with the proper values
        for (int i = 1; i < size; i++) {
            vertices[i - 1] = i;
        }

        // FIRST CALL TO THE RECURSIVE FUNCTION
        procedure(0, vertices, path, 0);



        return new BBresult(iterations,time,optimalDistance);
    }


    /* ------------------------------- RECURSIVE FUNCTION ---------------------------- */

    public static double procedure(int initial, int list[], String path, double costUntilHere) {

        // We concatenate the current path and the vertex taken as initial
        path = path + "" + Integer.toString(initial) + " - ";

        Long start = System.currentTimeMillis();
        int length = list.length;
        double newCostUntilHere;


        // Exit case, if there are no more options to evaluate (last node)
        if (length == 0) {

            path = path + "0";
            newCostUntilHere = costUntilHere + distances[initial][0];

            // If it is the first evaluated branch (optimalDistance = 0)
            if (optimalDistance == 0){
                optimalDistance = newCostUntilHere;
            }

            // If it is another branch and its value is lower than the stored one
            else if (newCostUntilHere < optimalDistance){
                optimalDistance = newCostUntilHere;
                optimalPath = path;
            }

            return (distances[initial][0]);
        }

        // If the traversed branch reaches a point where the cost is higher than the stored one: stop traversing.
        else if (costUntilHere > optimalDistance && optimalDistance != 0){
            return 0;
        }

        // Common case, when there are several nodes in the list
        else {

            double[] costChildren = new double[length];
            int[][] newList = new int[length][(length - 1)];
            double costCurrentNode, costChild;
            double totalCost = 10000000;           // Big number to simulate infinity

            // For each of the nodes of the list
            for (int i = 0; i < length; i++) {

                // First of all we construct our new list, the one to be passed to each recursion
                for (int j = 0, k = 0; j < length; j++, k++) {

                    // The first iteration is not taken into account due to avoid pass the select
                    if (j == i) {
                        k--;
                        continue;
                    }
                    newList[i][k] = list[j];
                }

                // Cost of arriving the current node from its parent
                costCurrentNode = distances[initial][list[i]];

                // Here the cost to be passed to the recursive function is computed
                newCostUntilHere = costCurrentNode + costUntilHere;

                // RECURSIVE CALLS TO THE FUNCTION IN ORDER TO COMPUTE THE COSTS
                costChildren[i] = procedure(list[i], newList[i], path, newCostUntilHere);
                iterations++;
                // The cost of every child + the current node cost is computed
                costChild = costChildren[i] + costCurrentNode;

                // Finally we select from the all possible children costs, the one with minimum value
                if (costChild < totalCost) {
                    totalCost = costChild;
                }
            }
            time = System.currentTimeMillis() - start;
            return (totalCost);
        }
    }

}