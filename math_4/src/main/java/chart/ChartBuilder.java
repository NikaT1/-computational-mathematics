package chart;

import data.DiscreteFunction;
import data.OneArgFunction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Collections;

public class ChartBuilder extends Application {

    private static DiscreteFunction discreteFunction;
    private static String functionName;
    private static OneArgFunction polynomial;
    private static String polynomialName;
    private LineChart<Number, Number> numberLineChart;
    private final double indent = 1.0;

    public static void setDiscreteFunction(DiscreteFunction discreteFunction, String functionName) {
        ChartBuilder.discreteFunction = discreteFunction;
        ChartBuilder.functionName = functionName;
    }

    public static void setPolynomial(OneArgFunction polynomial, String polynomialName) {
        ChartBuilder.polynomial = polynomial;
        ChartBuilder.polynomialName = polynomialName;
    }

    public void build() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Result chart");
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        numberLineChart = new LineChart<>(x, y);
        numberLineChart.setTitle("");
        Scene scene = new Scene(numberLineChart, 600, 600);
        double xMin = Collections.min(discreteFunction.getX());
        double xMax = Collections.max(discreteFunction.getX());
        drawFunction(polynomial, xMin, xMax, polynomialName, false);
        drawFunction(discreteFunction.getFunction(), xMin, xMax, functionName, true);
        drawDiscreteFunction();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void drawDiscreteFunction() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Выбранные точки");
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        for (int i = 0; i < discreteFunction.getX().size(); i++) {
            data.add(new XYChart.Data(discreteFunction.getX().get(i), discreteFunction.getY().get(i)));
        }
        series.setData(data);
        numberLineChart.getData().add(series);
        Node line = series.getNode().lookup(".chart-series-line");
        line.setStyle("-fx-stroke: transparent;");
    }

    public void drawFunction(OneArgFunction function, double xMin, double xMax, String name, boolean effect) {
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        double step = (xMax - xMin) / 2000;
        step = Math.max(step, 0.001);
        for (double i = xMin - indent; i < xMax + indent; i += step) {
            XYChart.Data item = new XYChart.Data(i, function.calculate(i));
            data.add(item);
        }
        series.setData(data);
        numberLineChart.getData().add(series);
        for (XYChart.Data item : data) {
            item.getNode().setVisible(false);
        }
        if (effect) series.getNode().setStyle("-fx-stroke-dash-array: 2 12 12 2; ");
    }
}
