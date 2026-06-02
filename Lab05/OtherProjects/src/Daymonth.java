import java.util.Scanner;

public class Daymonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int month;
        int year;
        while (true) {
            System.out.print("Enter month (name/abbreviation/number): ");
            String inputMonth = normalize(scanner.nextLine());
            month = parseMonth(inputMonth);
            if (month != -1) break;
            System.out.println("Invalid month. Please try again.");
        }
                
        while (true) {
            System.out.print("Enter year (non-negative integer): ");
            String inputYear = scanner.nextLine().trim();
            if (inputYear.matches("\\d+")) {
                try {
                    year = Integer.parseInt(inputYear);
                    if (year >= 0 && year <= 9999) break;
                } catch (NumberFormatException e) {
                }
            }
            System.out.println("Invalid year. Please enter again.");
        }
                        
        int days = getDaysInMonth(month, year);
        System.out.println("Month " + month + " in year " + year + " has " + days + " days.");
        scanner.close();
    }

    public static String normalize(String input) {
        return input.trim().toLowerCase().replace(".", "");
    }

    public static int parseMonth(String input) {
        switch (input) {
            case "1": case "jan": case "january": return 1;
            case "2": case "feb": case "february": return 2;
            case "3": case "mar": case "march": return 3;
            case "4": case "apr": case "april": return 4;
            case "5": case "may": return 5;
            case "6": case "jun": case "june": return 6;
            case "7": case "jul": case "july": return 7;
            case "8": case "aug": case "august": return 8;
            case "9": case "sep": case "sept": case "september": return 9;
            case "10": case "oct": case "october": return 10;
            case "11": case "nov": case "november": return 11;
            case "12": case "dec": case "december": return 12;
            default: return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }
}
            
            
            