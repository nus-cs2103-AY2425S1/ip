package friendlybot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a friendlybot.task.Deadline task.
 * A friendlybot.task.Deadline task is a simple task with a description and a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a new friendlybot.task.Deadline task with the specified description and deadline.
     *
     * @param description The description of the friendlybot.task.Deadline task.
     * @param by The deadline of the friendlybot.task.Deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the friendlybot.task.Deadline task.
     *
     * @return A string in the format "[D][statusIcon] description (by: date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
