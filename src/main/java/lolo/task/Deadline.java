package lolo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the Lolo application.
 * A Deadline is a specific type of task that has a due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by The due date and time of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes a prefix "[D]", the status icon, the description, and the due date formatted as "MMM dd yyyy, h:mm a".
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for saving to a file.
     * The string includes the task type, status, description, and due date formatted as "yyyy-MM-dd HHmm".
     *
     * @return The data string representation of the Deadline task.
     */
    @Override
    public String toDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
