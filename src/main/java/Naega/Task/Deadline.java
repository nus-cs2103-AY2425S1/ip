package Naega.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the specified description and due date.
     *
     * @param description the description of the task
     * @param by the due date and time of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task in a user-friendly format.
     * The format includes the task type, description, and due date.
     *
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a format suitable for saving.
     * The format includes the task type, completion status, description, and due date.
     *
     * @return a string representation of the Deadline task in save format
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}