package hust.soict.hedspi.lab01;

import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int month = -1;
            int year = -1;

            while (month == -1) {
                System.out.print("Enter month: ");
                String input = scanner.nextLine().trim().toLowerCase();

                month = switch (input) {
                    case "january", "jan", "jan.", "1" -> 1;
                    case "february", "feb", "feb.", "2" -> 2;
                    case "march", "mar", "mar.", "3" -> 3;
                    case "april", "apr", "apr.", "4" -> 4;
                    case "may", "5" -> 5;
                    case "june", "jun", "jun.", "6" -> 6;
                    case "july", "jul", "jul.", "7" -> 7;
                    case "august", "aug", "aug.", "8" -> 8;
                    case "september", "sep", "sep.", "9" -> 9;
                    case "october", "oct", "oct.", "10" -> 10;
                    case "november", "nov", "nov.", "11" -> 11;
                    case "december", "dec", "dec.", "12" -> 12;
                    default -> {
                        System.out.println("Invalid month, please enter again!");
                        yield -1;
                    }
                };
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

            int days = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                case 2 -> isLeap ? 29 : 28;
                default -> 0;
            };

            System.out.println("Number of days: " + days);
        }
    }
}
