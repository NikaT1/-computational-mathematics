import java.util.ArrayList;

public class Matrix {
    private final int size;
    private final ArrayList<ArrayList<Double>> array;

    public Matrix(ArrayList<ArrayList<Double>> array, int size) {
        this.size = size;
        this.array = array;
    }

    public void swap(int i1, int j1, int i2, int j2) {
        double tmp = array.get(i1).get(j1);
        array.get(i1).set(j1, array.get(i2).get(j2));
        array.get(i2).set(j2, tmp);
    }

    public int[] findAbsMaxElements() {
        int[] maxElementsInd = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (array.get(i).get(maxElementsInd[i]) < Math.abs(array.get(i).get(j))) maxElementsInd[i] = j;
            }
        }
        return maxElementsInd;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<ArrayList<Double>> getArray() {
        return array;
    }
}
