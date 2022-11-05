package functions;

import data.OneArgEquation;

import static java.lang.Math.*;

public class functionsForClient {
    public static final OneArgEquation[] EQUATIONS = new OneArgEquation[]{
            new OneArgEquation((x, coef) -> coef[0] * sin(x) / x + coef[1] * cos(x) + coef[2],
                    "y = a * sin(x) / x + b * cos(x) + c"),
            new OneArgEquation((x, coef) -> coef[0] * pow(x, 3) + coef[1] * pow(x, 2) + coef[2] * x,
                    "y = a * x^3 + b * x^2 + c * x"),
            new OneArgEquation((x, coef) -> 1 / x,
                    "y = 1 / x"),
            new OneArgEquation((x, coef) -> coef[0] * x - log(coef[1] + x) + coef[2],
                    "y = a * x - ln(x + b) + c")
    };
}
