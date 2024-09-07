package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDos;

/**
 * The Storage class handles saving and loading tasks from a file to persist the state of the task list.
 * It reads from and writes to a specified file to store the tasks across sessions.
 */
public class Storage {

    /** The directory where the state file is stored. */
    private static final String STATE_FILE_DIRECTORY = "./data";

    /** The name of the state file where tasks are saved. */
    private static final String STATE_FILE = "save.txt";

    /**
     * Converts a line of text from the state file into a Task object.
     *
     * @param state The line of text representing a saved task.
     * @return The corresponding Task object, or null if there is an error converting the task.
     */
    private static Task convertStateToTask(String state) {
        // Assert that the state string is valid and not empty
        assert state != null && !state.trim().isEmpty() : "Invalid state string provided";

        String[] taskInformation = state.split(" \\| ");
        assert taskInformation.length >= 3 : "Malformed state string, expected at least 3 parts";

        String description = taskInformation[2];
        Task task;

        if (taskInformation[0].equals("T")) {
            task = new ToDos(description);
        } else if (taskInformation[0].equals("D")) {
            assert taskInformation.length >= 4 : "Malformed deadline state, expected 4 parts";
            String by = taskInformation[3];

            try {
                task = new Deadline(description, by);
            } catch (InvalidDeadlineException e) {
                System.out.println(e.getMessage() + " error converting task back to deadline");
                return null;
            }
        } else if (taskInformation[0].equals("E")) {
            assert taskInformation.length >= 4 : "Malformed event state, expected 4 parts";
            String[] times = taskInformation[3].split("-");
            assert times.length == 2 : "Malformed event times, expected 'from-to' format";
            String from = times[0];
            String to = times[1];

            try {
                task = new Event(description, from, to);
            } catch (InvalidEventException e) {
                System.out.println(e.getMessage() + " error converting task back to event");
                return null;
            }
        } else {
            throw new IllegalArgumentException("Unknown task type: " + taskInformation[0]);
        }

        if (taskInformation[1].equals("1")) {
            task.setDone();
        }

        return task;
    }

    /**
     * Saves the current list of tasks to the state file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveTasksListToStateFile(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks list cannot be null";

        Path dirPath = Paths.get(Storage.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(Storage.STATE_FILE);

        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        BufferedWriter writer = Files.newBufferedWriter(filePath);
        List<String> stateFile = new ArrayList<>();

        for (Task task : tasks) {
            assert task != null : "Task cannot be null";
            stateFile.add(Storage.convertTaskToState(task));
        }

        for (String line : stateFile) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Converts a Task object into a string that can be saved in the state file.
     *
     * @param task The task to be converted.
     * @return The string representation of the task for storage.
     */
    private static String convertTaskToState(Task task) {
        assert task != null : "Task cannot be null";

        StringBuilder str = new StringBuilder();
        str.append(task.getStatusIcon().equals("X") ? "| 1 " : "| 0 ");
        str.append("| ");
        str.append(task.getDescription());
        str.append(" ");

        if (task instanceof ToDos) {
            str.insert(0, "T ");
        } else if (task instanceof Deadline deadline) {
            str.insert(0, "D ");
            str.append("| ");
            str.append(deadline.getBy());
        } else if (task instanceof Event event) {
            str.insert(0, "E ");
            str.append("| ");
            str.append(event.getFrom());
            str.append("-");
            str.append(event.getTo());
        }

        return str.toString();
    }

    /**
     * Loads the state file and converts its content to a list of Task objects.
     * Creates the state file and directory if they do not exist.
     *
     * @return The list of tasks read from the state file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> loadStateFileToTasksList() throws IOException {
        Path dirPath = Paths.get(Storage.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(Storage.STATE_FILE);

        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        List<String> lines = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // Convert the state file lines to a list of tasks
        List<Task> tasks = new ArrayList<>();
        for (String state : lines) {
            Task task = Storage.convertStateToTask(state);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }
}
