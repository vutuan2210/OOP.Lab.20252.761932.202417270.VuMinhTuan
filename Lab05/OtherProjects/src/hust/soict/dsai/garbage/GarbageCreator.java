package hust.soict.dsai.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * GarbageCreator demonstrates the performance problem of reading a file
 * using String concatenation with + operator.
 * 
 * This class creates excessive garbage objects (one new String per byte read).
 * WARNING: This may consume significant memory and time for large files.
 * 
 * Problem Analysis:
 * - Each s += (char)b operation creates a new String object
 * - Old String objects become garbage
 * - For a file with N bytes, creates N new String objects
 * - Memory allocation and garbage collection becomes bottleneck
 * - Result: VERY SLOW performance (sometimes hangs with large files)
 */
public class GarbageCreator {
    public static void main(String[] args) {
        // Configuration - adjust filename and file size as needed
        String filename = args.length > 0 ? args[0] : "test.txt";
        
        System.out.println("=== GarbageCreator - Poor String Concatenation Performance ===\n");
        System.out.println("Reading file: " + filename);
        System.out.println("WARNING: This may be VERY SLOW and consume significant memory!\n");
        
        try {
            // Read file into byte array
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            System.out.println("File size: " + inputBytes.length + " bytes");
            System.out.println("Starting string concatenation with + operator...");
            
            long startTime = System.currentTimeMillis();
            
            // INEFFICIENT approach: String concatenation in loop
            // This creates ONE NEW STRING for EVERY SINGLE BYTE!
            String outputString = "";
            for (int i = 0; i < inputBytes.length; i++) {
                outputString += (char) inputBytes[i];
                
                // Progress indicator for large files
                if ((i + 1) % 10000 == 0) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    System.out.println("  Processed " + (i + 1) + " bytes in " + elapsed + " ms");
                }
            }
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            
            System.out.println("\n=== Results ===");
            System.out.println("Processing time: " + totalTime + " ms");
            System.out.println("Result string length: " + outputString.length());
            System.out.println("Time per byte: " + (totalTime / (double) inputBytes.length) + " ms");
            
            // Memory info
            Runtime runtime = Runtime.getRuntime();
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
            System.out.println("Used memory: " + usedMemory + " MB");
            
            System.out.println("\n=== Analysis ===");
            System.out.println("Number of String objects created: ~" + inputBytes.length);
            System.out.println("This creates massive garbage for GC to clean up!");
            System.out.println("For a 1MB file: ~1,000,000 temporary String objects!");
            
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.err.println("Usage: java hust.soict.dsai.garbage.GarbageCreator [filename]");
            System.err.println("Example: java hust.soict.dsai.garbage.GarbageCreator test.txt");
        }
    }
}
