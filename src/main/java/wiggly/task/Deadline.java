package wiggly.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representation of a task with a deadline
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a {@code Deadline} instance containing the description and the due date
     * @param description The description of the {@code Deadline} instance
     * @param by The due date of the {@code Deadline} instance
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return  "D|" + super.toFileFormat() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
