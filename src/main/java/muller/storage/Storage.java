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
import muller.task.DeadlineTask;
import muller.task.EventTask;
import muller.task.Task;
import muller.task.TaskList;
import muller.task.TodoTask;

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
     * @return The specific Task object (TodoTask, DeadlineTask, EventTask) parsed from the file.
     * @throws MullerException If there is an issue parsing the task.
     */
    private Task parseTask(String[] parts) throws MullerException {
        String type = parts[0].trim(); // Get task type: [T], [D], [E]
        boolean isDone = parts[1].equals("1"); // Done status
        String name = parts[2].trim(); // Task name

        Task task;
        switch (type) {
        case "T":
            task = new TodoTask(name);
            break;
        case "D":
            if (parts.length < 4) {
                throw new MullerException("Deadline task is missing date information.");
            }
            LocalDate deadline = LocalDate.parse(parts[3].trim(), Task.OUTPUT_DATE_FORMATTER);
            task = new DeadlineTask(name, deadline);
            break;
        case "E":
            if (parts.length < 5) {
                throw new MullerException("Event task is missing start and/or end date information.");
            }
            LocalDate startDate = LocalDate.parse(parts[3].trim(), Task.OUTPUT_DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(parts[4].trim(), Task.OUTPUT_DATE_FORMATTER);
            task = new EventTask(name, startDate, endDate);
            break;
        default:
            throw new MullerException("Unknown task type found in file.");
        }

        return task;
    }
}

