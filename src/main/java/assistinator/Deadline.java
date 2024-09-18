package assistinator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialises a deadline task
     * @param description Task description.
     * @param by Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        return String.format("D | %s | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description, by);
    }
}
