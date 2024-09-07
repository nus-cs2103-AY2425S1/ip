package arsenbot.storage;

import arsenbot.task.Deadline;
import arsenbot.task.Event;
import arsenbot.task.Task;
import arsenbot.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles loading and saving tasks from/to a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the file path to load and save tasks from/to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     * If the file does not exist, it returns an empty list of tasks.
     *
     * @return a list of tasks loaded from the file
     * @throws IOException if an I/O error occurs during file reading
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // No tasks to load
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            Task task = switch (parts[0]) {
                case "T" -> new Todo(parts[2]);
                case "D" -> new Deadline(parts[2], parts[3]);
                case "E" -> new Event(parts[2], parts[3], parts[4]);
                default -> null;
            };
            if (task != null && parts[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file at the specified file path.
     * If the directory does not exist, it creates the directory.
     *
     * @param tasks the list of tasks to save to the file
     * @throws IOException if an I/O error occurs during file writing
     */
    public void save(List<Task> tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}
