package Jard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with a description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date and time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the due date and time of the deadline.
     *
     * @return The due date and time as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task for file storage.
     *
     * @return A string representing the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return The description of the deadline.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string for the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
