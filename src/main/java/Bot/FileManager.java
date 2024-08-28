package Bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private String FilePath = "src/main/java/data";

    public FileManager(String pathname) {
        this.FilePath = pathname;
    }

    public void readFile() {
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

    // File to write to will always exist as will call readFile method first always on boot up which would automatically
    // Create a new file if it doesn't exist
    public void writeFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(this.FilePath, true);
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
