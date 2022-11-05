package methods;

import data.DiscreteFunction;
import data.OneArgFunction;

import java.util.ArrayList;

public class MethodLagrange {

    public OneArgFunction calculatePolynomial(DiscreteFunction function) {
        ArrayList<Double> x = function.getX();
        ArrayList<Double> y = function.getY();

        StringBuilder stringPolynomial = new StringBuilder();
        for (int i = 0; i < y.size(); i++) {
            stringPolynomial.append(y.get(i)).append("*").append(calcLagrangeMultiplier(x, i).toString());
            if (i < y.size() - 1) stringPolynomial.append(" + ");
        }
        return new OneArgFunction(stringPolynomial.toString());
    }

    public StringBuilder calcLagrangeMultiplier(ArrayList<Double> x, int ind) {
        StringBuilder multiplierNumerator = new StringBuilder();
        multiplierNumerator.append("1");
        double multiplierDenominator = 1D;
        for (int i = 0; i < x.size(); i++) {
            if (i != ind) {
                multiplierNumerator.append("*(x-").append(x.get(i)).append(")");
                multiplierDenominator *= (x.get(ind) - x.get(i));
            }
        }
        return multiplierNumerator.append("/").append(multiplierDenominator);
    }
}
