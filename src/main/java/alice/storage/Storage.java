package alice.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alice.task.InvalidTaskException;
import alice.task.Task;

/** Reads and writes task data to a file. */
public class Storage {
    private final String dataDirectoryPath;
    private final String tasksFileName;

    /**
     * Creates a storage class to manage read from/write to disk.
     *
     * @param dataDirectoryPath directory of stored logs
     * @param tasksFileName     this is exactly what it sounds like
     */
    public Storage(String dataDirectoryPath, String tasksFileName) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.tasksFileName = tasksFileName;
    }

    /**
     * Loads stored tasks from disk.
     *
     * @return                      the list of stored tasks
     * @throws InvalidTaskException if there are errors in parsing the stored task
     * @throws IOException          if the stored file cannot be read
     */
    public List<Task> loadTasks() throws IOException, InvalidTaskException {
        File dataDirectory = new File(dataDirectoryPath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        assert dataDirectory.exists();

        List<Task> tasks = new ArrayList<>();
        File tasksFile = new File(String.format("%s/%s", dataDirectoryPath, tasksFileName));
        FileReader input = new FileReader(tasksFile.getAbsoluteFile());
        BufferedReader reader = new BufferedReader(input);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            Task task = Task.fromJsonString(line);
            tasks.add(task);
        }
        reader.close();
        input.close();
        return tasks;
    }

    /**
     * Save tasks to disk to persist data.
     *
     * @throws IOException if the stored file cannot be written to
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File dataDirectory = new File(dataDirectoryPath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        assert dataDirectory.exists();

        File tasksFile = new File(String.format("%s/%s", dataDirectoryPath, tasksFileName));
        // overwrite file
        FileWriter output = new FileWriter(tasksFile.getAbsoluteFile(), false);
        BufferedWriter writer = new BufferedWriter(output);
        for (Task task : tasks) {
            writer.write(task.toJsonString());
            writer.write("\n");
        }
        writer.close();
        output.close();
    }
}
