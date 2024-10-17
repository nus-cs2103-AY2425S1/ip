package ollie.task;

import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a generic task with a description and completion status.
 */
public abstract class Task {
    private static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private String description;
    private boolean isDone;
    private TaskType taskType;

    /**
     * Constructs a Task with the specified description and type.
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
     * Returns the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a boolean indicating if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
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
        return INPUT_DATE;
    }

    /**
     * Returns the DateTimeFormatter instance for the date output by the program
     *
     * @return formatDate the DateTimeFormatter instance with the pattern "MMM dd yyyy HH:mm".
     */
    public static DateTimeFormatter getFormatDate() {
        return FORMAT_DATE;
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
     * Saves the task as a string for storage.
     * @return The task as a string.
     */
    public String saveAsString() {
        return String.format("%s | %s | %s", taskType, getStatusIcon(), description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + "[" + taskType + "] " + description;
    }
}
