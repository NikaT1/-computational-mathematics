package functions;

import data.DiscreteFunction;
import data.OneArgFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class functionsForClient {
    public static final OneArgFunction[] FUNCTIONS = new OneArgFunction[]{
            new OneArgFunction("sin(x)"),
            new OneArgFunction("log(x)"),
            new OneArgFunction("x^2"),
            new OneArgFunction("x - 3")
    };
}
