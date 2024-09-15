package bob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * Handles the loading and saving of tasks from/to a file.
 * The Storage class provides methods for reading tasks from a file and saving tasks to a file.
 */
public class Storage {
    /** File path to where saved tasks are stored */
    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param fp The path to the file where tasks are stored.
     */
    public Storage(String fp) {
        filePath = fp;
    }

    /**
     * Ensures that the directory for the file path exists.
     * If the directory does not exist, create it.
     *
     * @throws BobException If the directory cannot be created.
     */
    private static void ensureDataDirectoryExists() throws BobException {
        File dataDirectory = new File(filePath).getParentFile();
        if (dataDirectory != null && !dataDirectory.exists()) {
            if (!dataDirectory.mkdir()) {
                throw new BobException("Failed to create directory: " + dataDirectory.getAbsolutePath());
            }
        }
    }

    /**
     * Loads the list of saved tasks.
     *
     * @return List of Task objects loaded from the file.
     * @throws BobException If file is not found.
     */
    public List<Task> load() throws BobException {
        assert filePath != null : "filePath should not be null";
        File file = new File(filePath);
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            // Load each task
            while (s.hasNext()) {
                String taskLine = s.nextLine();
                Task task = Parser.parseTask(taskLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new BobException("No saved tasks found.");
        }
        if (taskList.isEmpty()) {
            throw new BobException("No saved tasks found.");
        }
        return taskList;
    }

    /**
     * Saves the list of tasks.
     *
     * @param taskList List containing the tasks to be saved.
     * @throws BobException If an error occurs while saving the tasks.
     */
    public void saveTasks(TaskList taskList) throws BobException {
        assert taskList != null : "Task List should not be null";
        ensureDataDirectoryExists();
        try {
            FileWriter fw = new FileWriter(filePath);

            // Save each task
            for (Task task : taskList.getTasks()) {
                fw.write(task.getTaskLine() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("Oh no! An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
