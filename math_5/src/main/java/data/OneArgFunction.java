package data;

public class OneArgFunction extends Function {

    public OneArgFunction(String stringFunc) {
        super(stringFunc, new String[]{"x"});
    }

    public double calculate(double value) {
        return super.calculate(new double[]{value});
    }
}