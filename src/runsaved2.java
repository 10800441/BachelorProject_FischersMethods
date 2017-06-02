import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class runsaved2 {
    public static void main(String[] args) {
        ArrayList matrixArray = new ArrayList<>();
        try {
        FileReader fr = new FileReader("out/data/performanceTest20ReduceStep100.txt");
        BufferedReader br = new BufferedReader(fr);
        String s;
        while ((s = br.readLine()) != null) {
        // use comma as separator
        String[] country = s.split(",");
            matrixArray.add(new Perform(country[0], Integer.valueOf(country[1]), Double.valueOf(country[2])/10));
        }

        fr.close();
        } catch (IOException ex) {
        System.out.println(ex);
        }

        JavaFX2.main( matrixArray,""+ 12, "Reduce");
        }
        }