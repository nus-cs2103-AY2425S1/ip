package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 * A deadline task includes a description and a due date/time by which the task should be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime dueWhen;

    /**
     * Constructs a deadline task with the specified description and due date/time.
     *
     * @param description the description of the deadline task
     * @param dueWhen the due date and time of the deadline task
     */
    public Deadline(String description, String priority, LocalDateTime dueWhen) {
        super(description, priority);
        this.dueWhen = dueWhen;
    }

    /**
     * Returns the string representation of the deadline task for display purposes.
     * The format includes the status, description, and due date/time.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return this.getPriorityIcon() + " 📆 | " + this.getStatusIcon() + " | " + this.description + " (by: " + this.dueWhen.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the deadline task suitable for saving to a file.
     * The format is machine-readable, including the status, description, and due date/time.
     *
     * @return the string representation of the deadline task for file storage
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return this.priority + " | D | " + this.getStatusIcon() + " | " + this.description + " | " + this.dueWhen.format(formatter);
    }
}
