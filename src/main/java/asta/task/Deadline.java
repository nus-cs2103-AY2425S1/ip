package asta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline. A Deadline task must be completed by a specific date and time. This class
 * extends the Task class and adds a deadline attribute to store the due date.
 */
public class Deadline extends Task {
    final LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and a deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, a deadline date, and a completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The date and time by which the task must be completed.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for saving to a file. The format is "D
     * | {status} | {description} | {by}", where {status} is "1" if the task is done, "0" otherwise.
     *
     * @return A string representing the Deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Returns a string representation of the Deadline task, including its type, status, description, and due date. The
     * format is "[D][{status}] {description} (by: {formatted date})".
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
