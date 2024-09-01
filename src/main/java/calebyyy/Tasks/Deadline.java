package calebyyy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    /**
     * Returns the string representation of the deadline task in save format.
     *
     * @return The string representation of the deadline task in save format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D" + super.toSaveFormat() + " | " + by.format(inputFormatter);
    }
}
