package functions;

import data.OneArgEquation;
import data.TwoFuncSystem;

import static java.lang.Math.*;

public class functionsForClient {
    public static final OneArgEquation[] EQUATIONS = new OneArgEquation[]{
            new OneArgEquation((x, coef) -> coef[0] * sin(x) + coef[1] * cos(x) + pow(x, coef[2]) + coef[3],
                    "y = a * sin(x) + b * cos(x) + x^c + d"),
            new OneArgEquation((x, coef) -> coef[0] * pow(x, 3) + coef[1] * pow(x, 2) + coef[2] * x + coef[3],
                    "y = a * x^3 + b * x^2 + c * x + d"),
            new OneArgEquation((x, coef) -> coef[0] * x * pow(E, coef[1]) - log(coef[2] + x) + coef[3],
                    "y = a * x * e ^ b - ln(x + c) + d")
    };

    public static final TwoFuncSystem[] SYSTEMS = new TwoFuncSystem[]{
            new TwoFuncSystem((x, y, coef) -> coef[0] * sin(x) + coef[1] * cos(y) + pow(y, coef[2]) + coef[3],
                    (x, y, coef) -> coef[0] * y * x + coef[1] * pow(x, 2) + coef[2] * y + coef[3],
                    "a * sin(x) + b * cos(y) + y^c + d = 0\na * y * x + b * x^2 + c * y + d = 0"),
            new TwoFuncSystem((x, y, coef) -> coef[0] * x * pow(E, coef[1]) - log(coef[2] + y) + coef[3],
                    (x, y, coef) -> coef[0] * y * x + coef[1] * x + coef[2] * y + coef[3],
                    "a * x * e ^ b - ln(y + c) + d = 0\na * y * x + b * x + c * y + d = 0")
    };
}
