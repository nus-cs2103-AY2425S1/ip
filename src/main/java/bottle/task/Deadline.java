package bottle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Extends the Task class to include the deadline date and time.
 */
public class Deadline extends Task {
    /**
     * The deadline for this task.
     */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline with the specified description and deadline.
     *
     * @param description the description of the deadline task
     * @param by          the deadline date and time for the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline, including its type, description,
     * and deadline.
     *
     * @return a string representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline formatted for file storage.
     * The format includes the task type, completion status, description, and deadline.
     *
     * @return a string representation of the Deadline in a file-friendly format
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "D | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | " + this.by.format(formatter);
    }
}
