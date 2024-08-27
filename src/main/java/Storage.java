import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toFileString() + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during loading.
     */
    public ArrayList<Task> loadTasks() throws IOException, SecurityException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                Task task = createTaskFromFile(parts);
                tasks.add(task);
            }
        } else {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return tasks;
    }

    /**
     * Creates a Task object from its string representation in the file.
     *
     * @param parts The parts of the string split by " | ".
     * @return The corresponding Task object.
     */
    private Task createTaskFromFile(String[] parts) throws IllegalArgumentException {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                return new Event(description, parts[3], parts[4], isDone);
            default:
                throw new IllegalArgumentException("Invalid task type in file");
        }
    }
}
