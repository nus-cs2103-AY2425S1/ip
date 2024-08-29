import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from a file.
 * The file path is specified upon instantiation of the Storage object.
 */
public class Storage {

    /** The path to the file where tasks are stored. */
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, a new file is created.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file operations.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            // Create the file if it doesn't exist
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] taskData = scanner.nextLine().split(" \\| ");
                Task task = parseTaskData(taskData);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Parses an array of strings representing task data and creates a Task object.
     *
     * @param taskData An array of strings containing task data.
     * @return A Task object based on the parsed data.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    private Task parseTaskData(String[] taskData) {
        String type = taskData[0];
        boolean isDone = taskData[1].equals("1");
        String description = taskData[2];

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, taskData[3]);
            break;
        case "E":
            task = new Event(description, taskData[3], taskData[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     *
     * @param tasks An ArrayList of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs during file operations.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
        }
    }
}
