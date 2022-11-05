package data;

public class TwoFuncSystem {
    private final TwoArgsFunction[] functions = new TwoArgsFunction[2];
    private double[][] coefficients;
    private final String stringFunc;

    public TwoFuncSystem(TwoArgsFunction function1, TwoArgsFunction function2, String stringFunc) {
        functions[0] = function1;
        functions[1] = function2;
        this.stringFunc = stringFunc;
        this.coefficients = new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}};
    }

    public TwoFuncSystem(TwoArgsFunction function1, TwoArgsFunction function2, double[][] coefficients, String stringFunc) {
        functions[0] = function1;
        functions[1] = function2;
        this.stringFunc = stringFunc;
        this.coefficients = coefficients;
    }

    public double[] calculate(double x, double y) {
        double[] answers = new double[2];
        answers[0] = functions[0].calc(x, y, coefficients[0]);
        answers[1] = functions[1].calc(x, y, coefficients[1]);
        return answers;
    }

    public double calculateFirstFunc(double x, double y) {
        return functions[0].calc(x, y, coefficients[0]);
    }

    public double calculateSecondFunc(double x, double y) {
        return functions[1].calc(x, y, coefficients[1]);
    }

    public void setCoefficients(double[][] coefficients) {
        this.coefficients = coefficients;
    }

    public String getStringFunc() {
        return stringFunc;
    }
}
