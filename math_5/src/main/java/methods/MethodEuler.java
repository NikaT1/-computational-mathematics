package methods;

import data.DiscreteFunction;
import data.Function;

import java.util.ArrayList;

public class MethodEuler {

    private final int maxDotsCount = 20;

    public DiscreteFunction findAnswer(double x0, double y0, double xn, Function function, double h) {
        int count = (int) Math.ceil((xn - x0) / h);
        count = Math.min(count, maxDotsCount);
        h = (xn - x0) / count;
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> x = new ArrayList<>();
        y.add(y0);
        x.add(x0);
        double currentX = x0;
        double currentY = y0;
        for (int i = 0; i < count; i++) {
            currentY = currentY + h * function.calculate(new double[]{currentX, currentY});
            y.add(currentY);
            currentX += h;
            x.add(currentX);
        }
        return new DiscreteFunction(x, y);
    }
}
