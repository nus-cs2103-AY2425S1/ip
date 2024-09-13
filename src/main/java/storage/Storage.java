package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import task.Task;
import task.TaskList;

/**
 * Represents a storage for tasks.
 */
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

    /**
     * Archives tasks to a new file.
     *
     * @param tasks List of tasks to archive.
     * @throws IOException If an I/O error occurs.
     */
    public void archive(TaskList tasks) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String archiveFilePath = filePath.replace(".txt", "_" + timestamp + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(archiveFilePath));
        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).serialize());
            writer.newLine();
        }
        writer.close();
    }
}
