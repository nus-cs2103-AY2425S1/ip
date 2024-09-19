package friday.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.Todo;

/**
 * The Storage class handles loading and saving tasks to and from a file.
 * It manages the persistence of tasks between program runs.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, it creates a new empty file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while loading the file.
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
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
            case "T":
                tasks.add(new Todo(description, isDone));
                break;
            case "D":
                tasks.add(new Deadline(description, parts[3], isDone));
                break;
            case "E":
                tasks.add(new Event(description, parts[3], parts[4], isDone));
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
            }
        }
        scanner.close();

        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while saving the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Tasks list should not be null";

        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            writer.write(task.toFileFormat() + "\n");
        }

        writer.close();
    }
}
