package purrfessordipsy.task;

import java.time.LocalDate;

/**
 * Represents a generic task. A task has a description and a completion status (done or not done).
 * This class serves as the base class for more specific types of tasks like ToDo, Deadline, and Event.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task (true if done, false otherwise). */
    protected boolean isDone;

    /** The delimiter used to separate fields when formatting the task to CSV format. */
    protected static final String DELIMITER = "|";

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task (true if done, false otherwise).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion status.
     * "X" indicates that the task is done, and a space (" ") indicates that the task is not done.
     *
     * @return The status icon representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Wraps the given string in quotes for CSV formatting.
     *
     * @param str The string to wrap in quotes.
     * @return The string wrapped in quotes.
     */
    protected String wrapInQuotes(String str) {
        return "\"" + str + "\"";
    }

    /**
     * Returns the type of the task. This method is intended to be overridden by subclasses.
     * By default, it returns "Task" for a generic task.
     *
     * @return The string "Task" indicating the type of task.
     */
    protected String getTaskType() {
        return "Task";
    }

    /**
     * Returns the relevant date for this task. By default, a generic task does not have a relevant date.
     * This method is intended to be overridden by subclasses that have relevant dates (e.g., Deadline, Event).
     *
     * @return {@code null} as a generic task does not have a relevant date.
     */
    public LocalDate getRelevantDate() {
        return null; // By default, no relevant date for a generic task
    }

    /**
     * Formats the task into a CSV-compatible string for storage. The format includes the task's status,
     * type, and description, separated by the delimiter.
     *
     * @return A string representing the task in CSV format.
     */
    public String formatToCSV() {
        String[] arr = new String[]{
                wrapInQuotes(this.getStatusIcon()),
                wrapInQuotes(this.getTaskType()),
                wrapInQuotes(this.description)
        };
        return String.join(DELIMITER, arr);
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     * This method is used to display the task in a human-readable format.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}