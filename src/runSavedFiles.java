import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hans on 29-5-2017.
 */
public class runSavedFiles {
    public static void main(String[] args) {


        final int cities = 12;
        final String operator = "Shake"; // Reduction or shake
        final String fileId = ""; // default ""


        if (operator.equals("Reduction")) {
            ArrayList<Integer> preservation = new ArrayList<>();
            preservation.add(99);
            preservation.add(98);
            preservation.add(95);
            preservation.add(90);
            preservation.add(85);
            preservation.add(75);
            preservation.add(50);
            preservation.add(25);
            ArrayList<ArrayList<Result>> matrixArray = new ArrayList<>();

            for (Integer a : preservation) {
                ArrayList<Result> b = new ArrayList<>();
                try {
                    FileReader fr = new FileReader("out/data/" + operator + "_" + cities + "_" + a + fileId + ".txt");
                    BufferedReader br = new BufferedReader(fr);
                    String s;
                    while ((s = br.readLine()) != null) {
                        // use comma as separator
                        String[] country = s.split(",");
                        b.add(new Result(Integer.valueOf(country[0]), Integer.valueOf(country[1]), Long.valueOf(country[2]), Double.valueOf(country[3])));
                    }
                    matrixArray.add(b);
                    fr.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            JavaFX.main(preservation, matrixArray, "" + cities, operator);
        }



if(operator.equals("Shake"))

    {
        ArrayList<Integer> shaked = new ArrayList<>();
        shaked.add(5);
        shaked.add(10);
        shaked.add(25);
        shaked.add(50);
        shaked.add(75);
        shaked.add(100);
        shaked.add(150);
        shaked.add(200);
        ArrayList<ArrayList<Result>> matrixArray = new ArrayList<>();

        for (Integer a : shaked) {
            ArrayList<Result> b = new ArrayList<>();
            try {
                FileReader fr = new FileReader("out/data/" + operator + "_" + cities + "_" + a + fileId + ".txt");
                BufferedReader br = new BufferedReader(fr);
                String s;
                while ((s = br.readLine()) != null) {
                    // use comma as separator
                    String[] country = s.split(",");
                    b.add(new Result(Integer.valueOf(country[0]), Integer.valueOf(country[1]), Long.valueOf(country[2]), Double.valueOf(country[3])));
                }
                matrixArray.add(b);
                fr.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        JavaFX.main(shaked, matrixArray, "" + cities, operator);
    }
}
}
