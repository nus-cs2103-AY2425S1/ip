package nayana;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import nayana.task.Task;

/**
 * Handles loading tasks from and writing tasks to a file.
 */
public class Storage {

    private String filePath;
    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Loads the file, creating it if it does not exist.
     *
     * @return The Storage object for chaining.
     * @throws NayanaException If an error occurs while creating the file.
     */
    public ArrayList<Task> load() throws NayanaException {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new NayanaException("Unable to create new file.");
                }
            }
        } catch (IOException e) {
            throw new NayanaException("Error loading file: " + e.getMessage());
        }

        return new ArrayList<Task>();
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param tasks The list of tasks to write to the file.
     * @throws NayanaException If an error occurs while writing to the file.
     */
    public void writeToFile(ArrayList<Task> tasks) throws NayanaException {
        try (FileWriter fw = new FileWriter(filePath)) {
            StringBuilder textToAdd = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String taskString = (i + 1) + ". " + task.getType() + task.getStatus() + " " + task.getDescription()
                      + " " + task.getDates();
                textToAdd.append(taskString).append("\n");
            }
            fw.write(textToAdd.toString());
        } catch (IOException e) {
            throw new NayanaException("Error writing to file: " + e.getMessage());
        }
    }
}
