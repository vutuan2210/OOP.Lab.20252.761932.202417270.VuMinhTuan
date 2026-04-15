package hust.soict.hedspi.garbage;

/**
 * Solution to the garbage creation problem: use StringBuffer instead of String concatenation
 * StringBuffer reuses the same character array internally, avoiding creation of
 * thousands of temporary String objects that would become garbage.
 */
public class NoGarbage {
    
    public static void main(String[] args) {
        System.out.println("=== No Garbage: StringBuffer Solution ===\n");
        
        // Get initial memory information
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Initial memory usage: " + formatBytes(initialMemory));
        
        // Create large string using StringBuffer (no garbage!)
        System.out.println("\nCreating string using StringBuffer (efficient, minimal garbage)...");
        long startTime = System.currentTimeMillis();
        String largeString = createLargeStringWithStringBuffer(100000);
        long endTime = System.currentTimeMillis();
        
        // Get final memory information
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Final memory usage: " + formatBytes(finalMemory));
        System.out.println("Memory increase: " + formatBytes(finalMemory - initialMemory));
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Result length: " + largeString.length());
        
        System.out.println("\n=== Solution Benefits ===");
        System.out.println("- StringBuffer reuses internal character array");
        System.out.println("- No temporary String objects created");
        System.out.println("- Much faster for large strings");
        System.out.println("- Minimal garbage created");
        System.out.println("- Lower memory pressure on GC");
        
        // Compare with GarbageCreator approach
        System.out.println("\n=== Comparison ===");
        System.out.println("GarbageCreator (String +): Creates ~100,000 temporary String objects");
        System.out.println("NoGarbage (StringBuffer): Creates only 1 String object (final result)");
        System.out.println("Garbage objects avoided: ~99,999 objects");
    }
    
    /**
     * Create a large string using StringBuffer
     * StringBuffer maintains a character array internally and grows as needed.
     * Each append() modifies the existing buffer without creating new String objects.
     * 
     * @param length The desired length of the final string
     * @return A string of length 'length'
     */
    private static String createLargeStringWithStringBuffer(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("*"); // Appends to same buffer, no new String objects
        }
        return sb.toString(); // Only creates ONE final String
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

