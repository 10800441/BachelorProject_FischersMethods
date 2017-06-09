import java.util.ArrayList;

/**
 * Created by marty_000 on 22-5-2017.
 */
public class CPU_SCORE {

    ArrayList<City> grid;
    int pertrubation;
    int iterationsHK;
    long timeHK;

    double optScoreHK;
    int iterationsBB;
    long timeBB;
    double optScoreBB;
    double scoreGA;
    int maxIterGA;


    public CPU_SCORE( ArrayList<City> grid, int pertrubation, int iterationsHK, long timeHK, double optScoreHK, int iterationsBB, long timeBB, double optScoreBB, double scoreGA, int maxIterGA){
        this.grid = grid;
        this.pertrubation = pertrubation;
        this.iterationsHK = iterationsHK;
        this.timeHK = timeHK;

        this.optScoreHK = optScoreHK;
        this.iterationsBB = iterationsBB;
        this.timeBB = timeBB;
        this.optScoreBB = optScoreBB;
        this.scoreGA = scoreGA;
       this.maxIterGA = maxIterGA;

    }
}
