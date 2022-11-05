import data.OneArgEquation;
import data.TwoFuncSystem;
import methods.MethodAnswer;
import methods.MethodNewton;
import methods.MethodSecant;
import methods.MethodTangent;
import utils.UserIO;

import static java.lang.Math.abs;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        UserIO userIO = new UserIO();
        userIO.printHelloInformation();
        int format = userIO.readFormat();
        try {
            if (format == 2) return;
            if (format == 1) solveSystem(userIO.getSystem(), userIO);
            else solveEquation(userIO.getEquation(), userIO);
        } catch (ArithmeticException e) {
            userIO.printMessage("Обнаружено деление на ноль!");
        }
    }

    private void solveEquation(OneArgEquation equation, UserIO userIO) {
        double eps = userIO.readAccuracy();
        double a;
        double b;
        do {
            a = userIO.readDoubleValue("Введите левую границу интервала: ");
            b = userIO.readDoubleValue("Введите правую границу интервала: ");
        } while (a >= b);
        MethodSecant methodSecant = new MethodSecant();
        userIO.printMessage("---- Результат метода хорд ----");
        MethodAnswer secantAnswer = methodSecant.findAnswer(equation, eps, a, b, 150);
        userIO.printAnswer(secantAnswer);
        MethodTangent methodTangent = new MethodTangent();
        userIO.printMessage("\n---- Результат метода касательных ----");
        MethodAnswer tangentAnswer = methodTangent.findAnswer(equation, eps, a, b, 150);
        userIO.printAnswer(tangentAnswer);
        if (tangentAnswer.getErrorMessage().equals("") && secantAnswer.getErrorMessage().equals("")) {
            userIO.printMessage("---- Сравнение методов ----");
            userIO.printMessage("Разница между ответами: " + abs(secantAnswer.getxArray()[0] - tangentAnswer.getxArray()[0]));
        }
    }

    private void solveSystem(TwoFuncSystem system, UserIO userIO) {
        double eps = userIO.readAccuracy();
        double x;
        double y;
        x = userIO.readDoubleValue("Введите приближение для x: ");
        y = userIO.readDoubleValue("Введите приближение для y: ");
        MethodNewton methodNewton = new MethodNewton();
        userIO.printMessage("---- Результат метода Ньютона ----");
        MethodAnswer newtonAnswer = methodNewton.findAnswer(system, eps, x, y, 150);
        userIO.printAnswer(newtonAnswer);
    }
}
