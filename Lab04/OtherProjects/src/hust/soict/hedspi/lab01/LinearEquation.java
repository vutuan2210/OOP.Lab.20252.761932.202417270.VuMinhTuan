package hust.soict.hedspi.lab01;

//Example 4: Solve a linear equation
import javax.swing.JOptionPane;
public class LinearEquation {
    public static void main(String[] args) {
        String strA, strB;
        strA = JOptionPane.showInputDialog(null,
                "Please enter the coefficient a: ","Input the coefficient a",
                JOptionPane.INFORMATION_MESSAGE);

        strB = JOptionPane.showInputDialog(null,
                "Please input the coefficient b: ","Input the coefficient b",
                JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, "The equation has infinitely many solutions.", "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "The equation has no solution.", "Show the solution", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            double solution = -b / a;
            JOptionPane.showMessageDialog(null, "The solution of the equation " + a + "x + " + b + " = 0 is: x = " + solution, "Show the solution", JOptionPane.INFORMATION_MESSAGE);
        }

        System.exit(0);
    }
}
