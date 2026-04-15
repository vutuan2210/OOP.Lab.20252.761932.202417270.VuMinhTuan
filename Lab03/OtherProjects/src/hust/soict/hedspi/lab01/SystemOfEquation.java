package hust.soict.hedspi.lab01;

// SystemOfEquation.java
import java.util.Scanner;

public class SystemOfEquation {
    private double a1, b1, c1;
    private double a2, b2, c2;

    public SystemOfEquation(double a1, double b1, double c1, double a2, double b2, double c2) {
        this.a1 = a1;
        this.b1 = b1;
        this.c1 = c1;
        this.a2 = a2;
        this.b2 = b2;
        this.c2 = c2;
    }

    public void solve() {
        double det = a1 * b2 - a2 * b1;
        if (det == 0) {
            System.out.println("The system has no unique solution.");
            return;
        }
        double x = (c1 * b2 - c2 * b1) / det;
        double y = (a1 * c2 - a2 * c1) / det;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coefficients for the system of equations:");
        System.out.println("Equation 1: a1*x + b1*y = c1");
        System.out.print("Enter a1: ");
        double a1 = scanner.nextDouble();
        System.out.print("Enter b1: ");
        double b1 = scanner.nextDouble();
        System.out.print("Enter c1: ");
        double c1 = scanner.nextDouble();

        System.out.println("Equation 2: a2*x + b2*y = c2");
        System.out.print("Enter a2: ");
        double a2 = scanner.nextDouble();
        System.out.print("Enter b2: ");
        double b2 = scanner.nextDouble();
        System.out.print("Enter c2: ");
        double c2 = scanner.nextDouble();

        SystemOfEquation system = new SystemOfEquation(a1, b1, c1, a2, b2, c2);
        system.solve();

        scanner.close();
    }
}
