package bigdog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Storage class handles saving and loading of tasks from a file.
 * It reads tasks from a file and converts them into Task objects,
 * and also saves the current tasks in the TaskList to the file.
 */
public class Storage {

    /** The file path where the tasks are stored and loaded from. */
    private final String filePath;

    /**
     * Constructs a Storage object.
     * Initializes the file path where the tasks will be stored.
     *
     * @param filePath the file path to save and load tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file.
     * Each task is converted into string format and written to the file.
     *
     * @param taskList the list of tasks to be saved.
     * @throws BigdogException if there is an error during the saving process.
     */
    public void save(ArrayList<Task> taskList) throws BigdogException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task task: taskList) {
                String line = getString(task);
                writer.write(line + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new BigdogException("Storage Error: You do not have a .txt file to store my memory! " + e);
        } catch (IOException e) {
            throw new BigdogException("Storage Error: IO Error " + e);
        }
    }

    /**
     * Converts a Task object into its string representation.
     *
     * @param task the task to be converted to string format.
     * @return the string representation of the task.
     * @throws BigdogException if the task type is not recognized.
     */
    private static String getString(Task task) throws BigdogException {
        StringBuilder line = new StringBuilder();
        line.append(task.isMarked() ? "X | " : "O | ");

        if (task instanceof Todo) {
            line.append("T | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            line.append("D | ").append(task.getDescription());
        } else if (task instanceof Event) {
            line.append("E | ").append(task.getDescription());
        } else {
            throw new BigdogException("Storage Error: Unrecognized task type.");
        }
        return line.toString();
    }

    /**
     * Loads the list of tasks from the file.
     * Each line in the file is converted into a Task object and added to the list.
     *
     * @return an ArrayList of tasks loaded from the file.
     * @throws BigdogException if there is an error during the loading process,
     *     such as file corruption or file not found.
     */
    public ArrayList<Task> load() throws BigdogException {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new BigdogException("Storage Error: Unable to load tasks. File Path: " + e);
        }
        return list;
    }

    /**
     * Parses a line of text into a task.
     *
     * @param line the line of text
     * @return the parsed task
     * @throws BigdogException if the line is corrupted or invalid
     */
    private static Task parseLine(String line) throws BigdogException {
        if (line.charAt(0) == 'X') {
            return Task.of(line.substring(4), true);
        } else if (line.charAt(0) == 'O') {
            return Task.of(line.substring(4), false);
        } else {
            throw new BigdogException("Storage Error: Corrupted data in file.");
        }
    }

}
