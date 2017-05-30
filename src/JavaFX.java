import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;


public class JavaFX extends Application {
    private static ArrayList<Integer> visulatiseList = new ArrayList<>();

    private static ArrayList<ArrayList<Result>> mArray = new ArrayList<>();
    private static String cities = "";
    private static  String op = "";




    @Override public void start(Stage stage) {


        stage.setTitle("Cost distribution graph");
        //final LogarithmicNumberAxis yAxis = new LogarithmicNumberAxis(1,100 );
      // final LogarithmicNumberAxis xAxis = new LogarithmicNumberAxis(1,100   );
       final NumberAxis xAxis = new NumberAxis(0, 500, 20);
        final NumberAxis yAxis = new NumberAxis(0,100, 20);
//        final ScatterChart<Number,Number> sc = new
//                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Score ");
        yAxis.setLabel("Solved instances");
       // sc.setTitle("Reduction Operator on Branch and Bound");


        final LineChart<Number,Number> lc = new
                LineChart<Number,Number>(xAxis,yAxis);
        lc.setTitle(op + " Branch and Bound, " + cities + " cities");

        XYChart.Series<Number, Number> series1Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series4Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series5Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series6Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series7Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series8Average = new XYChart.Series<>();
        series1Average.setName("5");
        series2Average.setName("10");
        series3Average.setName("25");
        series4Average.setName("50");
        series5Average.setName("75");
        series6Average.setName("100");
        series7Average.setName("150");
        series8Average.setName("200");


        for(int a = 0; a < visulatiseList.size(); a++) {

            if (visulatiseList.get(a)  == 5) {
                for(Result r: mArray.get(a)) {
                    series1Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));


                }

            }
            else if (visulatiseList.get(a)  == 10) {
                for(Result r: mArray.get(a)) {
                    series2Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 25) {
                for(Result r: mArray.get(a)) {
                    series3Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 50) {
                for(Result r: mArray.get(a)) {
                    series4Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id));
                    System.out.println(r.id);
                }

            }
            else if (visulatiseList.get(a)  == 75) {
                for(Result r: mArray.get(a)) {
                    series5Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 100) {
                for(Result r: mArray.get(a)) {

                    series6Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 150) {
                for(Result r: mArray.get(a)) {
                    series7Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 200) {
                for(Result r: mArray.get(a)) {
                    series8Average.getData().add(new XYChart.Data<Number,Number>(r.score,r.id ));
                }

            }

        }




        lc.setPrefSize(700, 600);
        lc.setCreateSymbols(false);
        lc.getData().addAll(series1Average, series2Average, series3Average, series4Average, series5Average, series6Average, series7Average, series8Average);


      //  lc.getData().add(series2Average);
        Scene scene  = new Scene(new Group());
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();



        hbox.setSpacing(10);


        vbox.getChildren().addAll(lc, hbox);
        hbox.setPadding(new Insets(30, 30, 30, 50));

        ((Group)scene.getRoot()).getChildren().add(vbox);
        stage.setScene(scene);
        stage.show();

//        add.setOnAction((ActionEvent e) -> {
//            if (sc.getData() == null) {
//                sc.setData(FXCollections.<XYChart.Series<Number,
//                        Number>>observableArrayList());
//            }
//            ScatterChart.Series<Number, Number> series
//                    = new ScatterChart.Series<>();
//            series.setName("Option " + (sc.getData().size() + 1));
//            for (int i = 0; i < 100; i++) {
//                series.getData().add(
//                        new ScatterChart.Data<>(Math.random() * 100,
//                                Math.random() * 500));
//            }
//            sc.getData().add(series);
//        });
    }
    public static void main(ArrayList<Integer> preserveList,ArrayList<ArrayList<Result>> matrixArray, String TOTALCITIES, String operator) {
        cities = TOTALCITIES;
        op = operator;
        visulatiseList = preserveList;
        mArray = matrixArray;
        launch();


    }
}
