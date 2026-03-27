import javax.swing.JOptionPane;
public class SimpleCounting {
    public static void main(String[] args) {
        String strNum1, strNum2;
        strNum1 = JOptionPane.showInputDialog(null,
                "Please enter the first number: ","Input the first number",
                JOptionPane.INFORMATION_MESSAGE);

        strNum2 = JOptionPane.showInputDialog(null,
                "Please input the second number: ","Input the second number",
                JOptionPane.INFORMATION_MESSAGE);

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
        double sum = num1 + num2;
        JOptionPane.showMessageDialog(null, "The sum of " + num1 + " and " + num2 + " is: " + sum, "Show the sum", JOptionPane.INFORMATION_MESSAGE);
        double difference = num1 - num2;
        JOptionPane.showMessageDialog(null, "The difference of " + num1 + " and " + num2 + " is: " + difference, "Show the difference", JOptionPane.INFORMATION_MESSAGE);
        double product = num1 * num2;
        JOptionPane.showMessageDialog(null, "The product of " + num1 + " and " + num2 + " is: " + product, "Show the product", JOptionPane.INFORMATION_MESSAGE);
        double quotient = num1 / num2;
        JOptionPane.showMessageDialog(null, "The quotient of " + num1 + " and " + num2 + " is: " + quotient, "Show the quotient", JOptionPane.INFORMATION_MESSAGE);


        System.exit(0);
    }
    
}
