package deez;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Class representing a deadline task.
 */
public class Deadline extends Task {
    @Serial
    private static final long serialVersionUID = 1L;

    protected LocalDateTime byDate;

    /**
     * Constructor for creating a new deadline task with given description and date.
     *
     * @param description the description of the task
     * @param byDate      the due date of the task
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate.format(defaultDateTimeFormatter) + ")";
    }
}
