public class Utils {
    public static void swapForIntArraySells(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    public static double roundDouble(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }
}
