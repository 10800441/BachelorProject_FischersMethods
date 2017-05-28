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




    @Override public void start(Stage stage) {


        stage.setTitle("Cost distribution graph");
        //final LogarithmicNumberAxis yAxis = new LogarithmicNumberAxis(1,100 );
      // final LogarithmicNumberAxis xAxis = new LogarithmicNumberAxis(1,100 );
        final NumberAxis xAxis = new NumberAxis(0,20, 5);
        final NumberAxis yAxis = new NumberAxis(0,100, 20);
//        final ScatterChart<Number,Number> sc = new
//                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Time (ms)");
        yAxis.setLabel("Solved instances");
       // sc.setTitle("Reduction Operator on Branch and Bound");


//        XYChart.Series series1 = new XYChart.Series();
//        XYChart.Series series2 = new XYChart.Series();
//        XYChart.Series series3 = new XYChart.Series();
//        series1.setName("Branch and Bound");
//        series2.setName("Generic Algorithm");
//        series3.setName("Simulated Annealing");

        final LineChart<Number,Number> lc = new
                LineChart<Number,Number>(xAxis,yAxis);
        lc.setTitle("Reduction, Branch and Bound, 12 cities");

         XYChart.Series<Number, Number> series1Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series4Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series5Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series6Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series7Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series8Average = new XYChart.Series<>();
//        XYChart.Series<Number,Number> series1Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series2Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series3Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series4Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series5Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series6Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series7Average = new XYChart.Series<Number,Number>();
//        XYChart.Series<Number,Number> series8Average = new XYChart.Series<Number,Number>();
        series1Average.setName("99");
        series2Average.setName("98");
        series3Average.setName("95");
        series4Average.setName("90");
        series5Average.setName("85");
        series6Average.setName("75");
        series7Average.setName("50");
        series8Average.setName("25");


        for(int a = 0; a < visulatiseList.size(); a++) {

            if (visulatiseList.get(a)  == 99) {
                for(Result r: mArray.get(a)) {
                    series1Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));

                }

            }
            else if (visulatiseList.get(a)  == 98) {
                for(Result r: mArray.get(a)) {
                    series2Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 95) {
                for(Result r: mArray.get(a)) {
                    series3Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 90) {
                for(Result r: mArray.get(a)) {
                    series4Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id));
                    System.out.println(r.id);
                }

            }
            else if (visulatiseList.get(a)  == 85) {
                for(Result r: mArray.get(a)) {
                    series5Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 75) {
                for(Result r: mArray.get(a)) {

                    series6Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 50) {
                for(Result r: mArray.get(a)) {
                    series7Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
                }

            }
            else if (visulatiseList.get(a)  == 25) {
                for(Result r: mArray.get(a)) {
                    series8Average.getData().add(new XYChart.Data<Number,Number>(r.time,r.id ));
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
        hbox.setPadding(new Insets(10, 10, 10, 50));

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
    public static void main(ArrayList<Integer> preserveList,ArrayList<ArrayList<Result>> matrixArray) {
        visulatiseList = preserveList;
        mArray = matrixArray;
        launch();


    }
}
