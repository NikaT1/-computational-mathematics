package data;

import java.util.ArrayList;
import java.util.Random;

public class DiscreteFunction {
    private final ArrayList<Double> x;
    private ArrayList<Double> y;
    private final OneArgFunction function;
    private final boolean noise;

    public DiscreteFunction(ArrayList<Double> x, OneArgFunction function, boolean noise) {
        this.function = function;
        this.x = x;
        this.noise = noise;
        generateYArray(noise);
    }

    private void generateYArray(boolean noise) {
        y = new ArrayList<>();
        if (noise) {
            Random random = new Random();
            for (double i : x) {
                double rand = random.nextDouble();
                double summand = (rand > 0.1) ? random.nextDouble() * function.calculate(i) / 5 : 0;
                y.add(function.calculate(i) + summand);
            }
        } else {
            for (double i : x) {
                y.add(function.calculate(i));
            }
        }

    }

    public boolean isNoise() {
        return noise;
    }

    public OneArgFunction getFunction() {
        return function;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }
}
