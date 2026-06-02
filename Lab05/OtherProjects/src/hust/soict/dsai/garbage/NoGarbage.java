package hust.soict.dsai.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * NoGarbage provides an efficient solution to reading a file using StringBuffer.
 * 
 * Solution Analysis:
 * - StringBuffer reuses internal buffer and only expands when needed
 * - Only creates ONE final String object with toString()
 * - For a file with N bytes, creates just 1 String object (vs N in GarbageCreator)
 * - Memory allocation is O(N) and GC time is minimal
 * - Result: MUCH FASTER performance, even for large files
 * 
 * Performance Improvement:
 * - Typically 100x to 1000x faster than String concatenation
 * - Memory usage is significantly lower
 * - No excessive garbage collection pauses
 */
public class NoGarbage {
    public static void main(String[] args) {
        // Configuration - adjust filename as needed
        String filename = args.length > 0 ? args[0] : "test.txt";
        
        System.out.println("=== NoGarbage - Efficient StringBuffer Solution ===\n");
        System.out.println("Reading file: " + filename);
        System.out.println("Using StringBuffer for efficient concatenation...\n");
        
        try {
            // Read file into byte array
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            System.out.println("File size: " + inputBytes.length + " bytes");
            System.out.println("Starting StringBuffer concatenation...");
            
            long startTime = System.currentTimeMillis();
            
            // EFFICIENT approach: Using StringBuffer
            // Reuses internal buffer, no new String created per byte
            StringBuffer outputString = new StringBuffer();
            for (int i = 0; i < inputBytes.length; i++) {
                outputString.append((char) inputBytes[i]);
                
                // Progress indicator for large files
                if ((i + 1) % 100000 == 0) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    System.out.println("  Processed " + (i + 1) + " bytes in " + elapsed + " ms");
                }
            }
            
            // Convert StringBuffer to String (only here!)
            String result = outputString.toString();
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            
            System.out.println("\n=== Results ===");
            System.out.println("Processing time: " + totalTime + " ms");
            System.out.println("Result string length: " + result.length());
            System.out.println("Time per byte: " + (totalTime / (double) inputBytes.length) + " ms");
            
            // Memory info
            Runtime runtime = Runtime.getRuntime();
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
            System.out.println("Used memory: " + usedMemory + " MB");
            
            System.out.println("\n=== Analysis ===");
            System.out.println("Number of String objects created: 1 (optimal!)");
            System.out.println("Minimal garbage created");
            System.out.println("Minimal GC pressure");
            System.out.println("Much better performance!");
            
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.err.println("Usage: java hust.soict.dsai.garbage.NoGarbage [filename]");
            System.err.println("Example: java hust.soict.dsai.garbage.NoGarbage test.txt");
        }
    }
}
