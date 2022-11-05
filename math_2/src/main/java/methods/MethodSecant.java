package methods;

import data.OneArgEquation;

import static java.lang.Math.abs;

public class MethodSecant {

    public MethodAnswer findAnswer(OneArgEquation equation, double eps, double a, double b, int maxItr) {
        double funcAtA = equation.calculate(a);
        double funcAtB = equation.calculate(b);
        int iterationCount = 0;
        double x = 0;
        double currentX = 0;
        String errorMessage = "";
        if (funcAtA * funcAtB < 0) {
            boolean continueFlag;
            do {
                x = currentX;
                iterationCount++;
                currentX = a - ((b - a) * funcAtA) / (funcAtB - funcAtA);
                continueFlag = abs(currentX - x) > eps;
                if (equation.calculate(currentX) * funcAtA < 0) b = currentX;
                if (equation.calculate(currentX) * funcAtB < 0) a = currentX;
                funcAtA = equation.calculate(a);
                funcAtB = equation.calculate(b);
            } while (continueFlag && maxItr > iterationCount);
        } else errorMessage = "Неверный интервал локализации.";
        double[] answer = new double[]{currentX};
        double[] inaccuracy = new double[]{currentX - x};
        if (maxItr == iterationCount) errorMessage = "Достигнуто максимально допустимое количество итераций!";
        return new MethodAnswer(answer, inaccuracy, iterationCount, errorMessage);
    }
}
