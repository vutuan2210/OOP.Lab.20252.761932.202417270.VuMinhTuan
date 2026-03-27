import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int month = -1;
        int year = -1;

        while (month == -1) {
            System.out.print("Enter month: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "january": case "jan": case "jan.": case "1":
                    month = 1; break;
                case "february": case "feb": case "feb.": case "2":
                    month = 2; break;
                case "march": case "mar": case "mar.": case "3":
                    month = 3; break;
                case "april": case "apr": case "apr.": case "4":
                    month = 4; break;
                case "may": case "5":
                    month = 5; break;
                case "june": case "jun": case "jun.": case "6":
                    month = 6; break;
                case "july": case "jul": case "jul.": case "7":
                    month = 7; break;
                case "august": case "aug": case "aug.": case "8":
                    month = 8; break;
                case "september": case "sep": case "sep.": case "9":
                    month = 9; break;
                case "october": case "oct": case "oct.": case "10":
                    month = 10; break;
                case "november": case "nov": case "nov.": case "11":
                    month = 11; break;
                case "december": case "dec": case "dec.": case "12":
                    month = 12; break;
                default:
                    System.out.println("Invalid month, please enter again!");
            }
        }

        while (true) {
            System.out.print("Enter year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) break;
            } else {
                scanner.next(); 
            }
            System.out.println("Invalid year, please enter again!");
        }

        boolean isLeap = (year % 4 == 0 && year % 100 != 0) 
                        || (year % 400 == 0);

        int days;
        switch (month) {
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                days = 31; break;
            case 4: case 6: case 9: case 11:
                days = 30; break;
            case 2:
                days = isLeap ? 29 : 28; break;
            default:
                days = 0; 
        }

        System.out.println("Number of days: " + days);

        scanner.close();
    }
}