package shoai;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. This class extends the Task class to include
 * a deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline datetime.
     *
     * @param description The description of the task.
     * @param by The deadline datetime of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline datetime of the task.
     *
     * @return The deadline datetime of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
