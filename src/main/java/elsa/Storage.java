package elsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import elsa.task.Deadline;
import elsa.task.Event;
import elsa.task.Task;
import elsa.task.TaskList;
import elsa.task.Todo;

/**
 * Handles the loading of tasks from the data file and saving tasks to the file.
 * @author Aaron
 */
public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("Elsa.txt");

    /**
     * Initialises the storage and ensures that the data file exists. If the data file does not exist,
     * it will be created.
     *
     * @throws ElsaException If there is an error when ensuring the existence of the data file.
     */
    public Storage() throws ElsaException {
        ensureDataFileExists();
    }

    /**
     * This method ensures that the directory for the elsa.ui.Elsa.txt data file which is ..\ip\data exists.
     * If it does not exist, the method creates the ..\ip\data folder.
     * The method then checks if an elsa.ui.Elsa.txt file exists within the ..\ip\data folder.
     * If it does not exist, a new elsa.ui.Elsa.txt file is created.
     *
     * @throws ElsaException If there is an error when creating directories or files.
     */
    private static void ensureDataFileExists() throws ElsaException {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
            }
        } catch (IOException e) {
            throw new ElsaException("Oops, it seems like an error has occurred while "
                    + "creating directories or files:\n" + e.getMessage());
        }
    }

    /**
     * This method updates the tasks arrayList to match the data in the elsa.ui.Elsa.txt file.
     *
     * @return A list of tasks, reflecting the content found in the elsa.ui.Elsa.txt file.
     * @throws ElsaException If there is an error when reading from the data file.
     */
    public List<Task> populateTaskList() throws ElsaException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(convertStringToTask(line));
            }
        } catch (IOException e) {
            throw new ElsaException("Oops, it appears that an error has occurred "
                    + "while reading the data file:\n" + e.getMessage());
        }
        return tasks;
    }

    /**
     * This method converts a line in the elsa.ui.Elsa.txt data file to its corresponding elsa.task.Task object.
     *
     * @param taskInfo A line of text from the file.
     * @return The corresponding elsa.task.Task object.
     * @throws ElsaException If the task type is invalid or if there is an error when parsing the task.
     */
    private static Task convertStringToTask(String taskInfo) throws ElsaException {
        String[] parts = taskInfo.split(" \\| ");

        String taskType = parts[0];
        String description = parts[2];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            // Create a elsa.task.Todo task
            return new Todo(description, isDone);

        case "D":
            // Create a elsa.task.Deadline task
            String dueBy = parts[3];
            return new Deadline(description, isDone, dueBy);

        case "E":
            // Create an elsa.task.Event task
            String[] eventTimes = parts[3].split(" - ");
            String start = eventTimes[0].trim();
            String end = eventTimes[1].trim();
            return new Event(description, isDone, start, end);

        default:
            throw new ElsaException("Oops, it appears that the tasks saved "
                    + "in our data file are of an invalid task type:\n" + taskType);
        }
    }

    /**
     * Saves the list of tasks to the data file.
     *
     * @param tasks The list of tasks to save.
     * @throws ElsaException If there is an error when writing to the data file.
     */
    public void saveTasksToDataFile(TaskList tasks) throws ElsaException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile(), false))) {
            for (Task task : tasks.getTasks()) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ElsaException("Oops, it appears that an error has occurred while "
                    + "writing to the data file:\n" + e.getMessage());
        }
    }

    /**
     * Converts an elsa.task.Task object to a line of text for the file.
     *
     * @param task The elsa.task.Task object.
     * @return The corresponding line of text.
     * @throws ElsaException If the task type is unknown or cannot be converted.
     */
    private String taskToString(Task task) throws ElsaException {
        if (task instanceof Todo) {
            return "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | "
                    + deadline.getDueBy();
        } else if (task instanceof Event event) {
            return "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | "
                    + event.getStart() + " - " + event.getEnd();
        } else {
            throw new ElsaException("Oops, it appears that this task saved in our list "
                    + "is of an unknown type:\n" + task.getClass().getSimpleName());
        }
    }
}
