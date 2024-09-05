package niko.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import niko.common.DateTimeParser;

/**
 * Represents a task with a deadline.
 * The deadline is represented as a LocalDateTime object.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline as a string.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = DateTimeParser.convertDate(by);
        } catch (DateTimeParseException ignore) {
            // Intentionally left blank
        }
        try {
            this.by = DateTimeParser.parseDateTime(by);
        } catch (DateTimeParseException ignore) {
            // Intentionally left blank
        }
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH)) + ")";
    }
}
