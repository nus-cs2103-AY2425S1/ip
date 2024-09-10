package Storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import Tasks.Task;

/**
 * The Storage class handles reading and writing tasks to and from a file on the hard disk.
 *
 * @author jordanchan
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object that manages a file specified by the given file path.
     *
     * @param filePath The path to the file where tasks will be stored and retrieved from.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Writes a list of tasks to the hard disk. This method clears the existing contents
     * of the file before writing the new tasks.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void writeToHardDisk(List<Task> tasks) {
        try {
            // Clear the file contents by overwriting it
            try (FileWriter writer = new FileWriter(file, false)) {
                // Opening in overwrite mode clears existing content
            }
            // Write new content to the file
            try (FileWriter writer = new FileWriter(file, true)) {
                assert file.exists() && file.length() == 0 : "file still has leftover content that needs to be cleared";
                for (Task line : tasks) {
                    writer.write(line.toString());
                    writer.write(System.lineSeparator()); // Ensure each line appears on a new line
                }
                writer.flush();
                assert countLines(file) == tasks.size() : "hard drive should have same number of tasks as list";
            }
        } catch (IOException e) {
            System.out.println("no hard drive present!");
        }
    }

    /**
     * Reads all lines from the file on the hard disk and returns them as a list of strings.
     *
     * @return A list of strings, where each string represents a line from the file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public List<String> readFromHardDisk() throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static int countLines(File file) throws IOException {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }
}
