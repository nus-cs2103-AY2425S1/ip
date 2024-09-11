package storage;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import main.Parser;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.io.*;

/**
 * The {@code Storage} class handles reading tasks from a file and saving tasks back to a file.
 * This class ensures that task data is persistent between application runs.
 */
public class Storage {
    private final String filepath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filepath The path to the file where tasks are stored.
     */

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file at the specified filepath.
     *
     * @return A list of tasks loaded from the file. If the file does not exist or
     *         is empty, an empty list is returned.
     */
    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(this.filepath);

        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, taskList);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Processes each line from the file and adds the task to the task list.
     *
     * @param line      The line read from the file.
     * @param taskList  The list to which the parsed tasks will be added.
     */
    private void processLine(String line, List<Task> taskList) {
        String[] parts = line.trim().split("\\s*\\|\\s*");

        if (isValidLine(parts)) {
            String priority = parts[0];  // Priority is now the first element
            String taskType = parts[1];  // Task type is now the second element
            boolean isDone = parts[2].equals("1");

            // Check if parts[3] (description) exists before accessing it
            if (parts.length < 4) {
                System.out.println("Skipping line due to missing description: " + line);
                return;
            }
            String description = parts[3];

            switch (taskType) {
            case "T":
                processTodoTask(taskList, description, isDone, priority);
                break;
            case "D":
                processDeadlineTask(parts, taskList, description, isDone, line, priority);
                break;
            case "E":
                processEventTask(parts, taskList, description, isDone, line, priority);
                break;
            default:
                System.out.println("Unknown task type: " + taskType);
                break;
            }
        } else {
            System.out.println("Skipping invalid line: " + line);
        }
    }

    /**
     * Validates if the given line parts are valid for processing.
     *
     * @param parts The parts of the split line.
     * @return {@code true} if the line has at least 3 parts, {@code false} otherwise.
     */
    private boolean isValidLine(String[] parts) {
        return parts.length >= 3;
    }

    /**
     * Processes a to-do task and adds it to the task list.
     *
     * @param taskList    The list of tasks.
     * @param description The description of the to-do task.
     * @param isDone      Indicates if the task is marked as done.
     * @param priority    The priority of the task.
     */
    private void processTodoTask(List<Task> taskList, String description, boolean isDone, String priority) {
        ToDo newToDo = new ToDo(description, priority);
        if (isDone) {
            newToDo.markAsDone();
        }
        taskList.add(newToDo);
    }

    /**
     * Processes a deadline task and adds it to the task list.
     *
     * @param parts       The parts of the line that describe the deadline task.
     * @param taskList    The list of tasks.
     * @param description The description of the deadline task.
     * @param isDone      Indicates if the task is marked as done.
     * @param line        The line read from the file, used for error reporting.
     * @param priority    The priority of the task.
     */
    private void processDeadlineTask(String[] parts, List<Task> taskList, String description, boolean isDone, String line, String priority) {
        if (parts.length < 5) {
            System.out.println("Skipping invalid Tasks.Deadline line: " + line);
            return;
        }
        try {
            LocalDateTime dueWhen = Parser.parseDateTime(parts[4]);  // Ensure index is correct for the date
            Deadline newDeadline = new Deadline(description, priority, dueWhen);
            if (isDone) {
                newDeadline.markAsDone();
            }
            taskList.add(newDeadline);
        } catch (DateTimeParseException e) {
            System.out.println("Skipping invalid date format in Tasks.Deadline: " + line);
        }
    }

    /**
     * Processes an event task and adds it to the task list.
     *
     * @param parts       The parts of the line that describe the event task.
     * @param taskList    The list of tasks.
     * @param description The description of the event task.
     * @param isDone      Indicates if the task is marked as done.
     * @param line        The line read from the file, used for error reporting.
     * @param priority    The priority of the task.
     */
    private void processEventTask(String[] parts, List<Task> taskList, String description, boolean isDone, String line, String priority) {
        if (parts.length < 6) {
            System.out.println("Skipping invalid Tasks.Event line: " + line);
            return;
        }
        try {
            LocalDateTime startWhen = Parser.parseDateTime(parts[4]);  // Ensure index is correct for start date
            LocalDateTime endWhen = Parser.parseDateTime(parts[5]);    // Ensure index is correct for end date
            Event newEvent = new Event(description, priority, startWhen, endWhen);
            if (isDone) {
                newEvent.markAsDone();
            }
            taskList.add(newEvent);
        } catch (DateTimeParseException e) {
            System.out.println("Skipping invalid date format in Tasks.Event: " + line);
        }
    }

    /**
     * Saves the list of tasks to the file at the specified filepath.
     *
     * @param taskList the list of tasks to save
     */
    public void saveTasks(List<Task> taskList) {
        assert taskList != null : "Task list should not be null";

        File file = new File(this.filepath);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
