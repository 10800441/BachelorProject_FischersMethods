import java.util.ArrayList;

/**
 * Created by marty_000 on 22-5-2017.
 */
public class CPU_SCORE {

    ArrayList<City> grid;
    int pertrubation;
    int iterationsBB;
    long timeBB;
    double optScore;
    double scoreGA;
    int maxIterGA;


    public CPU_SCORE( ArrayList<City> grid, int pertrubation, int iterationsBB, long timeBB, double optScore, double scoreGA, int maxIterGA){
        this.grid = grid;
        this.pertrubation = pertrubation;
        this.iterationsBB = iterationsBB;
        this.timeBB = timeBB;
        this.optScore = optScore;
        this.scoreGA = scoreGA;
       this.maxIterGA = maxIterGA;

    }
}
