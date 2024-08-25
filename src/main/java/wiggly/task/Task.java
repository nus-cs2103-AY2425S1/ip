package wiggly.task;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * A class representation of a task
 */
public class Task {

    private final String description;
    private boolean isDone;

    /**
     * Creates a new {@code Task} instance based on the following String format:
     * {@code TaskType|isDone|Description|Date|Date}
     *
     * @param dataLine The specified data format
     * @return The {@code Task} instance
     */
    public static Task createFromData(String dataLine) throws IllegalArgumentException {
        Task task;
        String[] arguments = dataLine.split("\\|");
        String taskType = arguments[0];
        boolean isDone = Boolean.parseBoolean(arguments[1]);
        String taskDescription = arguments[2];

        try {
            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                task.isDone = isDone;
                break;
            case "D":
                String by = arguments[3];
                task = new Deadline(taskDescription, LocalDate.parse(by));
                break;
            case "E":
                String from = arguments[3];
                String to = arguments[4];
                task = new Event(taskDescription, LocalDate.parse(from), LocalDate.parse(to));
                break;
            default:
                throw new IllegalArgumentException("Invalid task type found in save file");

            }
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid date format found in save file");
        }

	    return task;
    }

    /**
     * Creates a {@code Task} instance with the given description
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done, if the task is already done, it will remain the same
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone, if the task is already marked as undone, it will remain the same
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the done status of the task, if the task is done, it will be represented as
     * {@code X}, if the task is not done, it will be represented as {@code " "}
     * @return The String representation of the done status of the task
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a String representation of the task to be stored in a data file
     * @return The converted string
     */
    public String toFileFormat() {
        return isDone + "|" + this.description;
    }

    /**
     * Returns a String representation of the task
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
