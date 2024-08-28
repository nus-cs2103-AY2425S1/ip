import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Formatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * The due date/time of this deadline.
     */
    protected LocalDateTime dueDateTime;

    /**
     * The constructor.
     *
     * @param description Deadline description.
     * @param dueDateTime Due date/time of this deadline.
     * @throws BocchiException If any parameter is empty.
     * @throws DateTimeParseException If the date/time format is invalid.
     */
    public Deadline(String description, String dueDateTime) throws BocchiException, DateTimeParseException {
        super(description);
        if (dueDateTime == null) {
            throw new BocchiException("So..sorry, but you have to specify the due time of a deadline.");
        }
        this.dueDateTime = BocchiDateTimeFormatter.parse(dueDateTime);
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return A string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[DDL]" + super.toString() + " (by: " + BocchiDateTimeFormatter.toString(dueDateTime) + ")";
    }
}
