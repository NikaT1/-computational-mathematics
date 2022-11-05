package methods;

import data.OneArgEquation;
import utils.MathUtils;

import static java.lang.Math.abs;

public class MethodTangent {
    public MethodAnswer findAnswer(OneArgEquation equation, double eps, double a, double b, int maxItr) {
        int iterationCount = 0;
        double x = a;
        if (equation.calculate(b) * MathUtils.getDerivativeInPoint(2, equation, b) > 0) x = b;
        double currentX = 0;
        String errorMessage = "";
        if (equation.calculate(a) * equation.calculate(b) < 0) {
            boolean continueFlag;
            do {
                x = currentX;
                iterationCount++;
                currentX = x - equation.calculate(x) / MathUtils.getDerivativeInPoint(1, equation, x);
                continueFlag = abs(x - currentX) > eps;
                if (!(currentX >= a && currentX <= b)) {
                    continueFlag = false;
                    errorMessage = "Xi вышел за пределы [a,b].";
                }
            } while (continueFlag && maxItr > iterationCount);
        } else errorMessage = "Неверный интервал локализации.";
        double[] answer = new double[]{currentX};
        double[] inaccuracy = new double[]{currentX - x};
        if (maxItr == iterationCount) errorMessage = "Достигнуто максимально допустимое количество итераций!";
        return new MethodAnswer(answer, inaccuracy, iterationCount, errorMessage);
    }
}
