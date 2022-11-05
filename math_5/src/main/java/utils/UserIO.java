package utils;

import data.DiscreteFunction;
import data.EulerFunction;
import java.util.Scanner;

import static functions.FunctionsForClient.EULER_FUNCTIONS;
import static functions.FunctionsForClient.FUNCTIONS;

public class UserIO {
    private final Scanner scanner;

    public UserIO() {
        scanner = new Scanner(System.in);
    }

    public void printHelloInformation() {
        printMessage("----Метод Эйлера----");
    }

    public void printDiscreteFunction(DiscreteFunction function) {
        for (int i = 0; i < function.getX().size(); i++) {
            printMessage("x: " + function.getX().get(i) + ", y: " + function.getY().get(i));
        }
    }

    private int readItemNumber(String[] items) {
        int number = 0;
        boolean correctInput;
        do {
            correctInput = true;
            try {
                StringBuilder message = new StringBuilder("Функции:\n");
                for (int i = 0; i < items.length; i++) {
                    message.append(i + 1).append(") ").append(items[i]).append("\n");
                }
                number = Integer.parseInt(readValue(message + "Введите номер выбранной функции: "));
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

    public double readPoint(String i) {
        double number = 0;
        boolean correctInput;
        do {
            correctInput = true;
            try {
                number = Double.parseDouble(readValue(i + ": ").replace(",", "."));
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод");
                correctInput = false;
            }
        } while (!correctInput);
        return number;
    }

    public EulerFunction getFunction() {
        String[] functions = new String[FUNCTIONS.length];
        for (int i = 0; i < FUNCTIONS.length; i++) {
            functions[i] = FUNCTIONS[i].getStringFunc();
        }
        int number = readItemNumber(functions);
        return EULER_FUNCTIONS[number - 1];
    }

    private String readValue(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}