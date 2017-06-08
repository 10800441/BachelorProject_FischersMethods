import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.abs;

/**
 * Created by marty_000 on 22-5-2017.
 */
public class makeCityList {
    // Cities are arranged in a chessboard like structure
    public static ArrayList<City> makePerfectCityList(int totalCities, int XYdistance) {
        ArrayList<City> cityDatabase = new ArrayList<>();
        int[] dimensions = calculate_grid_size(totalCities);
        int rows = dimensions[0];
        int columns = dimensions[1];
        int idCounter = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                cityDatabase.add(new City(r * XYdistance, c * XYdistance));
                if (idCounter < totalCities) idCounter++;
            }
        }
        return cityDatabase;
    }

    public static double[][] calculateCostMatrix(ArrayList<City> grid) {

        double[][] cm = new double[Main.TOTALCITIES][Main.TOTALCITIES];

        // Loop trough all rows
        for (int i = 0; i < Main.TOTALCITIES; i++) {
            City fromCity = grid.get(i);

            // Loop trough all columns
            for (int j = 0; j < Main.TOTALCITIES; j++) {
                City toCity = grid.get(j);

                if (i == j) {
                    // Negative distance to self
                    cm[j][i] = 0.0;
                }
                else {
                    //Calculate euclidean distance using pythagoras theorem
                    double delta_x = abs(fromCity.x - toCity.x);
                    double delta_y = abs(fromCity.y - toCity.y);
                    cm[j][i] = sqrt(Math.pow(delta_x,2) + Math.pow(delta_y,2));
                }
            }
        }

        return cm;
    }
    public static ArrayList<City> randomGrid(int size, int bound){
        ArrayList<City> rand = new ArrayList<>();
        Random rd = new Random();
        for(int f = 0; f < size; f++){
            int x = rd.nextInt(bound);
            int y = rd.nextInt(bound);
            City c = new City(x,y);
            while(rand.contains(c)){
                c.x =  rd.nextInt(bound);
                c.y =  rd.nextInt(bound);
            }
            rand.add(c);

        }
        return rand;
    }


    // Distributes an amount of cities evenly in rows and columns
    public static int[] calculate_grid_size(int totalCities) {
        double root_num = Math.sqrt(totalCities);
        int divider = (int) Math.ceil(root_num);
        while (divider <= totalCities) {

            if (totalCities % divider == 0) {
                int otherDivider = totalCities / divider;
                int[] data = new int[2];
                return new int[]{divider, otherDivider};
            }
            divider++;
        }
        return null;
    }



    // Operator that reduces
    public static ArrayList<City> reduce(ArrayList<City> cities, int preservation) {
        ArrayList<City> newList = new ArrayList<City>();

        // amount of cities to be removed
        int preserveAmount =  (int) (cities.size() * 0.01 * preservation);

        Random rd = new Random();


        Collections.shuffle(cities);
        for(int r = 0 ; r <  preserveAmount; r++){
            newList.add(cities.get(r));
        }

        return newList;
    }

    public static void printGrid(JFrame frame,  ArrayList<City> tour) {
        DrawGraph.createAndShowGui(frame, tour);
    }


    public static ArrayList<City> shake(ArrayList<City> cities, int XYdistance, int shake) {

        ArrayList<City> newList = new ArrayList<City>();
        double standardD = XYdistance * shake * 0.01;
        Random r = new Random();
        int minimalX = 0;
        int minimalY = 0;

        // determine the amount of offset  == length of the radius
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            double offset = r.nextGaussian() * standardD + 0;

            // offset can be in any random direction
            int angle = r.nextInt(360);

            double xOffset = Math.sin(angle)/offset;
            double yOffset = Math.cos(angle)/offset;

            int xIntegerOffset = (int )round(xOffset);
            int yIntegerOffset = (int )round(yOffset);

            city = new City(city.x+xIntegerOffset, city.y+yIntegerOffset);
            newList.add(city);

            if (xIntegerOffset  < minimalX) minimalX = xIntegerOffset;
            if (yIntegerOffset < minimalY) minimalY = yIntegerOffset;
        }

        ArrayList<City> shakeCities = new ArrayList<>();

        // Scale to only positive integers
        for (int p = 0; p < newList.size(); p++){
            City city = newList.get(p);
            shakeCities.add(new City(city.x+Math.abs(minimalX), city.y+Math.abs(minimalY)));
        }

        return shakeCities;
    }

}
