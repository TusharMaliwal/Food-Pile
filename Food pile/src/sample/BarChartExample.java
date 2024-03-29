package sample;

import java.util.ArrayList;
import java.util.Arrays;

import common.responses.Charts.BarChartResponse;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
        
public class BarChartExample extends Application {
    ArrayList<BarChartResponse> list;

    public BarChartExample(ArrayList<BarChartResponse> list) {
        this.list=list;
    }

    public void start(Stage stage) {
       //Defining the axes
       CategoryAxis xAxis = new CategoryAxis();
       xAxis.setCategories(FXCollections.<String>
               observableArrayList(Arrays.asList("price", "quantity", "threshold")));
       xAxis.setLabel("name");

       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("integer");

       //Creating the Bar chart
       BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
       barChart.setTitle("Comparison items price quantity and threshold");

        for (BarChartResponse barChartResponse : list) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(barChartResponse.getName());
            series.getData().add(new XYChart.Data<>("price", barChartResponse.getPrice()));
            series.getData().add(new XYChart.Data<>("quantity", barChartResponse.getQuantity()));
            series.getData().add(new XYChart.Data<>("threshold", barChartResponse.getThreshold()));
            barChart.getData().add(series);
        }

       Group root = new Group(barChart);
       Scene scene = new Scene(root, 600, 400);

       stage.setTitle("Bar Chart");
       stage.setScene(scene);

       stage.show();
   }
}