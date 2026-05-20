package hust.soict.hedspi.lab01;

// Solve a second degree equation
import javax.swing.JOptionPane;
public class SecondDegreeEquation {
    public static void main(String[] args) {
        String strA, strB, strC;
        strA = JOptionPane.showInputDialog(null,
                "Please enter the coefficient a: ","Input the coefficient a",
                JOptionPane.INFORMATION_MESSAGE);

        strB = JOptionPane.showInputDialog(null,
                "Please input the coefficient b: ","Input the coefficient b",
                JOptionPane.INFORMATION_MESSAGE);

        strC = JOptionPane.showInputDialog(null,
                "Please input the coefficient c: ","Input the coefficient c",
                JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    JOptionPane.showMessageDialog(null, "The equation has infinitely many solutions.", "Show the solution", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "The equation has no solution.", "Show the solution", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                double solution = -c / b;
                JOptionPane.showMessageDialog(null, "The solution of the equation " + b + "x + " + c + " = 0 is: x = " + solution, "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                JOptionPane.showMessageDialog(null, "The equation has no real solution.", "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            } else if (delta == 0) {
                double solution = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "The equation has one real solution: x = " + solution, "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            } else {
                double sqrtDelta = Math.sqrt(delta);
                double solution1 = (-b + sqrtDelta) / (2 * a);
                double solution2 = (-b - sqrtDelta) / (2 * a);
                JOptionPane.showMessageDialog(null, "The equation has two real solutions: x1 = " + solution1 + ", x2 = " + solution2, "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        System.exit(0);
    }
}
