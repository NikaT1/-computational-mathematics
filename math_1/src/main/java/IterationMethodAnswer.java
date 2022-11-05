import java.util.ArrayList;

public class IterationMethodAnswer {
    private final ArrayList<Double> xArray;
    private final ArrayList<Double> inaccuracy;
    private final int iterationCount;

    public IterationMethodAnswer(ArrayList<Double> xArray, ArrayList<Double> inaccuracy, int iterationCount) {
        this.xArray = xArray;
        this.inaccuracy = inaccuracy;
        this.iterationCount = iterationCount;
    }

    public ArrayList<Double> getxArray() {
        return xArray;
    }

    public ArrayList<Double> getInaccuracy() {
        return inaccuracy;
    }

    public int getIterationCount() {
        return iterationCount;
    }

}
