package devon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is a deadline.
 * A <code>Deadline</code> object corresponds to a task represented by a
 * description, and a due date/time that the task needs to be completed by.
 */

public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a new Deadline object.
     *
     * @param description A description of the task.
     * @param by The due date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the due date and time into a more readable string format.
     *
     * @return A string representing the due date/time in the format "MMM d yyyy, h:mm a".
     */
    private String byToString() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Provides a string representation of the "Deadline" task in a format suitable for database storage.
     * The format is "Deadline|status|description|by", where status is 1 if the task
     * is done, otherwise 0.
     *
     * @return A string representation of the Deadline for database storage.
     */
    @Override
    public String dbReadableFormat() {
        return String.format("Deadline|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.by);
    }

    /**
     * Returns the string representation of the Deadline object,
     * which includes the task type, status, description, and due date/time.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byToString() + ")";
    }
}
