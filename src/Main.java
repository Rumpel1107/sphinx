import java.util.Scanner;
import java.util.Locale;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        String fileName = "balance.csv";

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            BigDecimal income = getValue(scanner, "Ingresa tus ingresos del mes: ");
            BigDecimal sumExpences = BigDecimal.ZERO;
            printWriter.println("Ingresos: " + income);

            String option = "si";
            String allCategories = "";

            while (option.equalsIgnoreCase("si")) {
                BigDecimal expenses = getValue(scanner, "Ingresa el valor del gasto: ");
                sumExpences = sumExpences.add(expenses);
                scanner.nextLine();
                printWriter.println("Gastos: " + expenses);
                System.out.println("A que correspondio este valor? (ej. Comida, Renta, etc): ");
                String category = scanner.nextLine();
                printWriter.println("Categorias: " + category);
                allCategories = allCategories + category + " - ";
                System.out.println("Deseas agregar otro gasto? (si/no): ");
                option = scanner.nextLine();
            }

            BigDecimal balance = income.subtract(sumExpences);
            printWriter.println("Balance: " + balance);
            if (balance.compareTo(BigDecimal.ZERO) > 0) {
                System.out
                        .println("El ahorro del periodo es: " + balance + " y tus gastos fueron en: " + allCategories);
            } else {
                System.out
                        .println("El deficit del periodo es: " + balance + " y tus gastos fueron en: " + allCategories);
            }

            printWriter.close();

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        scanner.close();

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
