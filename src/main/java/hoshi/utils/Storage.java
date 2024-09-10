package hoshi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import hoshi.task.Deadline;
import hoshi.task.Event;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;

/**
 * Storage class that handles I/O related functions such as Loading and Saving for Hoshi
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new instance of Storage.
     *
     * @param filePath String path of where Hoshi txt file is relatively located from root
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from hoshi txt file if user is not new else greets the user.
     *
     * @param taskList ArrayList of 3 types of tasks to be retrieved from hoshi txt file.
     */
    public void load(TaskList taskList) throws FileNotFoundException {
        File file = new File(this.filePath);

        if (!file.exists()) {
            System.out.println("Hoshi has detected a new user! Welcome!");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }
    }

    /**
     * Parses a single line from txt file and returns the corresponding task.
     *
     * @param line The line to be parsed.
     * @return Task created from the parsed line or null if task type is unknown.
     */
    private Task parseTask(String line) {
        // parse common attributes of all task types
        String[] parts = line.split(", ");
        String taskType = parts[0];
        boolean isDone = Objects.equals(parts[1], "D");
        String description = parts[2];

        // parse unique fields each task type contains then return
        switch (taskType) {
        case "Todo":
            return new Todo(description, isDone);
        case "Deadline":
            return parseDeadline(parts, description, isDone);
        case "Event":
            return parseEvent(parts, description, isDone);
        default:
            // if task type is not known to Hoshi
            System.out.println("Hoshi is not aware of this task type: " + taskType + "!");
            return null;
        }
    }

    /**
     * Parses a Deadline task from txt file.
     *
     * @param parts       The split parts of the line.
     * @param description The task description.
     * @param isDone      The completion status of the task.
     * @return A Deadline task.
     */
    private Deadline parseDeadline(String[] parts, String description, boolean isDone) {
        // get deadlineDateTimeEnd from 3rd index
        LocalDate deadlineDateTimeEnd = LocalDate.parse(parts[3]);
        return new Deadline(description, isDone, deadlineDateTimeEnd);
    }

    /**
     * Parses an Event task from txt file.
     *
     * @param parts       The split parts of the line.
     * @param description The task description.
     * @param isDone      The completion status of the task.
     * @return An Event task.
     */
    private Event parseEvent(String[] parts, String description, boolean isDone) {
        // gets start and end event date times from 3rd/4th index
        LocalDate dateTimeEnd = LocalDate.parse(parts[3]);
        LocalDate dateTimeStart = LocalDate.parse(parts[4]);
        return new Event(description, isDone, dateTimeEnd, dateTimeStart);
    }

    /**
     * Saves tasks added and retrieved during the program to hoshi txt file.
     *
     * @param tasks TaskList of 3 types of tasks to be written to hoshi txt file.
     */
    public void save(TaskList tasks) throws IOException {
        try (FileWriter fileWriter = new FileWriter(this.filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                // get, format and store task
                Task task = tasks.get(i);
                String taskData = formatTask(task);
                fileWriter.write(taskData);
            }
        }
    }

    /**
     * Formats a task as a string to be saved into the file.
     *
     * @param task The task to format.
     * @return A formatted string representing the task.
     */
    private String formatTask(Task task) {
        // format the variables as individual strings
        String taskType = task.getClass().getSimpleName();
        String isDone = task.getStatusIcon().equals(" ") ? "ND" : "D";
        String description = task.getDesc();
        String additionalFields = formatAdditionalFields(task);

        // add up the individual strings to be added as 1 line to store
        String textToAdd;
        textToAdd = taskType + ", " + isDone + ", " + description + additionalFields + "\n";

        return textToAdd;
    }

    /**
     * Formats additional fields for Deadlines and Events.
     *
     * @param task The corresponding task to extract additional fields from.
     * @return A string representing the additional fields for Deadlines and Events.
     */
    private String formatAdditionalFields(Task task) {
        if (task instanceof Deadline deadline) {
            return ", " + deadline.getEndTime();
        } else if (task instanceof Event event) {
            return ", " + event.getEndTime() + ", " + event.getStartTime();
        }
        return "";
    }

}
