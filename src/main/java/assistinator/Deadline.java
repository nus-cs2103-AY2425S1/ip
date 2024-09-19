package assistinator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Initialises a deadline task
     * @param description Task description.
     * @param by Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        return String.format("D | %s | %s | %s", isDone ? TaskStatus.DONE : TaskStatus.NOTDONE, description, by.format(formatter));
    }
}
