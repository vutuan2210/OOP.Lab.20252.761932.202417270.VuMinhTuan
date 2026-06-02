package hust.soict.dsai.garbage;

import java.util.Random;

/**
 * ConcatenationInLoops demonstrates the performance difference between
 * String concatenation using + operator vs StringBuilder vs StringBuffer
 * 
 * Key Insights:
 * - String concatenation with + creates a new String object each time (O(n²) time complexity)
 * - StringBuilder and StringBuffer are optimized for multiple concatenations
 * - StringBuilder is faster than StringBuffer (no synchronization overhead)
 */
public class ConcatenationInLoops {
    public static void main(String[] args) {
        System.out.println("=== String Concatenation Performance Comparison ===\n");
        
        // Test 1: String concatenation with + operator
        System.out.println("Test 1: Using + operator with String");
        Random r = new Random(123);
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 65536; i++) {
            s += r.nextInt(2);
        }
        long stringTime = System.currentTimeMillis() - start;
        System.out.println("Time: " + stringTime + " ms");
        System.out.println("Result length: " + s.length() + " characters\n");

        // Test 2: StringBuffer (thread-safe, synchronized)
        System.out.println("Test 2: Using StringBuffer");
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 65536; i++) {
            sbf.append(r.nextInt(2));
        }
        s = sbf.toString();
        long stringBufferTime = System.currentTimeMillis() - start;
        System.out.println("Time: " + stringBufferTime + " ms");
        System.out.println("Result length: " + s.length() + " characters\n");

        // Test 3: StringBuilder (not thread-safe, faster)
        System.out.println("Test 3: Using StringBuilder");
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 65536; i++) {
            sb.append(r.nextInt(2));
        }
        s = sb.toString();
        long stringBuilderTime = System.currentTimeMillis() - start;
        System.out.println("Time: " + stringBuilderTime + " ms");
        System.out.println("Result length: " + s.length() + " characters\n");

        // Performance analysis
        System.out.println("=== Performance Analysis ===");
        System.out.println("String (+) vs StringBuilder: " + (stringTime / (double)stringBuilderTime) + "x slower");
        System.out.println("String (+) vs StringBuffer: " + (stringTime / (double)stringBufferTime) + "x slower");
        System.out.println("StringBuffer vs StringBuilder: " + (stringBufferTime / (double)stringBuilderTime) + "x slower");
        
        System.out.println("\n=== Conclusion ===");
        System.out.println("StringBuilder is the fastest for single-threaded operations.");
        System.out.println("StringBuffer should be used when thread-safety is required.");
        System.out.println("Avoid using + operator for multiple concatenations in loops!");
    }
}
