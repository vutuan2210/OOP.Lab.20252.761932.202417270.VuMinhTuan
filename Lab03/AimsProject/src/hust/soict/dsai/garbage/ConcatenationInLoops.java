package hust.soict.dsai.garbage;

/**
 * Demonstrates the performance difference between string concatenation approaches
 * in loops: String concatenation (+), StringBuffer, and StringBuilder
 */
public class ConcatenationInLoops {
    
    public static void main(String[] args) {
        // Warm up to reduce JVM initialization impact
        System.out.println("Warming up JVM...");
        warmUp();
        
        System.out.println("\n=== String Concatenation Performance Test ===\n");
        
        // Test parameters
        int iterations = 50000;
        
        // Test 1: Using String + operator (slowest - creates new String each iteration)
        System.out.println("Test 1: String concatenation with + operator");
        long startTime = System.currentTimeMillis();
        String result1 = stringConcatenation(iterations);
        long endTime = System.currentTimeMillis();
        long time1 = endTime - startTime;
        System.out.println("Time: " + time1 + " ms");
        System.out.println("Result length: " + result1.length() + "\n");
        
        // Test 2: Using StringBuffer (fast, thread-safe)
        System.out.println("Test 2: String concatenation with StringBuffer");
        startTime = System.currentTimeMillis();
        String result2 = stringBufferConcatenation(iterations);
        endTime = System.currentTimeMillis();
        long time2 = endTime - startTime;
        System.out.println("Time: " + time2 + " ms");
        System.out.println("Result length: " + result2.length() + "\n");
        
        // Test 3: Using StringBuilder (fastest, not thread-safe)
        System.out.println("Test 3: String concatenation with StringBuilder");
        startTime = System.currentTimeMillis();
        String result3 = stringBuilderConcatenation(iterations);
        endTime = System.currentTimeMillis();
        long time3 = endTime - startTime;
        System.out.println("Time: " + time3 + " ms");
        System.out.println("Result length: " + result3.length() + "\n");
        
        // Performance analysis
        System.out.println("=== Performance Analysis ===");
        System.out.println("String +: " + time1 + " ms");
        System.out.println("StringBuffer: " + time2 + " ms");
        System.out.println("StringBuilder: " + time3 + " ms");
        System.out.println("\nSpeedup (String + vs StringBuilder): " + (time1 / (double) time3) + "x");
        System.out.println("Speedup (StringBuffer vs StringBuilder): " + (time2 / (double) time3) + "x");
    }
    
    /**
     * Concatenate using String + operator (creates new String each iteration)
     * This approach creates many intermediate String objects that become garbage
     */
    private static String stringConcatenation(int iterations) {
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str = str + "x"; // Creates new String each iteration
        }
        return str;
    }
    
    /**
     * Concatenate using StringBuffer (thread-safe)
     * StringBuffer is synchronized but more efficient than String +
     */
    private static String stringBufferConcatenation(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("x"); // Appends to existing buffer
        }
        return sb.toString();
    }
    
    /**
     * Concatenate using StringBuilder (not thread-safe, fastest)
     * StringBuilder is the preferred choice for non-threaded code
     */
    private static String stringBuilderConcatenation(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("x"); // Appends to existing buffer
        }
        return sb.toString();
    }
    
    /**
     * Warm up the JVM to reduce startup overhead
     */
    private static void warmUp() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("warmup");
        }
    }
}
