package functions;

import data.EulerFunction;
import data.Function;

public class FunctionsForClient {
    public static final Function[] FUNCTIONS = new Function[]{
            new Function("x - y", new String[]{"x", "y"}),
            new Function("x^2 * y", new String[]{"x", "y"}),
            new Function("2xy", new String[]{"x", "y"}),
            new Function("x^2 - 2y", new String[]{"x", "y"}),
            new Function("1/2*x*y", new String[]{"x", "y"})
    };

    public static final EulerFunction[] EULER_FUNCTIONS = new EulerFunction[]{
            new EulerFunction(new Function("(y-x+1)/(e^(-x))", new String[]{"x", "y"}),
                    new Function("x+e^(-x)*c-1", new String[]{"x", "c"}),
                    FUNCTIONS[0]),
            new EulerFunction(new Function("y/(e^(x^2))", new String[]{"x", "y"}),
                    new Function("e^(x^2)*c", new String[]{"x", "c"}),
                    FUNCTIONS[1]),
            new EulerFunction(new Function("y/(e^(x^3/3))", new String[]{"x", "y"}),
                    new Function("e^(x^3/3)*c", new String[]{"x", "c"}),
                    FUNCTIONS[2]),
            new EulerFunction(new Function("(y-x^2/2+x/2-1/4)/(e^(-2x))", new String[]{"x", "y"}),
                    new Function("x^2/2-x/2+e^(-2x)*c+1/4", new String[]{"x", "c"}),
                    FUNCTIONS[4]),
            new EulerFunction(new Function("(y-x^2/2+x/2-1/4)/(e^(-2x))", new String[]{"x", "y"}),
                    new Function("x^2/2-x/2+e^(-2x)*c+1/4", new String[]{"x", "c"}),
                    FUNCTIONS[4])
    };
}
