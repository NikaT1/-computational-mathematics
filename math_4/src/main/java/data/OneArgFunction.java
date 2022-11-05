package data;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class OneArgFunction {
    private final Expression expression;
    private final String stringFunc;

    public OneArgFunction(String stringFunc) {
        expression = new ExpressionBuilder(stringFunc)
                .variables("x")
                .build();
        this.stringFunc = stringFunc;
    }

    public double calculate(double x) {
        return expression.setVariable("x", x).evaluate();
    }

    public String getStringFunc() {
        return stringFunc;
    }
}