public class Main {

    public static void main(String[] args) {
        UserIO userIO = new UserIO();
        userIO.printHelloInformation();
        Matrix matrix = userIO.getMatrix();
        if (matrix.getArray() == null) return;
        double eps = userIO.readAccuracy();
        MethodGaussaZeidelya method = new MethodGaussaZeidelya(matrix, eps);
        IterationMethodAnswer answer = method.findAnswer();
        userIO.printAnswer(answer);
    }
}
