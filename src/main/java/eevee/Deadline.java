package eevee;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of eevee.Task that has a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a eevee.Deadline eevee.Task with the given description and deadline.
     *
     * @param description The String description of the task.
     * @param by The eevee.Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description.trim());

        String dueDate = by.trim();
        try {
            LocalDate date = LocalDate.parse(dueDate);
            dueDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeException ignored) {

        }
        this.by = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + by;
    }
}

