package Bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private String filePath = "src/main/java/data";

    public FileManager(String pathname) {
        assert pathname != null && !pathname.isEmpty() : "Pathname must not be null or empty";
        this.filePath = pathname;
    }


    /**
     * Reads the content of the file located at filePath.
     * If the file does not exist, it creates a new file.
     */

    public void readFile() {
        assert this.filePath != null && !this.filePath.isEmpty() : "File path must not be null or empty";

        try {
            File file = new File("src/main/java/data");
            System.out.println("Absolute path: " + file.getAbsolutePath());
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
     *
     * @param textToAdd The text to be added to the file.
     */
    // File to write to will always exist as will call readFile method first always on boot up which would automatically
    // Create a new file if it doesn't exist
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

    public static void main(String[] args) {
        FileManager fileManager = new FileManager("src/main/java/data");
            fileManager.readFile();
    }


}
