import java.util.ArrayList;

/**
 * Created by marty_000 on 22-5-2017.
 */
public class CPU_SCORE {

    ArrayList<City> grid;
    int pertrubation;

    int iterationsBB;
    long timeBB;
    double optScoreBB;
    double scoreGA;
    double scoreSA;
    int maxIterGA;


    public CPU_SCORE( ArrayList<City> grid, int pertrubation,  int iterationsBB, long timeBB, double optScoreBB, double scoreGA,double scoreSA, int maxIterGA){
        this.grid = grid;
        this.pertrubation = pertrubation;

        this.iterationsBB = iterationsBB;
        this.timeBB = timeBB;
        this.optScoreBB = optScoreBB;
        this.scoreGA = scoreGA;
        this.scoreSA = scoreSA;
       this.maxIterGA = maxIterGA;

    }
}
