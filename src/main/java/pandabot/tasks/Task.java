package pandabot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pandabot.exceptions.InputException;

/**
 * Abstract class representing a generic Task.
 * This class provides basic functionalities for a task,
 * such as marking the task as done, unmarking it, and displaying its status.
 */
public abstract class Task {

    /** The formatter for parsing and formatting dates in this application. */
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /** The description of the task. */
    protected String description;
    /** The status of the task: true if done, false otherwise. */
    protected Boolean isDone;
    /**
     * Constructs a new Task with the given description.
     * The task is initially not marked as done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a Task instance of type as specified in the input command
     * Abstract method for creating a specific type of task.
     * This method must be implemented by any subclass.
     *
     * @param input the input string containing the task details.
     * @return the created Task object.
     * @throws InputException if the input is invalid or incomplete.
     */
    public abstract Task createTask(String input) throws InputException;

    /**
     * Returns the status icon of the task.
     * 'X' indicates the task is done, while a space indicates it is not done.
     *
     * @return a string containing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task by setting isDone to false.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Creates a Task object from a string representation.
     * This is an abstract method to be implemented by subclasses.
     *
     * @param details an array of string details parsed from the file.
     * @return the created Task object.
     */
    public static Task parse(String[] details) {
        if (details.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file. Expected at least 3 parts.");
        }

        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        Task task;

        switch (type) {
        case "T":
            if (details.length != 3) {
                throw new IllegalArgumentException("Invalid tasks.ToDo task format. Expected exactly 3 parts.");
            }
            task = new ToDo(description);
            break;
        case "D":
            if (details.length != 4) {
                throw new IllegalArgumentException("Invalid tasks.Deadline task format. Expected exactly 4 parts.");
            }
            LocalDateTime by = LocalDateTime.parse(details[3], FORMATTER);
            task = new Deadline(description, by);
            break;
        case "E":
            if (details.length != 5) {
                throw new IllegalArgumentException("Invalid tasks.Event task format. Expected exactly 5 parts.");
            }
            LocalDateTime from = LocalDateTime.parse(details[3], FORMATTER);
            LocalDateTime to = LocalDateTime.parse(details[4], FORMATTER);
            task = new Event(description, from, to);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Returns a string representation of a task when saving to file.
     *
     * @return a string representation of the task.
     */
    public abstract String encode();

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a textual description of the task
     *
     * @return the description of this task
     */
    public String getDescription() {
        return this.description;
    }
}
