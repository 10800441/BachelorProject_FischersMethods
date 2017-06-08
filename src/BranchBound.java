import javax.swing.*;
import java.util.ArrayList;

public class BranchBound
{
    public static BBresult main(ArrayList<City> grid) {
        int verbose=0;
        int size = grid.size();
        //System.out.println("size="+size);
        double [] places=new double[size*2+1]; //x and y        coordinate
int counter = 0;
        for (City c:grid) {
            counter++;
            places[counter]=Double.parseDouble(String.valueOf(c.x)); //x coordinate
            counter++;
            places[counter]=Double.parseDouble(String.valueOf(c.y)); //y coordinate
            if (verbose==1) System.out.println("places["+(counter-1)+"],places["+(counter)+"]="+places[(counter-1)]+","+places[counter]);
        }
        long startTime = System.currentTimeMillis();

        //generate cost matrix
        if (verbose==1) System.out.println("Generated cost matrix=");
        double [][] costMatrix = new double [size+1][size+1];
        for (int j=1; j<=size; j++) {
            for (int i=1; i<=size; i++) {

                if (j==i) costMatrix[j][i]=0; //distance from A to A is        zero every time

                costMatrix[j][i]=Math.sqrt( Math.pow(places[(j-1)*2+1]-
                        places[(i-1)*2+1],2)+
                        Math.pow(places[(j-1)*2+2]-
                                places[(i-1)*2+2],2) );
                if (verbose==1) System.out.printf("%.4f ",costMatrix[j][i]);
            }
            if (verbose==1) System.out.println();
        }
        //get solution
        TSP tsp = new TSP(costMatrix,size,Double.MAX_VALUE,verbose);
//TSP(costMatrix,size,bestRoute,verbose)
        tsp.generateSolution(false);
        long endTime = System.currentTimeMillis();
        return new BBresult(tsp.iterations, (endTime- startTime), tsp.bestTour());
    }
}
