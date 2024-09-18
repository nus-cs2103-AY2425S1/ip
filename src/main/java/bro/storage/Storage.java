package bro.storage;

import bro.BroException;
import bro.task.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles reading from and writing to storage for tasks managed by Bro.
 * The <code>Storage</code> class provides methods to save tasks to a file and
 * load tasks from a file into a <code>TaskList</code>.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the list of tasks to the storage file. If the file or directory does not exist,
     * it creates them.
     *
     * @param tasks The list of tasks to be written to the storage file.
     */
    public void writeToStorage(ArrayList<Task> tasks) {
        File file = new File(this.filePath);

        file.getParentFile().mkdirs(); // Create directory if it does not exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write((i + 1) + ". " + tasks.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and returns them as a TaskList.
     * If the file does not exist, an empty TaskList is returned.
     *
     * @return A <code>TaskList</code> containing the tasks loaded from the storage file.
     * @throws BroException If an error occurs while reading from the file.
     */
    public TaskList loadFromStorage() throws BroException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if file exists; if not, return an empty TaskList
        if (!file.exists()) {
            return new TaskList(tasks);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new BroException("An error occurred while reading the file: " + e.getMessage());
        }

        return new TaskList(tasks);
    }

    /**
     * Parses a line from the file and returns the corresponding Task object.
     * The type of task is determined based on the prefix in the line.
     *
     * @param line The line to be parsed from the storage file.
     * @return The corresponding <code>Task</code> object based on the line content.
     */
    private Task parseTask(String line) {
        String trimmedLine = line.substring(line.indexOf(" ") + 1);

        // Determine the task type by its prefix
        boolean isCompleted = trimmedLine.contains("[X]");
        if (trimmedLine.startsWith("[T]")) {
            String content = trimmedLine.substring(6); // Skip "[T][ ] "
            return new TodoTask(content, isCompleted);
        } else if (trimmedLine.startsWith("[D]")) {
            String content = trimmedLine.substring(6, trimmedLine.indexOf("(by:")).trim();
            String deadline = trimmedLine.substring(trimmedLine.indexOf("(by:") + 5, trimmedLine.lastIndexOf(")")).trim();
            return new DeadlineTask(content, deadline, isCompleted);
        } else if (trimmedLine.startsWith("[E]")) {
            String content = trimmedLine.substring(6, trimmedLine.indexOf("(from:")).trim();
            String times = trimmedLine.substring(trimmedLine.indexOf("(from:") + 6, trimmedLine.lastIndexOf(")")).trim();
            String[] timeParts = times.split("to:");
            String startTime = timeParts[0].trim();
            String endTime = timeParts[1].trim();
            return new EventTask(content, startTime, endTime, isCompleted);
        }
        return null;
    }
}
