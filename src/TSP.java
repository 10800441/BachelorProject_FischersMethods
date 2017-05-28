// TSP Branch and Bound Main Driver
import java.util.*;
import java.io.*;
public class TSP implements Serializable
{
    private static final long serialVersionUID = 1L;
    // Fields
    private int verbose;
    public static int numRows;
    private int numCols;
    private double bestTour = Double.MAX_VALUE / 4;
    private Node bestNode;
    private TreeSet queue = new TreeSet();
    public static Cost c;
    private long totalNodeCount = 0L;
    public TSP (double [][] costMatrix, int size, double bestTour /*, double threshold */, int verbose) {
// this.threshold = threshold;
        this.verbose=verbose;
        this.bestTour = bestTour;
        numRows = numCols = size;
        c = new Cost(numRows, numCols);
        for (int row = 1; row <= size; row++)
            for (int col = 1; col <= size; col++) {
                //System.out.println("costMatrix["+row+"]["+col+"]="+costMatrix[row][col]);
                c.assignCost(costMatrix[row][col], row, col);
                //System.out.println("c["+row+"]["+col+"]="+c.cost(row,col));
            }
    }
    public void generateSolution (boolean ongoing) {
        long startTimeMs=System.currentTimeMillis();
        if (!ongoing) {
            // Create root node
            byte[] cities = new byte[2];
            cities[1] = 1;
            Node root = new Node(cities, 1);
            root.setLevel(1);
            totalNodeCount++;
            root.computeLowerBound();
            if (verbose==1) System.out.println("The lower bound for root node (no constraints): "+root.lowerBound());
            queue.add(root);
        }

        while (queue.size() > 0) {
            Node next = (Node) queue.first();
            //System.out.println("next.size="+next.size());
            //System.out.println("next.lowerBound="+next.lowerBound());
            if (next.size() == TSP.numRows - 1 && next.lowerBound() < bestTour) {
                bestTour = next.lowerBound();
                bestNode = next;
                if (verbose==1) {
                    System.out.println("\nNodes generated: "
                            +totalNodeCount);
                    System.out.println("Best tour cost: " + bestTour);
                    System.out.println("Best tour: " + bestNode);
                    System.out.println("queue.size(): " + queue.size());
                }
            }
            queue.remove(next);
            if (next.lowerBound() < bestTour) {
                int newLevel = next.level() + 1;
                byte [] nextCities = next.cities();
                int size = next.size();
                for (int city = 2; city <= TSP.numRows; city++) {
                    if (!present((byte) city, nextCities)) {
                        byte [] newTour = new byte[size + 2];
                        for (int index = 1; index <= size; index++) {
                            newTour[index] = nextCities[index];
                        }
                        newTour[size + 1] = (byte) city;
                        Node newNode = new Node(newTour, size + 1);
                        newNode.setLevel(newLevel);
                        totalNodeCount++;
                        if (totalNodeCount % 100000 == 0) {
                            if (verbose==1) System.out.print(".");
                        }
                        if (totalNodeCount % 1000000 == 0) {
                            if (verbose==1) {
                                System.out.println();
                                System.out.println("\nNodes generated:"+totalNodeCount / 1000000 +" million nodes.");
                                        System.out.println("queue.size():"+queue.size());
                            }
                            if (bestTour != Integer.MAX_VALUE / 4) {
                                if (verbose==1) System.out.println("Best tour cost: "+bestTour);
                                if (totalNodeCount % 10000000 == 0) {
                                    if (verbose==1) {
                                        System.out.println("Best tour: "
                                                +bestNode);
                                        System.out.println();
                                        System.out.println("Distribution of Levels In Queue from "+((Node) queue.last()).level()+" to "+((Node)
                                                queue.first()).level());
                                    }
                                    int[] number = new
                                            int[TSP.numRows];
                                    for (Iterator iter =queue.iterator();

                                    iter.hasNext(); ) {
                                        Node n = (Node) iter.next();
                                        number[n.level()]++;
                                    }
                                    if (verbose==1) {
                                        System.out.println();
                                        for (int i = 2; i < TSP.numRows;
                                             i++) {
                                            System.out.print(i + ": " +
                                                    number[i] + " ");
                                            if (i % 5 == 0) {
                                                System.out.println();
                                            }
                                        }
                                        System.out.println("\n");
                                    }
                                }
                            }
                        }
                        newNode.computeLowerBound();
                        double lowerBound = newNode.lowerBound();
                        if (lowerBound < bestTour) {
                            queue.add(newNode);
                        } else {
                            newNode = null;
                        }
                    }
                }
            } else {
                next = null;
            }
        }
        if (verbose==1) System.out.println("\n\nAn optimum tour has been found.");
        long endTimeMs=System.currentTimeMillis();
        long durationMs=endTimeMs-startTimeMs;
        //output best tour
        //System.out.print(bestNode);
        if (verbose==1) {
            System.out.println("Best tour: "+bestNode);
            System.out.println("Cost of tour: "+bestTour);
            System.out.println("Total of nodes generated: "+totalNodeCount);
                    System.out.println("Duration="+durationMs/1000+"s");
            System.out.println();
        }
    }
    // Queries
    public long nodesCreated () {
        return totalNodeCount;
    }
    public Node bestNode () {
        return bestNode;
    }

    public double bestTour () {
        return bestTour;
    }
    public long nodesGenerated () {
        return totalNodeCount;
    }
    private boolean present (byte city, byte [] cities) {
        for (int i = 1; i <= cities.length - 1; i++) {
            if (cities[i] == city) {
                return true;
            }
        }
        return false;
    }
}
