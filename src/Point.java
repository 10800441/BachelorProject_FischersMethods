/**
 * Created by Hans on 7-6-2017.
 */
public class Point {


        int x;
        int y;
    int realx;

    int realy;

        // Constructs a randomly placed city
        public Point(int x, int y, int realx, int realy){
            this.x = x;
            this.y = y;
            this.realx = realx;
            this.realy = realy;
        }


        @Override
        public String toString(){
            return x +", "+y ;
        }
    }