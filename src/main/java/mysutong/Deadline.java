package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific date and time by which the task must be completed.
 * Extends {@link Task}.
 */
public class Deadline extends Task {
    /**
     * The date and time by which the task must be completed.
     */
    private LocalDateTime by;

    /**
     * Constructs a new Deadline.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the deadline task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline, including its description
     * and its due date formatted as "MMM d yyyy h:mma".
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    /**
     * Returns a string suitable for file storage, representing the deadline task with its completion status,
     * description, and due date formatted as "d/M/yyyy HHmm".
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", (isDone ? 1 : 0),
                description,
                by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
