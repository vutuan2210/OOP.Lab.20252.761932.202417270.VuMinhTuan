    import java.util.Scanner;

    public class equation {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.println("ax + b = 0 ?");
            double a = sc.nextDouble();
            double b = sc.nextDouble();

            if (a == 0) {
                if (b == 0) {
                    System.out.println("Infinite solutions");
                } else {
                    System.out.println("No solution");
                }
            } else {
                double x = -b / a;
                System.out.println("x = " + x);
            }

            System.out.println("\nSolve system:");
            double a11 = sc.nextDouble();
            double a12 = sc.nextDouble();
            double b1 = sc.nextDouble();
            double a21 = sc.nextDouble();
            double a22 = sc.nextDouble();
            double b2 = sc.nextDouble();

            double D = a11 * a22 - a21 * a12;
            double D1 = b1 * a22 - b2 * a12;
            double D2 = a11 * b2 - a21 * b1;

            if (D != 0) {
                double x1 = D1 / D;
                double x2 = D2 / D;
                System.out.println("x1 = " + x1 + ", x2 = " + x2);
            } else {
                if (D1 == 0 && D2 == 0) {
                    System.out.println("Infinite solutions");
                } else {
                    System.out.println("No solution");
                }
            }

            // ===== 3. Quadratic equation =====
            System.out.println("\nSolve ax^2 + bx + c = 0");
            double a2 = sc.nextDouble();
            double b2q = sc.nextDouble();
            double c = sc.nextDouble();

            if (a2 == 0) {
                if (b2q == 0) {
                    if (c == 0) {
                        System.out.println("Infinite solutions");
                    } else {
                        System.out.println("No solution");
                    }
                } else {
                    System.out.println("x = " + (-c / b2q));
                }
            } else {
                double delta = b2q * b2q - 4 * a2 * c;

                if (delta > 0) {
                    double x1 = (-b2q + Math.sqrt(delta)) / (2 * a2);
                    double x2 = (-b2q - Math.sqrt(delta)) / (2 * a2);
                    System.out.println("x1 = " + x1 + ", x2 = " + x2);
                } else if (delta == 0) {
                    double x = -b2q / (2 * a2);
                    System.out.println("root x = " + x);
                } else {
                    System.out.println("No solution");
                }
            }

            sc.close();
        }
    }