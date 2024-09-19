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
    private String filePath;

    /**
     * Creates a Storage object to manage the file located at the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as a list of Task objects.
     *
     * @return A List of Task objects loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null && !line.isEmpty()){
            Task task = Parser.parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves the current TaskList to the specified file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks.getAllTasks()) {
            writer.write(Parser.taskToString(task));
            writer.newLine();
        }
        writer.close();
    }
}
