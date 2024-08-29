package gavinchatbot.util;

import gavinchatbot.task.Deadline;
import gavinchatbot.task.Event;
import gavinchatbot.task.Task;
import gavinchatbot.task.ToDos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of tasks in the file system.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = null;
            switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new IllegalStateException("Unexpected Value: " + type);
            }
            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}
