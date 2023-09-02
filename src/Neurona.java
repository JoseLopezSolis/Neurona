import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Neurona {
    public static int n;
    private static final List<Double> XValues = new ArrayList<>();
    private static final List<Double> wValues = new ArrayList<>();
    public static double bValue;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        setDataRequested();
        saveDataToCSV("XValues.csv", XValues);
        saveDataToCSV("wValues.csv", wValues);
    }

    public static void setDataRequested() {
        setNValue();
        setLisValues(XValues, "X");
        setLisValues(wValues, "W");
        setBValue();
        showResult();
    }

    public static void showResult() {
        System.out.println("Result: " + getY());
    }

    public static void setNValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the n number:");
        n = scanner.nextInt();
    }

    public static void setLisValues(List<Double> listValues, String selector) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter the " + selector + " value for element " + i + ": ");
            double number = scanner.nextDouble();
            listValues.add(number);
        }
    }

    public static void setBValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the b number:");
        bValue = scanner.nextDouble();
    }

    public static double getY() {
        /*
          y = ∑(x w) + b
         */
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += XValues.get(i) * wValues.get(i);
        }
        return result + bValue;
    }

    public static void saveDataToCSV(String fileName, List<Double> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            StringBuilder line = new StringBuilder();
            for (double value : data) {
                line.append(Double.toString(value));
                line.append(",");
            }
            // Remove the trailing comma
            if (!line.isEmpty()) {
                line.setLength(line.length() - 1);
            }
            writer.append(line.toString());
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
