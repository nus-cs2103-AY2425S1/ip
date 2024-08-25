import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class includes the name of the task and a deadline for the task to be completed by.
 * Subclass of Task class.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy ha");
    protected LocalDateTime by;

    /**
     * Constructor for Deadline instance.
     *
     * @param description Name of deadline task.
     * @param by Deadline for task.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.Deadline);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.Deadline);
        this.by = by;
    }

    public String getBy() {
        return by.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Formats Deadline for saving.
     *
     * @return String Formatted details of Deadline.
     */
    @Override
    public String saveDetails() {
        return "D | " + super.saveDetails() + " | " + getBy();
    }
}
