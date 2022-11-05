import chart.ChartBuilder;
import data.DiscreteFunction;
import data.OneArgFunction;
import methods.MethodLagrange;
import utils.UserIO;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        UserIO userIO = new UserIO();
        userIO.printHelloInformation();
        try {
            DiscreteFunction function = userIO.getFunction();
            OneArgFunction polynomial = new MethodLagrange().calculatePolynomial(function);
            ChartBuilder.setPolynomial(polynomial, "Полином Лагранжа");
            ChartBuilder.setDiscreteFunction(function, "Функция " + function.getFunction().getStringFunc());
            userIO.printMessage("Полином построен, идет создание графиков...");
            new ChartBuilder().build();
        } catch (ArithmeticException e) {
            userIO.printMessage("Обнаружено деление на ноль!");
        } catch (IllegalArgumentException e) {
            userIO.printMessage("Функция не определена в выбранных точках!");
        }
    }
}
