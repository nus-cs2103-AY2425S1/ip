package mittens.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, that is a task having a deadline.
 */
public class Deadline extends Task {
    
    protected LocalDate by;

    /**
     * Creates a Deadline task with the given description and deadline.
     * 
     * @param description The description of the task
     * @param by The deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ")";
    }
}
