package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import exceptions.HardDriveNotFoundException;
import task.Task;

/**
 * The storage class handles reading and writing tasks to and from a file on the hard disk.
 *
 * @author jordanchan
 */
public class Storage {
    private final File file;

    /**
     * Constructs a storage object that manages a file specified by the given file path.
     * code reused but modified from Justin yeo, another student in this course
     *
     * @author naythee169 - reused
     * @param filePath The path to the file where tasks will be stored and retrieved from.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
     * @throws HardDriveNotFoundException If an I/O error occurs reading from the file.
     */
    public List<String> readFromHardDisk() throws HardDriveNotFoundException {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new HardDriveNotFoundException();
        }
    }

    /**
     * this is a helper function for the assertion to count the number of lines in the file
     *
     * @param file the hard drive
     * @return number of lines(or tasks) in the hard drive
     * @throws IOException should never be thrown as there should always be a hard disk
     */
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
