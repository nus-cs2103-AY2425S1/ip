package hana;

import hana.task.Task;
import hana.task.ToDo;
import hana.task.Deadline;
import hana.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private String filePath;

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
    public List<Task> load() throws IOException {
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
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + d.description + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + e.description + " | " + e.from + " | " + e.to;
        }
        return "";
    }
}
