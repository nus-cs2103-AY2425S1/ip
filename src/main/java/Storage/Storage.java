package Storage;

import Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.io.File;
import java.nio.file.Files;
public class Storage {
    File file;
    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    public void writeToHardDisk(List<Task> tasks) {
        try {
            // Clear the file contents by overwriting it
            try (FileWriter writer = new FileWriter(file, false)) {
                // Opening in overwrite mode clears existing content
            }
            // Write new content to the file
            try (FileWriter writer = new FileWriter(file, true)) {
                for (Task line : tasks) {
                    writer.write(line.toString());
                    writer.write(System.lineSeparator()); // Ensure each line appears on a new line
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
    public List<String> readFromHardDisk() throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
