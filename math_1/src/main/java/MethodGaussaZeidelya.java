import java.util.ArrayList;

public class MethodGaussaZeidelya {

    private final Matrix matrix;
    private final double eps;

    public MethodGaussaZeidelya(Matrix matrix, double eps) {
        this.matrix = matrix;
        this.eps = eps;
    }

    private int[] makeSystemDiagonallyDominant() {
        int size = matrix.getSize();
        boolean[] flag = new boolean[size];
        boolean flagForStrictlyMore = false;
        int[] maxElementsRowInd = matrix.findAbsMaxElements();
        int[] maxElementsColumnInd = new int[size];

        for (int i = 0; i < size; i++) {
            double currentSum = 0;
            for (int j = 0; j < size; j++) {
                if (maxElementsRowInd[i] != j) currentSum += Math.abs(matrix.getArray().get(i).get(j));
            }

            if (!flag[i] && Math.abs(matrix.getArray().get(i).get(maxElementsRowInd[i])) >= currentSum) {
                flag[i] = true;
                maxElementsColumnInd[maxElementsRowInd[i]] = i;
                if (Math.abs(matrix.getArray().get(i).get(maxElementsRowInd[i])) > currentSum)
                    flagForStrictlyMore = true;
            } else {
                return null;
            }
        }
        int[] permuteResult = new int[size];
        System.arraycopy(maxElementsColumnInd, 0, permuteResult, 0, size);

        if (flagForStrictlyMore) {
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    matrix.swap(i, maxElementsColumnInd[j], i, j);
                }
                Utils.swapForIntArraySells(maxElementsColumnInd, maxElementsColumnInd[j], j);
            }
            return permuteResult;
        }
        return null;
    }

    public IterationMethodAnswer findAnswer() {
        int[] maxElementsColumnInd = makeSystemDiagonallyDominant();
        if (maxElementsColumnInd == null) return null;

        ArrayList<Double> answers = new ArrayList<>(matrix.getSize());
        ArrayList<Double> inaccuracy = new ArrayList<>(matrix.getSize());

        for (int i = 0; i < matrix.getSize(); i++) {
            answers.add(0.0);
        }

        boolean endOfIterations;
        int count = 0;
        ArrayList<Double> old_answers;

        do {
            count++;
            old_answers = new ArrayList<>(answers);
            for (int i = 0; i < matrix.getSize(); i++) {
                double summand = 0.0;
                for (int j = 0; j < matrix.getSize(); j++) {
                    if (j != i) summand += matrix.getArray().get(i).get(j) * answers.get(j);
                }
                answers.set(i, 1 / matrix.getArray().get(i).get(i) * (matrix.getArray().get(i).get(matrix.getSize()) - summand));
            }

            endOfIterations = true;
            for (int i = 0; i < matrix.getSize(); i++) {
                if (Math.abs(answers.get(i) - old_answers.get(i)) > eps) {
                    endOfIterations = false;
                    break;
                }
            }
        } while(!endOfIterations);

        for (int i = 0; i < matrix.getSize(); i++) {
            inaccuracy.add(answers.get(i) - old_answers.get(i));
        }

        permuteResults(maxElementsColumnInd, inaccuracy);
        permuteResults(maxElementsColumnInd, answers);
        return new IterationMethodAnswer(answers, inaccuracy, count);
    }

    private void permuteResults(int[] maxElementsColumnInd, ArrayList<Double> array) {
        int size = maxElementsColumnInd.length;
        int[] indArray = new int[size];
        System.arraycopy(maxElementsColumnInd, 0, indArray, 0, size);
        for (int i = 0; i < size; i++) {
            double tmp = array.get(i);
            array.set(i, array.get(indArray[i]));
            array.set(indArray[i], tmp);
            Utils.swapForIntArraySells(indArray, indArray[i], i);
        }
    }
}
