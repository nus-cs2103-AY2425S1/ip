package jeff.storage;

import jeff.exception.JeffException;
import jeff.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

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
