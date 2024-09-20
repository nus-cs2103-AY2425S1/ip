package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The `Deadline` class represents a task with a specific deadline and provides a method to return
 * the deadline in a formatted string.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline class.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string format of deadline.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
            + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

