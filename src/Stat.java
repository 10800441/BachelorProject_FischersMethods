/*
* City.java
* Models a city
*/


import java.util.ArrayList;

public class Stat {
    int pertrubation;
    ArrayList<Double> yCoord;
    double mean;
    double ub;
    double lb;




    // Constructs a city at chosen x, y location
    public Stat(int pertrubation, ArrayList<Double> yCoord,double mean,double ub, double lb){
        this.pertrubation = pertrubation;
        this.yCoord = yCoord;
        this.mean = mean;
        this.ub = ub;
        this.lb = lb;
    }


    @Override
    public String toString(){
        return mean+", "+ub + "" + lb;   }
}
