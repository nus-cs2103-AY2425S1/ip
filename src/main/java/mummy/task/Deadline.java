package mummy.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private final String dueBy;

    /**
     * Creates a new Deadline task with the given description and due date.
     * isDone is set to false by default.
     *
     * @param description The description of the Deadline task.
     * @param dueBy The due date of the Deadline task.
     */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Creates a new Deadline task with the given description, completion status, and due date.
     *
     * @param description The description of the Deadline task.
     * @param isDone      The completion status of the Deadline task.
     * @param dueBy       The due date of the Deadline task.
     */
    public Deadline(String description, boolean isDone, String dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toFileRecord() {
        return String.format("D | %d | %s | %s", this.isDone() ? 1 : 0,
                this.getDescription(), this.getDueBy());
    }

    /**
     * Returns the due date of the task.
     *
     * @return the due date of the task
     */
    public String getDueBy() {
        return this.dueBy;
    }

    /**
     * Returns the due date of the task as a LocalDate object.
     * @return The due date of the task as a LocalDate object, or null if the due date is not in a valid format.
     */
    public LocalDate getDueByLocalDate() {
        try {
            return LocalDate.parse(this.getDueBy());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDueBy());
    }
}
