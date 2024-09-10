package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Class to interact with files stored on the users computer.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructor for the storage class.
     *
     * @param filePath The path of the file to store the tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";

        Path p = Paths.get(filePath);
        Path parentDir = p.getParent();

        try {
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (IOException e) {
            Utility.prettyPrint("Error creating missing file: " + e.getMessage());
        }
        this.filePath = p;
    }

    /**
     * Method to append data to the file.
     *
     * @param data A string represnting the line to be added.
     */
    public void addToStorage(String data) {
        try {
            Files.write(this.filePath, (data + Utility.NEW_LINE).getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error saving to storage!");
        }
    }

    /**
     * Method to overwrite the file.
     *
     * @param data A string representing the data to overwrite with.
     * @param lineNumber An integer representing the line to be updated.
     */
    public void updateStorage(String data, int lineNumber) {
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, data);
            } else {
                System.err.println("Invalid line number.");
                return;
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Error updating storage!");
        }
    }

    /**
     * Method to delete a line from the file.
     *
     * @param lineNumber An integer representing the line to be deleted.
     */
    public void removeFromStorage(int lineNumber) {
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.remove(lineNumber);
            } else {
                System.err.println("Invalid line number.");
                return;
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Error updating storage!");
        }
    }

    /**
     * Utility method to represent the data in the form of an array.
     *
     * @return An array of strings where each index corresponds to a line in the stored file.
     */
    public String[] toArray() {
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return new String[0];
        }
    }
}
