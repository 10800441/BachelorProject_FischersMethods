// StringLengthComparator.java
import java.util.Comparator;

public class ResultComparator implements Comparator<Result>
{
    @Override
    public int compare(Result x, Result y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.time < y.time)
        {
            return -1;
        }
        if (x.time > y.time)
        {
            return 1;
        }
        return 0;
    }
}