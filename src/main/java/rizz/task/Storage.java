package rizz.task;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import rizz.source.TaskList;

/**
 * Handles saving and loading tasks from a file.
 * The Storage class is responsible for reading and writing task data to a file
 * and converting between Task objects and their string representations.
 */
public class Storage {
    private static final DateTimeFormatter readDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter readTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    private final Path dataFilePath;
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param dataFilePath The path to the file where tasks will be saved and loaded.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = Path.of(dataFilePath);
        this.taskList = new ArrayList<>();
    }

    /**
     * Saves the tasks from the TaskList to the file specified by dataFilePath.
     * Each task is converted to a string and written to the file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        if (!Files.exists(dataFilePath)) {
            Files.createDirectories(dataFilePath.getParent());
            Files.createFile(dataFilePath);
        }
        String content = String.join("\n", taskList.export());
        Files.writeString(dataFilePath, content.toString());
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs when reading from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        if (Files.exists(dataFilePath)) {
            Files.readAllLines(dataFilePath).stream()
                    .map(Storage::decodeTasks)
                    .filter(task -> task != null)
                    .forEach(taskList::add);
        }
        return taskList;
    }

    /**
     * Decodes a line from the file and converts it to a Task object.
     * This method identifies the type of task (ToD0, Deadline, Event) based on the line's contents
     * and parses the task's details accordingly.
     *
     * @param line The line from the file representing a task.
     * @return A Task object corresponding to the data in the line, or null if the task type is unknown.
     */
    public static Task decodeTasks(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String text = parts[2];

        switch (taskType) {
        case "T":
            return new ToDo(text, isDone);
        case "D":
            LocalDateTime time = LocalDateTime.parse(parts[3], readDateTimeFormatter);
            return new Deadline(text, time, isDone);
        case "E":
            String[] timeParts = parts[3].split(" ");
            LocalDateTime from = LocalDateTime.parse(timeParts[0] + " "
                    + timeParts[1], readDateTimeFormatter); // Combine the date and start time
            LocalTime to = LocalTime.parse(timeParts[2], readTimeFormatter);
            return new Event(text, from, to, isDone);
        default:
            System.out.println("Unknown task type: " + taskType);
            return null;
        }
    }
}
