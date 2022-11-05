package methods;

public class MethodAnswer {
    private final double result;
    private final double inaccuracy;
    private final int segmentsCount;
    private final String errorMessage;

    public MethodAnswer(double result, double inaccuracy, int segmentsCount, String errorMessage) {
        this.result = result;
        this.inaccuracy = inaccuracy;
        this.segmentsCount = segmentsCount;
        this.errorMessage = errorMessage;
    }

    public double getResult() {
        return result;
    }

    public double getInaccuracy() {
        return inaccuracy;
    }

    public int getSegmentsCount() {
        return segmentsCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
