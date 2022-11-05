package methods;

import java.util.ArrayList;

public class MethodAnswer {
    private final double[] xArray;
    private final double[] inaccuracy;
    private final int iterationCount;
    private final String errorMessage;

    public MethodAnswer(double[] xArray, double[] inaccuracy, int iterationCount, String errorMessage) {
        this.xArray = xArray;
        this.inaccuracy = inaccuracy;
        this.iterationCount = iterationCount;
        this.errorMessage = errorMessage;
    }

    public double[] getxArray() {
        return xArray;
    }

    public double[] getInaccuracy() {
        return inaccuracy;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
