package data;

import lombok.Getter;

import java.util.ArrayList;

public class DiscreteFunction {
    @Getter
    private final ArrayList<Double> x;
    @Getter
    private ArrayList<Double> y;
    @Getter
    private final OneArgFunction function;

    public DiscreteFunction(ArrayList<Double> x, ArrayList<Double> y) {
        this.function = null;
        this.x = x;
        this.y = y;
    }
}
