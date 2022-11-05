package data;

public class OneArgEquation {
    private OneArgFunction function;
    private double[] coefficients;
    private String stringFunc;

    public OneArgEquation(OneArgFunction function, String stringFunc) {
        this.function = function;
        this.coefficients = new double[]{1, 1, 1, 1};
        this.stringFunc = stringFunc;
    }
    public OneArgEquation(OneArgFunction function, double[] coefficients, String stringFunc) {
        this.function = function;
        this.coefficients = coefficients;
        this.stringFunc = stringFunc;
    }

    public double calculate(double x) {
        return function.calc(x, coefficients);
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public String getStringFunc() {
        return stringFunc;
    }
}