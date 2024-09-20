package sammy;

import sammy.task.Task;
import sammy.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {

    private static final String ERROR_CREATING_FILE = "Error creating file: ";

    private String filePath;

    /**
     * Creates a Storage object to manage the file located at the specified file path.
     *
     * @param fileName The name of the file to store tasks (e.g., "Sammy.txt").
     */
    public Storage(String fileName) {

        this.filePath = buildFilePath(fileName);
        createFileIfNotExists();

    }

    /**
     * Constructs the full file path by combining the current working directory with the provided file name.
     *
     * @param fileName The file name to be appended to the working directory path.
     * @return The complete file path.
     */
    private String buildFilePath(String fileName) {
        return System.getProperty("user.dir") + File.separator + fileName;
    }

    /**
     * Creates a new file in the current working directory if it does not exist.
     */
    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();  // Create the new file in the working directory
            } catch (IOException e) {
                System.out.println(ERROR_CREATING_FILE + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the specified file and returns them as a list of Task objects.
     *
     * @return A List of Task objects loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public List<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();  // Return an empty task list if the file doesn't exist
        }

        return loadTasksFromFile();
    }

    /**
     * Loads tasks from the file into a list of Task objects.
     *
     * @return A List of Task objects loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    private List<Task> loadTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
                line = reader.readLine();
            }
        }
        return tasks;
    }

    /**
     * Saves the current TaskList to the specified file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(Parser.taskToString(task));
                writer.newLine();
            }
        }
    }
}