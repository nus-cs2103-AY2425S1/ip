import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class CombineJavaToMarkdown {

    public static void main(String[] args) {
        // Define the directory and output file name
        File directory = new File("/Users/eliot/CS2103T");
        String outputFileName = "combined_files.md";

        // Call the method to combine Java files into a Markdown file
        combineJavaToMarkdown(directory, outputFileName);
    }

    public static void combineJavaToMarkdown(File directory, String outputFileName) {
        if (!directory.isDirectory()) {
            System.err.println("Provided path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".java")) {
                        processFileToMarkdown(file, writer);
                    } else {
                        System.out.println("Skipping non-Java file: " + file.getName());
                    }
                }
                System.out.println("Combined Java files into " + outputFileName);
            } catch (IOException e) {
                System.err.println("Error writing to the output file.");
                e.printStackTrace();
            }
        } else {
            System.err.println("Could not list files in the directory.");
        }
    }

    private static void processFileToMarkdown(File javaFile, BufferedWriter writer) {
        String fileName = javaFile.getName();

        try (BufferedReader reader = new BufferedReader(new FileReader(javaFile))) {
            writer.write("## " + fileName + "\n\n");
            writer.write("```java\n");  // Start of code block with Java syntax highlighting
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.write("```\n\n");  // End of code block
            System.out.println("Processed file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error processing file: " + javaFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}