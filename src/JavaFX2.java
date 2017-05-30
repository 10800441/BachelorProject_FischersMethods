import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class JavaFX2 extends Application {



    private static ArrayList<Perform> mArray = new ArrayList<>();
    private static String cities = "";
    private static  String op = "";




    @Override public void start(Stage stage) {


        stage.setTitle("Average Time graph");
         //final LogarithmicNumberAxis yAxis = new LogarithmicNumberAxis(1,180 );
        // final LogarithmicNumberAxis xAxis = new LogarithmicNumberAxis(1,100   );
        final NumberAxis xAxis = new NumberAxis(0, 25, 5);
        final NumberAxis yAxis = new NumberAxis(0,180, 20);
//        final ScatterChart<Number,Number> sc = new
//                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Instance size ");
        yAxis.setLabel("Average CPU-time");
        // sc.setTitle("Reduction Operator on Branch and Bound");


        final LineChart<Number,Number> lc = new
                LineChart<Number,Number>(xAxis,yAxis);
        lc.setTitle(op + " for BB, GA amd SA   ");

        XYChart.Series<Number, Number> series1Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2Average = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3Average = new XYChart.Series<>();
//        XYChart.Series<Number, Number> series4Average = new XYChart.Series<>();
//        XYChart.Series<Number, Number> series5Average = new XYChart.Series<>();
//        XYChart.Series<Number, Number> series6Average = new XYChart.Series<>();
        series1Average.setName("BB ");
        series2Average.setName("SA ");
        series3Average.setName("GA ");
//        series4Average.setName("BB_perf");
//        series5Average.setName("SA_perf");
//        series6Average.setName("GA_perf");



        for(Perform performance: mArray) {

            if (performance.id.equals("avgBB_R_Time")) {
                series1Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
            } else if (performance.id.equals("avgGA_R_Time")) {
                series2Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
            } else if (performance.id.equals("avgSA_R_Time")) {
                series3Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
//            } else if (performance.id.equals("avgBBTime")) {
//                series4Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
//            } else if (performance.id.equals("avgGATime")) {
//                series5Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
//            } else if (performance.id.equals("avgSATime")) {
//                series6Average.getData().add(new XYChart.Data<Number, Number>(performance.preservation, performance.avgTime));
//            }
            }

        }

        lc.setPrefSize(700, 600);
        lc.setCreateSymbols(false);
        lc.getData().addAll(series1Average, series2Average, series3Average );//, series4Average, series5Average, series6Average );


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
    public static void main(ArrayList<Perform> matrixArray, String TOTALCITIES, String operator) {
        cities = TOTALCITIES;
        op = operator;
        mArray = matrixArray;
        launch();


    }
}
