package charlotte.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import charlotte.exception.CharlotteException;

/**
 * Represents a task that has a deadline.
 * This task contains a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) throws CharlotteException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.by = LocalDate.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new CharlotteException("Invalid date format! Use yyyy-MM-dd instead");
        }
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date of the task as a LocalDate.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Converts the deadline task to a suitable format for saving to a file.
     *
     * @return A string representing the deadline task in a file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the task type, status icon, description, and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
