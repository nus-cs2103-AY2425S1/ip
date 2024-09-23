package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initializes a Deadline task with a description and a deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Converts the Deadline task to a string format suitable for saving to a file.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFile() {
        return "D|" + getStatusIcon() + "|" + this.description + "|"
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Provides a string representation of the Deadline task for display.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
