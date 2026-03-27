import javax.swing.JOptionPane;
public class ChoosingOption {
    public static void main(String[] srgs){
        Object[] options = {"I do", "I don't"};
        int option = JOptionPane.showOptionDialog(null, "Do you want to change to the first class ticket?", "Custom option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        String result = options[option].toString();

        JOptionPane.showMessageDialog(null, "You have chosen: " + result);
        System.exit(0);
    }
}
