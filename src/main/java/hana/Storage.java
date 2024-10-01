package hana;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hana.task.Deadline;
import hana.task.Event;
import hana.task.Task;
import hana.task.ToDo;

/**
 * Handles loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes the Storage with the specified file path.
     *
     * @param filePath The path of the file to save/load tasks.
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
    public List<Task> load() throws IOException, HanaException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist
            file.createNewFile(); // Create file if it doesn't exist
        } else {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    ToDo todo = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new HanaException("No such task");
                }
            }
        }

        return tasks;
    }

    /**
     * Saves the tasks to the specified file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            writer.write(taskToString(task) + System.lineSeparator());
        }

        writer.close();
    }

    /**
     * Converts a Task object to a string format suitable for saving to the file.
     *
     * @param task The Task to convert.
     * @return A string representing the Task.
     */
    private String taskToString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.getIsDone() ? "1" : "0")
                    + " | " + task.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + (task.getIsDone() ? "1" : "0")
                    + " | " + deadline.getDescription()
                    + " | " + deadline.getBy();
        } else if (task instanceof Event event) {
            return "E | " + (task.getIsDone() ? "1" : "0")
                    + " | " + event.getDescription()
                    + " | " + event.getFrom()
                    + " | " + event.getTo();
        }
        return "";
    }
}
