package hust.soict.dsai.garbage;

/**
 * Demonstrates garbage creation and memory degradation when using String concatenation
 * instead of StringBuffer. This class creates massive amounts of temporary String objects
 * that become garbage and must be collected, causing memory pressure and performance degradation.
 */
public class GarbageCreator {
    
    public static void main(String[] args) {
        System.out.println("=== Garbage Creator: String Concatenation Problem ===\n");
        
        // Get initial memory information
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Initial memory usage: " + formatBytes(initialMemory));
        
        // Create garbage with String concatenation
        System.out.println("\nCreating string using + concatenation (creates massive garbage)...");
        long startTime = System.currentTimeMillis();
        String largeString = createLargeStringWithConcatenation(100000);
        long endTime = System.currentTimeMillis();
        
        // Get final memory information
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Final memory usage: " + formatBytes(finalMemory));
        System.out.println("Memory increase: " + formatBytes(finalMemory - initialMemory));
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Result length: " + largeString.length());
        
        System.out.println("\n=== Analysis ===");
        System.out.println("Using String + in a loop creates a new String object");
        System.out.println("with each iteration. All intermediate strings become garbage.");
        System.out.println("With 100,000 iterations, that's approximately 100,000 String objects!");
        System.out.println("These all need to be garbage collected, causing memory & performance issues.");
    }
    
    /**
     * Creates a large string using String concatenation with + operator
     * This approach creates MANY temporary String objects that become garbage
     * 
     * @param length The desired length of the final string
     * @return A string of length 'length'
     */
    private static String createLargeStringWithConcatenation(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result = result + "*"; // Each iteration creates new String object
        }
        return result;
    }
    
    /**
     * Formats bytes into human-readable format (KB, MB, etc)
     */
    private static String formatBytes(long bytes) {
        final long KILOBYTE = 1024;
        final long MEGABYTE = 1024 * KILOBYTE;
        final long GIGABYTE = 1024 * MEGABYTE;
        
        if (bytes >= GIGABYTE) {
            return String.format("%.2f GB", bytes / (double) GIGABYTE);
        } else if (bytes >= MEGABYTE) {
            return String.format("%.2f MB", bytes / (double) MEGABYTE);
        } else if (bytes >= KILOBYTE) {
            return String.format("%.2f KB", bytes / (double) KILOBYTE);
        } else {
            return bytes + " B";
        }
    }
}
