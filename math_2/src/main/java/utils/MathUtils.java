package utils;

import data.OneArgEquation;
import data.TwoFuncSystem;

public class MathUtils {
    public static double getDerivativeInPoint(int level, OneArgEquation equation, double point) {
        double h = 0.00001;
        if (level <= 0) return 0;
        if (level == 1) return (equation.calculate(point + h) - equation.calculate(point)) / h;
        return (getDerivativeInPoint(level - 1, equation, point + h) -
                getDerivativeInPoint(level - 1, equation, point)) / h;
    }

    public static double[] getDerivativeInPointByX(TwoFuncSystem system, double x, double y) {
        double h = 0.00001;
        double[] answers = new double[2];
        answers[0] = (system.calculateFirstFunc(x + h, y) - system.calculateFirstFunc(x, y)) / h;
        answers[1] = (system.calculateSecondFunc(x + h, y) - system.calculateSecondFunc(x, y)) / h;
        return answers;
    }

    public static double[] getDerivativeInPointByY(TwoFuncSystem system, double x, double y) {
        double h = 0.00001;
        double[] answers = new double[2];
        answers[0] = (system.calculateFirstFunc(x, y + h) - system.calculateFirstFunc(x, y)) / h;
        answers[1] = (system.calculateSecondFunc(x, y) - system.calculateSecondFunc(x, y + h)) / h;
        return answers;
    }
}
