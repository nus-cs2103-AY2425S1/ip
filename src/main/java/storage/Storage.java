package storage;

import task.Task;
import task.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    /**
     * Creates a new TaskStorage object.
     *
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return List of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create the file if it does not exist
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        // Read tasks from the file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            tasks.add(Task.deserialize(line));
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void save(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).serialize());
            writer.newLine();
        }
        writer.close();
    }
}
