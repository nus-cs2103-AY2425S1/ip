package utility;

import task.*;

import java.io.*;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It interacts with the {@code TaskList} to store tasks in a text file and load them on startup.
 */
public class Storage {

    private static final String FILEPATH = "./data/db.txt";

    /**
     * Loads tasks from the file specified by {@code FILEPATH} and populates the given {@code TaskList}.
     *
     * @param tasks The {@code TaskList} to populate with the loaded tasks.
     */
    public void load(TaskList tasks) {
        new Validator().checkFileExists(FILEPATH);
        Parser dateTimeParser = new Parser();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String desc = parts[2].trim();
                switch (taskType) {
                case "T" -> {
                    ToDo todo = new ToDo(desc);
                    if (isDone) todo.markDone();
                    tasks.addExisting(todo);
                }
                case "D" -> {
                    Deadline deadline = new Deadline(desc, dateTimeParser.parseDateTime(parts[3].trim()));
                    if (isDone) deadline.markDone();
                    tasks.addExisting(deadline);
                }
                case "E" -> {
                    Event event = new Event(desc, dateTimeParser.parseDateTime(parts[3].trim()),
                            dateTimeParser.parseDateTime(parts[4].trim()));
                    if (isDone) event.markDone();
                    tasks.addExisting(event);
                }
                default -> System.out.println("Invalid task type in db.txt: " + taskType);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks from the given {@code TaskList} to the file specified by {@code FILEPATH}.
     * Each task is written in the format of type, status, description, and date/time if applicable.
     *
     * @param tasks The {@code TaskList} containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(FILEPATH, false)) {  // 'false' to overwrite the file
            for (Task task : tasks.getAsList()) {
                String line = getString(task);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a {@code Task} object into a string representation for file storage.
     * The format varies depending on the type of task (ToDo, Deadline, Event).
     *
     * @param task The {@code Task} to be converted into a string.
     * @return A string representation of the task, suitable for file storage.
     */
    public String getString(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.getDone() ? "1" : "0";
        String line = taskType + " | " + status + " | " + task.getDesc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        if (task instanceof Deadline deadline) {
            line += " | " + deadline.getBy().format(formatter);
        } else if (task instanceof Event event) {
            line += " | " + event.getFrom().format(formatter) + " | " + event.getTo().format(formatter);
        }
        return line;
    }
}
