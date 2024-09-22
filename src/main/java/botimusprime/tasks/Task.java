package botimusprime.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Task class is an abstract representation of a task.
 * It contains common attributes and methods shared by all types of tasks,
 * such as description, status, and methods to mark the task as done or undone.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public abstract boolean isEvent();

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public abstract boolean isToDo();

    /**
     * Checks if the task is a deadline.
     *
     * @return true if the task is a deadline, false otherwise.
     */
    public abstract boolean isDeadline();

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and " " indicates that the task is not done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
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
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the task into a string that can be saved to disk.
     * This method should be overridden by subclasses to provide specific formatting.
     *
     * @return A string representation of the task for saving to disk.
     */
    public String saveString() {
        return "placeholder";
    }

    /**
     * Creates a Task object from a string representation saved on disk.
     * The string is expected to be in a specific format that matches the task's type.
     *
     * @param taskString The string representation of the task.
     * @return The Task object created from the string.
     */
    public static Task fromString(String taskString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] parser = taskString.split(" \\| ");
        String type = parser[0];
        String description = parser[1];
        boolean isDone = Boolean.parseBoolean(parser[2]);

        if (Objects.equals(type, "T")) {
            return new ToDo(description, isDone);
        } else if (Objects.equals(type, "D")) {
            String deadline = parser[3];
            return new Deadline(description, isDone, LocalDateTime.parse(deadline, formatter));
        } else {
            String from = parser[3];
            String to = parser[4];
            return new Event(description, isDone, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
        }

    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


}