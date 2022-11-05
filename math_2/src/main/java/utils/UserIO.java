package utils;

import data.OneArgEquation;
import data.TwoFuncSystem;
import methods.MethodAnswer;

import java.util.Scanner;

import static functions.functionsForClient.EQUATIONS;
import static functions.functionsForClient.SYSTEMS;

public class UserIO {
    private final Scanner scanner;

    public UserIO() {
        scanner = new Scanner(System.in);
    }

    public void printHelloInformation() {
        printMessage("----Решение нелинейных уравнений и систем нелинейных уравнений----");
        printMessage("Вводить коэффициенты следует построчно, через пробел.");
        printMessage("Пример ввода: a b c d...");
    }

    public void printAnswer(MethodAnswer answer) {
        if (answer.getErrorMessage().equals("")) {
            printMessage("Получены ответы:");
            if (answer.getxArray().length > 0) printMessage("x = " + answer.getxArray()[0]);
            if (answer.getxArray().length > 1) printMessage("y = " + answer.getxArray()[1]);
            printMessage("\nКоличество итераций: " + answer.getIterationCount() + "\n");
            printMessage("Погрешности:");
            if (answer.getInaccuracy().length > 0) printMessage("для х: " + answer.getInaccuracy()[0]);
            if (answer.getInaccuracy().length > 1) printMessage("для y: " + answer.getInaccuracy()[1]);
        } else {
            printMessage(answer.getErrorMessage());
        }
    }

    private int readItemNumber(String[] items) {
        int number = 0;
        boolean correctInput;
        do {
            correctInput = true;
            try {
                StringBuilder message = new StringBuilder("Уравнения/Системы:\n");
                for (int i = 0; i < items.length; i++) {
                    message.append(i + 1).append(") ").append(items[i]).append("\n");
                }
                number = Integer.parseInt(readValue(message + "Введите номер выбранного уравнения (системы): "));
                if (number < 1 || number > items.length) {
                    printMessage("Значение некорректно, повторите ввод:");
                    correctInput = false;
                }
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод:");
                correctInput = false;
            }
        } while (!correctInput);
        return number;
    }

    public OneArgEquation getEquation() {
        String[] functions = new String[EQUATIONS.length];
        for (int i = 0; i < EQUATIONS.length; i++) functions[i] = EQUATIONS[i].getStringFunc();
        int number = readItemNumber(functions);
        double[] coefficients = readCoefficients(4);
        OneArgEquation equation = EQUATIONS[number - 1];
        equation.setCoefficients(coefficients);
        return equation;
    }

    public TwoFuncSystem getSystem() {
        String[] systems = new String[SYSTEMS.length];
        for (int i = 0; i < SYSTEMS.length; i++) systems[i] = SYSTEMS[i].getStringFunc();
        int number = readItemNumber(systems);
        double[][] coefficients = new double[2][];
        printMessage("--- Ввод коэффициентов для первого уравнения ---");
        coefficients[0] = readCoefficients(4);
        printMessage("--- Ввод коэффициентов для второго уравнения ---");
        coefficients[1] = readCoefficients(4);
        TwoFuncSystem system = SYSTEMS[number - 1];
        system.setCoefficients(coefficients);
        return system;
    }

    private double[] readCoefficients(int count) {
        double[] coefficients = new double[count];
        boolean correctInput;
        do {
            correctInput = true;
            try {
                String[] numbers = readValue("Введите " + count + " коэффициента(ов): ").replace(',', '.').split(" ");
                if (numbers.length != count) {
                    printMessage("Количество коэффициентов некорректно, повторите ввод:");
                    correctInput = false;
                    continue;
                }
                for (int i = 0; i < count; i++) {
                    coefficients[i] = Double.parseDouble(numbers[i]);
                }
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод:");
                correctInput = false;
            }
        } while (!correctInput);
        return coefficients;
    }

    private String readValue(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public int readFormat() {
        int format = 0;
        boolean correctInput;
        do {
            correctInput = true;
            try {
                format = Integer.parseInt(readValue("Введите вид задачи (0 - нелинейное уравнение, " +
                        "1 - система нелинейных уравнений, 2 - выход):"));
                if (format < 0 || format > 2) {
                    printMessage("Значение некорректно, повторите ввод:");
                    correctInput = false;
                }
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод:");
                correctInput = false;
            }
        } while (!correctInput);
        return format;
    }

    public double readAccuracy() {
        double eps;
        boolean continueFlag;

        do {
            continueFlag = true;
            eps = readDoubleValue("Введите точность (больше 0 и меньше 1):");
            if (eps <= 0 || eps >= 1) {
                printMessage("Значение некорректно, повторите ввод:");
                continueFlag = false;
            }
        } while (!continueFlag);

        return eps;
    }


    public double readDoubleValue(String message) {
        double value = 1;
        boolean continueFlag;
        do {
            continueFlag = true;
            try {
                value = Double.parseDouble(readValue(message));
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод:");
                continueFlag = false;
            }
        } while (!continueFlag);

        return value;
    }
}