import data.OneArgEquation;
import methods.MethodAnswer;
import methods.MethodRectangle;
import utils.UserIO;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        UserIO userIO = new UserIO();
        userIO.printHelloInformation();
        try {
            findIntegral(userIO.getEquation(), userIO);
        } catch (ArithmeticException e) {
            userIO.printMessage("Обнаружено деление на ноль!");
        }
    }

    private void findIntegral(OneArgEquation equation, UserIO userIO) {
        double eps = userIO.readAccuracy();
        double a;
        double b;
        do {
            a = userIO.readDoubleValue("Введите левую границу интервала: ");
            b = userIO.readDoubleValue("Введите правую границу интервала: ");
        } while (a >= b);
        MethodRectangle methodRectangle = new MethodRectangle();
        userIO.printMessage("---- Результат метода левых прямоугольников ----");
        MethodAnswer rectangleAnswer = methodRectangle.findAnswer(equation, eps, a, b, 20, 0);
        userIO.printAnswer(rectangleAnswer);
        userIO.printMessage("---- Результат метода правых прямоугольников ----");
        rectangleAnswer = methodRectangle.findAnswer(equation, eps, a, b, 20, 1);
        userIO.printAnswer(rectangleAnswer);
        userIO.printMessage("---- Результат метода средних прямоугольников ----");
        rectangleAnswer = methodRectangle.findAnswer(equation, eps, a, b, 20, 0.5);
        userIO.printAnswer(rectangleAnswer);
    }
}
