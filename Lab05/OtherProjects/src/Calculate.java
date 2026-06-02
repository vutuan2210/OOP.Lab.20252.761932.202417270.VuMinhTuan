
import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("first number:");
        String strnum1 = scanner.nextLine();
        double num1 = Double.parseDouble(strnum1);

        System.out.println("second number:");
        String strnum2 = scanner.nextLine();
        double num2 = Double.parseDouble(strnum2);

        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;

        System.out.println("sum: " + sum);
        System.out.println("difference: " + difference);
        System.out.println("product: " + product);

        if (num2 != 0) {
            double quotient = num1 / num2;
            System.out.println("quotient: " + quotient);
        } else {
            System.out.println("Error");
        }

        scanner.close();

    }
}
