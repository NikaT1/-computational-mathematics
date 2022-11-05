package utils;

import data.OneArgEquation;
import methods.MethodAnswer;

import java.util.Scanner;

import static functions.functionsForClient.EQUATIONS;

public class UserIO {
    private final Scanner scanner;

    public UserIO() {
        scanner = new Scanner(System.in);
    }

    public void printHelloInformation() {
        printMessage("----Метод прямоугольников----");
        printMessage("Вводить коэффициенты следует построчно, через пробел.");
        printMessage("Пример ввода: a b c d...");
        printMessage("Устранение разрыва происходит путем использования алгоритмического среднего от значения\n" +
                "от двух соседних точек функции \uD835\uDC53(\uD835\uDC65 − \uD835\uDF00), " +
                "\uD835\uDC53(\uD835\uDC65 + \uD835\uDF00).");
    }

    public void printAnswer(MethodAnswer answer) {
        if (answer.getErrorMessage().equals("")) {
            printMessage("Ответ: " + answer.getResult());
            printMessage("Количество разбиений: " + answer.getSegmentsCount());
            printMessage("Погрешность: " + answer.getInaccuracy());
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
                StringBuilder message = new StringBuilder("Уравнения:\n");
                for (int i = 0; i < items.length; i++) {
                    message.append(i + 1).append(") ").append(items[i]).append("\n");
                }
                number = Integer.parseInt(readValue(message + "Введите номер выбранного уравнения: "));
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
        double[] coefficients = readCoefficients(3);
        OneArgEquation equation = EQUATIONS[number - 1];
        equation.setCoefficients(coefficients);
        return equation;
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