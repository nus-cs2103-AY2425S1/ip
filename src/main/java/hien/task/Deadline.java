package hien.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that includes a description and a deadline date/time.
 * Inherits from the {@code Task} class and adds a deadline date/time to the task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline date/time.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date and time of the task.
     *
     * @return The deadline date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the status icon, description, and the formatted deadline date/time.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_DATE_FORMAT) + ")";
    }
}