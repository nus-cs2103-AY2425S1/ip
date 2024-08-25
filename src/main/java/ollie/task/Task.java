package ollie.task;

import ollie.exception.OllieException;
import ollie.TaskType;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    private static final DateTimeFormatter inputDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a ollie.task.Task with the specified description and type.
     *
     * @param description The description of the task.
     * @param taskType The type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the DateTimeFormatter instance for the date input by the user
     *
     * @return inputDate the DateTimeFormatter instance with the pattern "yyyy-MM-dd HH:mm".
     */
    public static DateTimeFormatter getInputDate() {
        return inputDate;
    }

    /**
     * Returns the DateTimeFormatter instance for the date output by the program
     *
     * @return formatDate the DateTimeFormatter instance with the pattern "MMM dd yyyy HH:mm".
     */
    public static DateTimeFormatter getFormatDate() {
        return formatDate;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param status The status to set (true for done, false for not done).
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Validates the task's description based on the command.
     *
     * @param command The command string used to create the task.
     * @throws OllieException If the description is invalid.
     */
    public abstract void validateDescription(String command) throws OllieException;

    /**
     * Saves the task as a string for storage.
     * @return The task as a string.
     */
    public String saveAsString() {
        return String.format("%s | %s | %s", taskType, getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + "[" + taskType + "] " + description;
    }
}