package lolo.storage;

import lolo.LoloException;
import lolo.task.Deadline;
import lolo.task.Event;
import lolo.task.Task;
import lolo.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks from/to a file.
 * The tasks are represented in a specific format for persistence.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LoloException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            ensureFileExists(file);

            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                Task task = parseTask(line);
                tasks.add(task);
            }

        } catch (IOException e) {
            throw new LoloException("Error loading tasks from file.");
        }

        return tasks;
    }

    /**
     * Ensures that the file and its directory exist. If not, creates them.
     *
     * @param file The file to be checked/created.
     * @throws IOException If an error occurs while creating the file.
     */
    private void ensureFileExists(File file) throws IOException {
        if (!file.exists()) {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs(); // Create directories if they don't exist
            }
            file.createNewFile(); // Create the file if it doesn't exist
        }
    }

    /**
     * Parses a line from the file and creates the corresponding Task object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Task object.
     * @throws LoloException If the task type is unknown or if the date format is incorrect.
     */
    private Task parseTask(String line) throws LoloException {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            String tags = parts.length > 3 ? parts[parts.length - 1] : "";

            Task task = createTask(type, description, parts);
            handleTaskCompletion(task, isDone);
            handleTags(task, tags);

            return task;
        } catch (Exception e) {
            throw new LoloException("Error parsing task.");
        }
    }

    /**
     * Creates a Task object based on its type.
     *
     * @param type The type of task (e.g., "T", "D", "E").
     * @param description The description of the task.
     * @param parts The parts of the line that contains additional task details.
     * @return The created Task object.
     * @throws LoloException If the task type is unknown or if the date format is incorrect.
     */
    private Task createTask(String type, String description, String[] parts) throws LoloException {
        switch (type) {
            case "T":
                return new ToDo(description);
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                return new Deadline(description, by);
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                return new Event(description, from, to);
            default:
                throw new LoloException("Unknown task type in file.");
        }
    }

    /**
     * Marks the task as done if it has been completed.
     *
     * @param task The task to be marked.
     * @param isDone The completion status of the task.
     */
    private void handleTaskCompletion(Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        }
    }

    /**
     * Adds tags to a task if any are present.
     *
     * @param task The task to which tags will be added.
     * @param tags The tags to be added (comma-separated).
     */
    private void handleTags(Task task, String tags) {
        if (!tags.isEmpty()) {
            for (String tag : tags.split(",")) {
                task.addTag(tag.trim());
            }
        }
    }

    public void save(ArrayList<Task> tasks) throws LoloException {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(task.toDataString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new LoloException("Error saving tasks to file.");
        }
    }
}


