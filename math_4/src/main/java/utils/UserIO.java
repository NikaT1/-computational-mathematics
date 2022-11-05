package utils;

import data.DiscreteFunction;
import data.OneArgFunction;

import java.util.ArrayList;
import java.util.Scanner;

import static functions.functionsForClient.FUNCTIONS;

public class UserIO {
    private final Scanner scanner;

    public UserIO() {
        scanner = new Scanner(System.in);
    }

    public void printHelloInformation() {
        printMessage("----Метод интерполяции полиномом Лагранжа----");
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

    private ArrayList<Double> readPoints() {
        int count = 0;
        ArrayList<Double> array = new ArrayList<>();
        boolean correctInput;
        do {
            correctInput = true;
            try {
                count = Integer.parseInt(readValue("Введите количество точек: "));
                if (count < 1 || count > 1000) {
                    printMessage("Значение некорректно, повторите ввод:");
                    correctInput = false;
                }
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод:");
                correctInput = false;
            }

        } while (!correctInput);
        for (int i = 0; i < count; i++) {
            array.add(readPoint(i + 1));
        }
        return array;
    }

    private double readPoint(int i) {
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

    private boolean readBooleanValue(String message) {
        boolean value = false;
        boolean correctInput;
        do {
            correctInput = true;
            try {
                String strValue = readValue(message);
                if (strValue.equals("да")) value = true;
                else if (!strValue.equals("нет")) correctInput = false;
            } catch (NumberFormatException ex) {
                printMessage("Неверный формат данных, повторите ввод (да/нет)");
                correctInput = false;
            }
        } while (!correctInput);
        return value;
    }

    public DiscreteFunction getFunction() {
        String[] functions = new String[FUNCTIONS.length];
        for (int i = 0; i < FUNCTIONS.length; i++) {
            functions[i] = FUNCTIONS[i].getStringFunc();
        }
        int number = readItemNumber(functions);
        OneArgFunction function = FUNCTIONS[number - 1];
        ArrayList<Double> array = readPoints();
        boolean noise = readBooleanValue("Добавить шум? (да/нет)");
        return new DiscreteFunction(array, function, noise);
    }

    private String readValue(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}