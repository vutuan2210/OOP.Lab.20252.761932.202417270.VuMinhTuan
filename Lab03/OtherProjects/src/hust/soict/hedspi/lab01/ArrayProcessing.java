package hust.soict.hedspi.lab01;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayProcessing {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of elements: ");
            int n = scanner.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.print("Element " + i + ": ");
                arr[i] = scanner.nextInt();
            }

            System.out.println("Original array: " + Arrays.toString(arr));

            Arrays.sort(arr);
            System.out.println("Sorted array: " + Arrays.toString(arr));

            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i];
            }

            double average = (double) sum / n;

            System.out.println("Sum = " + sum);
            System.out.println("Average = " + average);
        }
    }
}
