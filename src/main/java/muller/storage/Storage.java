package muller.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import muller.command.MullerException;
import muller.task.Task;
import muller.task.TaskList;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws MullerException If there is an issue loading the tasks.
     */
    public ArrayList<Task> loadTasks() throws MullerException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks; // No tasks to load, return empty list
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new MullerException("File is corrupted at line " + lineNumber + ".");
                }
                Task task = parseTask(parts);
                tasks.add(task);
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            throw new MullerException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The TaskList containing the tasks to save.
     * @throws MullerException If there is an issue saving the tasks.
     */
    public void saveTasks(TaskList tasks) throws MullerException {
        try {
            File file = new File(filePath);
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks.getTasks()) {
                writer.write(task.convertToFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MullerException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line of text from the file into a Task object.
     *
     * @param parts The components of the task read from the file.
     * @return The Task object parsed from the file.
     * @throws MullerException If there is an issue parsing the task.
     */
    private Task parseTask(String[] parts) throws MullerException {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String name = parts[2];
        Task task = new Task(name);
        task.setType(type);
        task.markAsDone(isDone);

        if (type.equals("D") && parts.length >= 4) {
            LocalDate date = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
            task.setDate(date);
        } else if (type.equals("E") && parts.length >= 5) {
            LocalDate startDate = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(parts[4], Task.INPUT_DATE_FORMATTER);
            task.setDateRange(startDate, endDate);
        }
        return task;
    }
}
