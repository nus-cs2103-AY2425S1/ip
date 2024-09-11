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

/**
 * Handles the loading of tasks from the data file and saving tasks to the file.
 * @author Aaron
 */
public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("Elsa.txt");

    public Storage() throws ElsaException {
        ensureDataFileExists();
    }

    /**
     * This method ensures that the directory for the Elsa.txt data file which is ..\ip\data exists.
     * If it does not exist, the method creates the ..\ip\data folder.
     * The method then checks if an Elsa.txt file exists within the ..\ip\data folder.
     * If it does not exist, a new Elsa.txt file is created.
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
            String message = ElsaException.addSeparatorLines("Oops, it seems like an error has occurred while " +
                    "creating directories or files:\n" + e.getMessage());
            throw new ElsaException(message);
        }
    }

    /**
     * This method updates the tasks arrayList to match the data in the Elsa.txt file.
     *
     * @return A list of tasks, reflecting the content found in the Elsa.txt file.
     */
    public List<Task> populateTaskList() throws ElsaException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(convertStringToTask(line));
            }
        } catch (IOException e) {
            String message = ElsaException.addSeparatorLines("Oops, it appears that an error has occurred " +
                    "while reading the data file:\n" + e.getMessage());
            throw new ElsaException(message);
        }
        return tasks;
    }

    /**
     * This method converts a line in the Elsa.txt data file to its corresponding Task object.
     *
     * @param taskInfo A line of text from the file.
     * @return The corresponding Task object.
     */
    private static Task convertStringToTask(String taskInfo) throws ElsaException {
        String[] parts = taskInfo.split(" \\| ");

        String taskType = parts[0];
        String description = parts[2];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
            case "T":
                // Create a Todo task
                return new Todo(description, isDone);

            case "D":
                // Create a Deadline task
                String dueBy = parts[3];
                return new Deadline(description, isDone, dueBy);

            case "E":
                // Create an Event task
                String[] eventTimes = parts[3].split(" - ");
                String start = eventTimes[0].trim();
                String end = eventTimes[1].trim();
                return new Event(description, isDone, start, end);

            default:
                String message = ElsaException.addSeparatorLines("Oops, it appears that the tasks saved " +
                        "in our data file are of an invalid task type:\n" + taskType);
                throw new ElsaException(message);
        }
    }

    /**
     * Saves the list of tasks to the data file.
     *
     * @param tasks The list of tasks to save.
     */
    private void saveTasksToDataFile(List<Task> tasks) throws ElsaException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile(), false))) {
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            String message = ElsaException.addSeparatorLines("Oops, it appears that an error has occurred while " +
                    "writing to the data file:\n" + e.getMessage());
            throw new ElsaException(message);
        }
    }

    /**
     * Converts a Task object to a line of text for the file.
     * @param task The Task object.
     * @return The corresponding line of text.
     */
    private String taskToString(Task task) throws ElsaException {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getDueBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " +
                    event.getStart() + " - " + event.getEnd();
        } else {
            String message = ElsaException.addSeparatorLines("Oops, it appears that this task saved in our list " +
                    "is of an unknown type:\n" + task.getClass().getSimpleName());
            throw new ElsaException(message);
        }
    }
}
