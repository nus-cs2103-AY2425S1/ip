package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    private final Path filePath;

    /**
     * Constructor for the storage class.
     * 
     * @param filePath The path of the file to store the tasks.
     */
    public Storage(String filePath) {
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

    public void addToStorage(String data) {
        try {
            Files.write(this.filePath, (data + Utility.NEW_LINE).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error saving to storage!");
        }
    }

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
