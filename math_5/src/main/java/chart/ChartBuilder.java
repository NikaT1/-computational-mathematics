package chart;

import data.Function;
import data.OneArgFunction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartBuilder extends Application {

    private static OneArgFunction polynomial;
    private static Function analyticalFunction;
    private static double constant;
    private static String polynomialName;
    private static double xMin;
    private static double xMax;
    private LineChart<Number, Number> numberLineChart;

    public static void setPolynomial(OneArgFunction polynomial, String polynomialName) {
        ChartBuilder.polynomial = polynomial;
        ChartBuilder.polynomialName = polynomialName;
    }

    public static void setAnalyticalFunction(Function function, double constant) {
        ChartBuilder.analyticalFunction = function;
        ChartBuilder.constant = constant;
    }

    public static void setBounds(double min, double max) {
        ChartBuilder.xMin = min;
        ChartBuilder.xMax = max;
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
        drawAnalyticalFunction();
        drawFunction(polynomial, xMin, xMax, polynomialName);
        drawPoint(xMin);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void drawAnalyticalFunction() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Аналитическое решение");
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        double step = (xMax - xMin) / 2000;
        step = Math.max(step, 0.001);
        for (double i = xMin; i < xMax; i += step) {
            XYChart.Data item = new XYChart.Data(i, analyticalFunction.calculate(new double[]{i, ChartBuilder.constant}));
            data.add(item);
        }
        series.setData(data);
        numberLineChart.getData().add(series);
        for (XYChart.Data item : data) {
            item.getNode().setVisible(false);
        }
        series.getNode().setStyle("-fx-stroke-dash-array: 2 12 12 2; ");
    }

    public void drawPoint(double x) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Выбранная точка");
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        data.add(new XYChart.Data(x, polynomial.calculate(x)));
        series.setData(data);
        numberLineChart.getData().add(series);
    }

    public void drawFunction(OneArgFunction function, double xMin, double xMax, String name) {
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        double step = (xMax - xMin) / 2000;
        step = Math.max(step, 0.001);
        for (double i = xMin; i < xMax; i += step) {
            XYChart.Data item = new XYChart.Data(i, function.calculate(i));
            data.add(item);
        }
        series.setData(data);
        numberLineChart.getData().add(series);
        for (XYChart.Data item : data) {
            item.getNode().setVisible(false);
        }
    }
}
