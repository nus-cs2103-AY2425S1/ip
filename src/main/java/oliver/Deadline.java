package oliver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be completed by a certain deadline
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the formatted string to be stored in the data file.
     *
     * @return formatted string representing the deadline task
     */
    @Override
    public String getFormatted() {
        return "D|" + super.getStatusIcon() + "|" + super.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + ")";
    }
}
