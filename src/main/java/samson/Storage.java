package samson;

import samson.task.Deadline;
import samson.task.Event;
import samson.task.Task;
import samson.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code> Storage </code> class is responsible for reading from and writing tasks to a file.
 * It manages the persistence of tasks by saving them to a specified file path and loading
 * them back into the application when needed.
 */
public class Storage {
    private String filePath;
    private final File file;

    /**
     * Constructs a <code> Storage </code> object and initializes the file at the specified file path.
     * If the file or its parent directories do not exist, they are created.
     *
     * @param filePath The file path where tasks will be stored.
     * @throws IOException If an I/O error occurs during file creation.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from the file specified by the file path into a list of <code> Task </code> objects.
     * Each line in the file represents a task, which is parsed and converted into a specific
     * <code> Task </code> subclass (e.g., <code> ToDo </code>, <code> Deadline </code>, <code> Event </code>).
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws SamException If the file contains invalid data.
     */
    public List<Task> loadTaskFromFile() throws IOException, SamException {
        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            Task task = parseTaskFromLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Parses a line of text from the task file and converts it into a <code> Task </code> object.
     *
     * @param line The line from the file representing the task.
     * @return The corresponding <code> Task </code> object or <code> null </code> if the data is invalid.
     * @throws SamException If the line contains invalid data.
     */
    private Task parseTaskFromLine(String line) throws SamException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Task todo = new ToDo(description);
                if (isDone) {
                    todo.complete();
                }
                return todo;
            case "D":
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.complete();
                }
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                if (isDone) {
                    event.complete();
                }
                return event;
            default:
                System.out.println("Invalid data detected: " + line);
                return null;
        }
    }


    /**
     * Appends a new task to the file.
     *
     * @param task The task to be added to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void addTaskToFile(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task.toStorageString() + System.lineSeparator());
        fileWriter.close();
    }

    /**
     * Saves the entire list of tasks to the file, overwriting any existing content.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasksToFile(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.toStorageString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
