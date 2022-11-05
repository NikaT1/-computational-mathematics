package methods;

import data.TwoFuncSystem;
import utils.MathUtils;

import static java.lang.Math.abs;

public class MethodNewton {
    public MethodAnswer findAnswer(TwoFuncSystem system, double eps, double currentX, double currentY, int maxItr) {
        int iterationCount = 0;
        double x;
        double y;
        String errorMessage = "";
        boolean continueFlag;
        do {
            x = currentX;
            y = currentY;
            iterationCount++;
            double[] coefficients = calculateCoefficients(x, y, system);
            currentX = x + coefficients[0];
            currentY = y + coefficients[1];
            continueFlag = (abs(x - currentX) > eps || abs(y - currentY) > eps);
        } while (continueFlag && maxItr > iterationCount);
        double[] answer = new double[]{currentX, currentY};
        double[] inaccuracy = new double[]{currentX - x, currentY - y};
        if (maxItr == iterationCount) errorMessage = "Достигнуто максимально допустимое количество итераций!";
        if (Double.isNaN(currentX) || Double.isNaN(currentY)) errorMessage = "Нет корней";
        return new MethodAnswer(answer, inaccuracy, iterationCount, errorMessage);
    }

    private double[] calculateCoefficients(double x, double y, TwoFuncSystem system) {
        double[] derivativesX = MathUtils.getDerivativeInPointByX(system, x, y);
        double[] derivativesY = MathUtils.getDerivativeInPointByY(system, x, y);
        double func1 = system.calculateFirstFunc(x, y);
        double func2 = system.calculateSecondFunc(x, y);
        double[] answers = new double[2];
        answers[0] = (func2 * derivativesY[0] - func1 * derivativesY[1]) / (derivativesY[1] * derivativesX[0] -
                derivativesX[1] * derivativesY[0]);
        answers[1] = (func1 * derivativesX[1] - func2 * derivativesX[0]) / (derivativesY[1] * derivativesX[0] -
                derivativesX[1] * derivativesY[0]);
        return answers;
    }
}
