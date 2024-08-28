package slothingwaffler;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the application.
 * <p>
 * This is an abstract class representing a task with a description and a status indicating whether it's done.
 * </p>
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    // For now, Deadline Tasks will take in dates only, while Event Tasks will take in dates and times
    protected final static DateTimeFormatter DTF1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final static DateTimeFormatter DTF2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected final static DateTimeFormatter DTF3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final static DateTimeFormatter DTF4 = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a Task instance with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a formatted string representing the task for file storage.
     *
     * @return a formatted string representing the task
     */
    public String toFileFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
