package methods;

import data.OneArgEquation;

import static java.lang.Math.abs;

public class MethodRectangle {

    public MethodAnswer findAnswer(OneArgEquation equation, double eps, double a, double b, int maxItr, double coef) {
        int segmentsCount = 2;
        int iterationCount = 0;
        double sum, currentSum = 0;
        String errorMessage = "";
        boolean continueFlag;
        do {
            iterationCount++;
            sum = currentSum;
            double h = (b - a) / segmentsCount;
            currentSum = 0;
            for (double i = a; i < b; i += h) {
                double x = equation.calculate(i + coef * h);
                if (Double.isNaN(x)) {
                    x = (equation.calculate(i + coef * h + 0.0000000001) + equation.calculate(i + coef * h - 0.0000000001)) / 2;
                }
                currentSum += x;
            }
            if (Double.isInfinite(currentSum) || Double.isNaN(currentSum)) {
                errorMessage = "Невозможно вычислить интеграл!";
                break;
            }
            currentSum *= h;
            continueFlag = abs(currentSum - sum) > eps;
            segmentsCount *= 2;
        } while (continueFlag && maxItr > iterationCount);
        if (maxItr == iterationCount) errorMessage = "Невозможно вычислить интеграл!";
        return new MethodAnswer(currentSum, currentSum - sum, segmentsCount / 2, errorMessage);
    }
}
