package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline task has a description and a specific date and time
 * by which it needs to be completed.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and a deadline time as a string.
     *
     * @param description The description of the task.
     * @param by The deadline time in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Constructs a Deadline task with the specified description and a deadline time as a string.
     *
     * @param description The description of the task.
     * @param by The deadline time as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return The description of the deadline task.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the deadline time of the task.
     *
     * @return The LocalDateTime object representing the task's deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type, completion status, description, and formatted deadline time.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    /**
     * Returns a formatted string suitable for saving the task to a file.
     * Includes the task type, completion status, description, and formatted deadline time.
     *
     * @return A formatted string representation of the task for saving.
     */
    @Override
    public String toSaveFormat() {
        return "task.Deadline | " + (isDone ? "Done" : "Not Done") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}