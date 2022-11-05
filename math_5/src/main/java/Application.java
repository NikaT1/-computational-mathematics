import chart.ChartBuilder;
import data.DiscreteFunction;
import data.EulerFunction;
import data.OneArgFunction;
import methods.MethodEuler;
import methods.MethodLagrange;
import utils.UserIO;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        UserIO userIO = new UserIO();
        userIO.printHelloInformation();
        long counter =  0;
        try {
            while (true) {
                Thread.sleep(500);
                counter++;
                if (counter == 1000000000000000000L) break;
            }
            EulerFunction function = userIO.getFunction();
            DiscreteFunction discreteFunction = solveByEuler(function, userIO);
            userIO.printMessage("Задача Коши решена");
            userIO.printDiscreteFunction(discreteFunction);
            OneArgFunction polynomial = new MethodLagrange().calculatePolynomial(discreteFunction);
            userIO.printMessage("Полином построен");
            ChartBuilder.setPolynomial(polynomial, "Решение задачи Коши");
            userIO.printMessage("Идет создание графиков...");
            new ChartBuilder().build();
        } catch (ArithmeticException e) {
            userIO.printMessage("Обнаружено деление на ноль!");
        } catch (IllegalArgumentException e) {
            userIO.printMessage("Функция не определена в выбранных точках!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private DiscreteFunction solveByEuler(EulerFunction eulerFunction, UserIO userIO) {
        double x0 = userIO.readPoint("Введите x0");
        double y0 = userIO.readPoint("Введите y0");
        double xn = userIO.readPoint("Введите xn");
        while (xn <= x0) {
            userIO.printMessage("неверное значение xn");
            xn = userIO.readPoint("Введите xn");
        }
        double h = userIO.readPoint("Введите шаг");
        while (h <= 0) {
            userIO.printMessage("неверное значение xn");
            h = userIO.readPoint("Введите шаг");
        }
        ChartBuilder.setBounds(x0, xn);
        double constant = eulerFunction.getConstantFunction().calculate(new double[]{x0, y0});
        ChartBuilder.setAnalyticalFunction(eulerFunction.getAnalyticalAnswer(), constant);
        MethodEuler methodEuler = new MethodEuler();
        return methodEuler.findAnswer(x0, y0, xn, eulerFunction.getFunction(), h);
    }
}
