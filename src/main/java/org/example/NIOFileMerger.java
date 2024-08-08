package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NIOFileMerger {

    public static void main(String[] args) {
        Path inputPath1 = Paths.get("input1.txt");
        Path inputPath2 = Paths.get("input2.txt");
        Path mergedPath = Paths.get("merged.txt");
        Path commonPath = Paths.get("common.txt");

        try {
            // Read integers from input1.txt
            List<String> lines1 = Files.readAllLines(inputPath1);
            Set<Integer> set1 = new HashSet<>();
            for (String line : lines1) {
                try {
                    set1.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid integer in input1.txt: " + line);
                }
            }

            // Read integers from input2.txt
            List<String> lines2 = Files.readAllLines(inputPath2);
            Set<Integer> set2 = new HashSet<>();
            for (String line : lines2) {
                try {
                    set2.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid integer in input2.txt: " + line);
                }
            }

            // Merge contents of both files, maintaining the original order
            List<String> mergedLines = Files.readAllLines(inputPath1);
            mergedLines.addAll(lines2);
            Files.write(mergedPath, mergedLines);
            System.out.println("Merged contents written to merged.txt");

            // Find common integers
            set1.retainAll(set2);  // Set intersection of integers from both files

            // Write common integers to common.txt
            Files.write(commonPath, set1.stream().map(String::valueOf).toList());
            System.out.println("Common integers written to common.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


