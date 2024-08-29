import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
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

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] taskData = scanner.nextLine().split(" \\| ");
            Task task = parseTaskData(taskData);
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    /**
     * Parses task data from a string array into a Task object.
     *
     * @param taskData The data to parse, split by " | ".
     * @return The corresponding Task object.
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
            LocalDateTime deadlineDate = LocalDateTime.parse(taskData[3], SAVE_FORMAT);
            task = new Deadline(description, deadlineDate);
            break;
        case "E":
            LocalDateTime eventFrom = LocalDateTime.parse(taskData[3], SAVE_FORMAT);
            LocalDateTime eventTo = LocalDateTime.parse(taskData[4], SAVE_FORMAT);
            task = new Event(description, eventFrom, eventTo);
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
     * Saves a list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + "\n");
        }
        writer.close();
    }
}
