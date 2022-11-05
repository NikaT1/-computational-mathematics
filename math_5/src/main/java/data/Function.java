package data;

import lombok.Getter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {
    @Getter
    private final Expression expression;
    @Getter
    private final String stringFunc;
    private final String[] vars;

    public Function(String stringFunc, String[] vars) {
        expression = new ExpressionBuilder(stringFunc)
                .variables(vars)
                .build();
        this.stringFunc = stringFunc;
        this.vars = vars;
    }

    public double calculate(double[] values) {
        for (int i = 0; i < values.length; i++) {
            expression.setVariable(vars[i], values[i]);
        }
        return expression.evaluate();
    }
}