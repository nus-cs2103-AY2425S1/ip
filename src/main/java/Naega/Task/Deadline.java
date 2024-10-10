package Naega.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param by          the due date and time of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by != null : "Deadline 'by' date should not be null";
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return a string describing the deadline, including the due date
     */
    @Override
    public String toString() {
        assert by != null : "Deadline 'by' date should not be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string suitable for saving the Deadline task to a file.
     *
     * @return a string representation of the Deadline task for file storage
     */
    @Override
    public String toSaveFormat() {
        assert by != null : "Deadline 'by' date should not be null when saving";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}