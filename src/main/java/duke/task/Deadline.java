package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that needs to be done by a specific date and time.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description, deadline date/time, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string in the format "yyyy-MM-dd HHmm".
     * @param isDone The completion status of the task. True if the task is done, false otherwise.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Constructs a Deadline task with a description and deadline date/time. The task is initially not done.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for saving to a file.
     *
     * @return A string representing the deadline task in the format "D | isDone | description | by".
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Returns a string representation of the deadline task for display purposes.
     *
     * @return A string representing the deadline task in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public LocalDateTime getDate() {
        return by;
    }
}
