package bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private String filePath = "data";

    public FileManager(String pathname) {
        if (pathname == null || pathname.isEmpty()) {
            System.out.println("Pathname must not be null or empty");
        }

        File file = new File(pathname);
        if (!file.exists()) {
            System.out.println("File or directory does not exist. Creating new file.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create the file: " + pathname, e);
            }
        }

        this.filePath = pathname;
    }



    /**
     * Reads the content of the file located at filePath.
     * If the file does not exist, it creates a new file.
     */

    public void readFile() {
        assert this.filePath != null && !this.filePath.isEmpty() : "File path must not be null or empty";

        try {
            File file = new File("data");
            if (!file.exists()) {
                file.createNewFile();
            }


            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Writes the specified text to the file. This method appends the text to the file,
     * ensuring that existing content is not overwritten. Every item added will end with a line break (to a new line)
     * File to write to will always exist as will call readFile method first always on boot up which would automatically
     * Create a new file if it doesn't exist
     * @param textToAdd The text to be added to the file.
     */
    public void writeFile(String textToAdd) {
        assert textToAdd != null : "Text to add must not be null";

        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(textToAdd + "\n");
            fw.close();
        } catch (IOException exception) {
            System.out.println("IOException encountered when writing to file!");
        }

    }

    /**
     * Deletes a specific line from a text file based on the line number.
     * The file is overwritten after the deletion.
     *
     * @param lineNumber The line number to delete (1-based index).
     * @throws IOException if an I/O error occurs.
     */
    public void deleteLine(int lineNumber) throws IOException {
        File inputFile = new File(filePath);
        // Store the file content
        StringBuilder content = new StringBuilder();
        int currentLine = 1;

        // Read the file content and skip the line to be deleted
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (currentLine != lineNumber) {
                    content.append(line).append("\n");
                }
                currentLine++;
            }
        }

        // Overwrite the file with the new content
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(content.toString());
        }
    }

    /**
     * Marks the task at the given line number as done by adding '[X]' in place of '[ ]'.
     *
     * @param lineNumber The 1-based index of the line to mark as done.
     */
    public void markTaskAsDone(int lineNumber)  {
        File inputFile = new File(filePath);
        StringBuilder content = new StringBuilder();  // Store the file content
        int currentLine = 1;

        // Read the file content and modify the line at the given lineNumber
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (currentLine == lineNumber) {
                    // Modify the line to mark the task as done
                    line = line.replace("[ ]", "[X]");
                }

                // Append the (possibly modified) line back to the content
                content.append(line).append("\n");
                currentLine++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Overwrite the file with the modified content
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager("data");
        fileManager.readFile();
    }


}
