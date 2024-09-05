package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class representing a task. A task has a description and a completion status.
 * The specific type of task is determined by subclasses.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns the status icon of the task. Indicates whether the task is completed or not.
     *
     * @return A string representing the status icon: "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done maga.task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string of the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task. The specific type is determined by subclasses.
     *
     * @return A string representing the type of the task.
     */
    public abstract String getTaskType();

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representation of the task.
     */
    public abstract String printTask();

    /**
     * Creates a {@code Task} object from a string representation of the task.
     * The string format is: "type | status | description | date".
     * Only called when parsing string representation of tasks from the Maga data file by {@code TaskManager}.
     *
     * @param taskString The string representation of the task.
     * @return A {@code Task} object created from the string, or a default {@code TodoTask}
     * if the string cannot be parsed.
     */
    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts.length == 3) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            return new TodoTask(isDone, description);
        } else if (parts.length == 4) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate dateTime = LocalDate.parse(parts[3], formatter); // need handle error
            return new EventTask(isDone, description, dateTime);
        } else if (parts.length == 5) {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate startDate = LocalDate.parse(parts[3], formatter); // need handle error
            LocalDate endDate = LocalDate.parse(parts[4], formatter);
            return new DeadlineTask(isDone, description, startDate, endDate);
        }

        return new TodoTask(false, "");
    }

    /**
     * Returns a string representation of the task. This method must be implemented by subclasses.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();
}
