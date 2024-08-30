package jeff.storage;

import jeff.exception.JeffException;
import jeff.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a connector to take tasks from and write tasks to the task text file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructor for the Storage Class.
     * Stores the file path string as a Path object.
     *
     * @param filePath Task file path string.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Returns a scanner that contains the tasks from the task list text file.
     *
     * @return scanner that contain the tasks.
     * @throws JeffException if something went wrong when getting the file.
     */
    public Scanner load() throws JeffException {
        try {
            // Check if the parent directory exists
            Path directoryPath = this.filePath.getParent();
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Check if the task data file exists
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }

            return new Scanner(this.filePath);
        } catch (IOException e) {
            throw new JeffException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Writes the tasks given to the task list text file.
     *
     * @param taskList List that contains the tasks.
     * @throws JeffException if something went wrong when getting the file.
     */
    public void writeTaskList(TaskList taskList) throws JeffException {
        try {
            // Map the tasks in the task list into their file strings
            List<String> fileStringList = taskList.toFileStrings();

            // Write the strings into the file
            Files.write(filePath, fileStringList);
        } catch (IOException e) {
            throw new JeffException("Something went wrong: " + e.getMessage());
        }
    }
}
