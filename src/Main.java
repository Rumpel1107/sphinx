import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        String fileName = "balance.csv";

        savetoCSV(scanner, fileName);

        scanner.close();

    }

    public static void savetoCSV(Scanner scanner, String fileName) {

        File newFile = new File(fileName);
        boolean isNew = !newFile.exists();

        try (FileWriter fileWriter = new FileWriter(fileName, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);) {

            if (isNew) {
                printWriter.println("Tipo,Valor,Categoria");
            }
            BigDecimal income = getValue(scanner, "Ingresa El valor de tu ingreso: ");
            BigDecimal sumExpences = BigDecimal.ZERO;
            scanner.nextLine();
            System.out.println("A que correspondio este ingreso?: ");
            String category = scanner.nextLine();
            printWriter.println("Ingreso," + income + "," + category);

            String option = "si";
            String allCategories = "";

            while (option.equalsIgnoreCase("si")) {
                BigDecimal expenses = getValue(scanner, "Ingresa el valor del gasto: ");
                sumExpences = sumExpences.add(expenses);
                scanner.nextLine();
                System.out.println("A que correspondio este valor? (ej. Comida, Renta, etc): ");
                category = scanner.nextLine();

                if (allCategories.isEmpty()) {
                    allCategories = category;
                } else {
                    allCategories = allCategories + " - " + category;
                }

                printWriter.println("Gasto," + expenses + "," + category);
                System.out.println("Deseas agregar otro gasto? (si/no): ");
                option = scanner.nextLine();
            }

            BigDecimal balance = income.subtract(sumExpences);
            if (balance.compareTo(BigDecimal.ZERO) > 0) {
                System.out
                        .println("El ahorro del periodo es: " + balance + " y tus gastos fueron en: " + allCategories);
            } else {
                System.out
                        .println("El deficit del periodo es: " + balance + " y tus gastos fueron en: " + allCategories);
            }

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static BigDecimal getValue(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("Error al ingresar los datos");
                System.out.println("Por favor usa UNICAMENTE formato numerico.");
                scanner.nextLine();
            }

        }
    }

}
