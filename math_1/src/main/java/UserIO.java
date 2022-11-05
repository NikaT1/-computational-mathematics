import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class UserIO {
    private Scanner scanner;
    private boolean printMessages;

    public UserIO() {
        scanner = new Scanner(System.in);
        printMessages = true;
    }

    public void printHelloInformation() {
        System.out.println("----Решение СЛАУ методом Гаусса-Зейделя----");
        System.out.println("Вводить коэфициенты следует построчно, через пробел.");
        System.out.println("Пример ввода: a1 a2 a3 ... an b1");
    }

    private void printMatrix(ArrayList<ArrayList<Double>> coefficients, int size) {
        System.out.println("Матрица:");
        for (int i = 0; i < size; i++) {
            coefficients.get(i).forEach(x -> System.out.print(x + "  "));
            System.out.println();
        }
    }

    public void printAnswer(IterationMethodAnswer answer) {
        if (answer != null) {
            System.out.println("Получены ответы (в порядке Х1..Хn):");
            for (int i = 0; i < answer.getxArray().size(); i++) {
                System.out.println("x" + (i + 1) + " = " + answer.getxArray().get(i));
            }
            System.out.println();
            System.out.println("Количество итераций: " + answer.getIterationCount());
            System.out.println("Погрешности (в порядке для Х1..Хn):");
            for (int i = 0; i < answer.getInaccuracy().size(); i++) {
                System.out.println("для x" + (i + 1) + ": " + answer.getInaccuracy().get(i));
            }
        } else {
            System.out.println("Невозможно достигнуть диагонального преобладания!");
        }
    }

    public Matrix getMatrix() {
        ArrayList<ArrayList<Double>> coefficients = null;
        int format = readFormat();
        int size = 0;
        switch (format) {
            case (0):
                size = readSize();
                coefficients = readData(size);
                break;
            case (1):
                setFileStreamForScanner();
                printMessages = false;
                size = readSize();
                coefficients = readDataFromFile(size);
                printMessages = true;
                scanner = new Scanner(System.in);
                break;
            case (2):
                size = readSize();
                coefficients = generateRandomData(size);
                break;
            case (3):
                break;
        }
        return new Matrix(coefficients, size);
    }

    private String readField(String message) {
        String s;
        if (printMessages) System.out.println(message);
        s = scanner.nextLine();
        return s;
    }

    public int readFormat() {
        int format = 0;
        boolean flag = false;
        while (!flag) {
            flag = true;
            try {
                format = Integer.parseInt(readField("Введите способ ввода данных (0 - ввод с клавиатуры, " +
                        "1 - ввод из файла, 2 - рандомные числа, 3 - выход):"));
                if (format < 0 || format > 3) {
                    System.out.println("Значение некорректно, повторите ввод:");
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат данных, повторите ввод:");
                flag = false;
            }
        }
        return format;
    }

    public void setFileStreamForScanner() {
        boolean flag = false;
        while (!flag) {
            flag = true;
            try {
                String fileName = readField("Введите имя файла: ");
                FileInputStream fileInputStream = new FileInputStream(fileName);
                scanner = new Scanner(fileInputStream);
            } catch (FileNotFoundException ex) {
                System.out.println("Неверное имя файла, повторите ввод:");
                flag = false;
            }
        }
    }

    public int readSize() {
        int size = 0;
        boolean flag = false;
        if (printMessages) {
            while (!flag) {
                flag = true;
                try {
                    size = Integer.parseInt(readField("Введите размер системы (больше 0 и меньше 21):"));
                    if (size <= 0 || size > 20) {
                        System.out.println("Значение некорректно, повторите ввод:");
                        flag = false;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Неверный формат данных, повторите ввод:");
                    flag = false;
                }
            }
        } else {
            try {
                size = Integer.parseInt(readField(""));
                if (size <= 0 || size > 20) {
                    System.out.println("Неверный размер системы (должен быть целым числом от 1 до 20)");
                    size = 0;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Неверный размер системы (должен быть целым числом от 1 до 20)");
                size = 0;
            }
        }
        return size;
    }

    public double readAccuracy() {
        double eps = 1;
        boolean flag = false;

        while (!flag) {
            flag = true;
            try {
                eps = Double.parseDouble(readField("Введите точность (больше 0 и меньше 1):"));
                if (eps <= 0 || eps >= 1) {
                    System.out.println("Значение некорректно, повторите ввод:");
                    flag = false;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат данных, повторите ввод:");
                flag = false;
            }
        }

        return eps;
    }

    private ArrayList<ArrayList<Double>> generateRandomData(int size) {
        ArrayList<ArrayList<Double>> coefficients = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            coefficients.add(new ArrayList<>());
            double currentSum = 0;
            for (int j = 0; j < size + 1; j++) {
                coefficients.get(i).add(Utils.roundDouble(rand.nextDouble() * 200 - rand.nextDouble() * 200));
                currentSum += Math.abs(coefficients.get(i).get(j));
            }
            coefficients.get(i).set(i, Utils.roundDouble(currentSum + rand.nextDouble() * 10));
        }
        printMatrix(coefficients, size);
        return coefficients;
    }

    private ArrayList<ArrayList<Double>> readData(int size) {
        ArrayList<ArrayList<Double>> coefficients = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            boolean flag = false;
            coefficients.add(new ArrayList<>());
            while (!flag) {
                flag = true;
                try {
                    String[] numbers = readField("Введите коэффициенты (" + (i + 1) + " строка):").replace(',', '.').split(" ");
                    if (numbers.length != size + 1) {
                        System.out.println("Количество коэффициентов некорректно, повторите ввод:");
                        flag = false;
                        continue;
                    }
                    for (String number : numbers) {
                        coefficients.get(i).add(Double.parseDouble(number));
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Неверный формат данных, повторите ввод:");
                    coefficients.get(i).clear();
                    flag = false;
                }
            }
        }
        return coefficients;
    }

    private ArrayList<ArrayList<Double>> readDataFromFile(int size) {
        ArrayList<ArrayList<Double>> coefficients = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            coefficients.add(new ArrayList<>());
            try {
                String[] numbers = readField("").replace(',', '.').split(" ");
                if (numbers.length != size + 1) {
                    System.out.println("Количество коэффициентов в строке " + (i + 1) +
                            " некорректно, пожалуйста, исправьте данные в файле");
                    flag = false;
                    break;
                }
                for (String number : numbers) {
                    coefficients.get(i).add(Double.parseDouble(number));
                }
            } catch (NumberFormatException ex) {
                System.out.println("Неверный формат данных! Пожалуйста, исправьте данные в файле");
                flag = false;
                break;
            }
        }
        if (flag && size != 0) {
            printMatrix(coefficients, size);
            return coefficients;
        }
        return null;
    }

}
