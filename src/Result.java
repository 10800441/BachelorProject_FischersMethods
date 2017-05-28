import java.util.Comparator;

public class Result implements Comparator<Result>, Comparable<Result> {
    int id;
    int preservation;
    long time;
    double score;
    Result() {
    }



    public Result(int id, int preservation, long time, double score) {
        this.id = id;
        this.preservation = preservation;
        this.time = time;
        this.score = score;
    }

    public int compare(Result o1, Result o2)
    {
        if(o1.time < o2.time)

        {
            return -1;
        }else if (o1.time == o2.time)
        {
            return 0;
        }else
        {
            return 1;
        }
    }

    public int compareTo(Result o1)
    {
        if(time < o1.time)

        {
            return -1;
        }else if (time == o1.time)
        {
            return 0;
        }else
        {
            return 1;
        }
    }
}

