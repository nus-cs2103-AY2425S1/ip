package velma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class - deadline task that has a localdatetime deadline
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for deadline
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }

}
