package johncena.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import johncena.exceptions.CenaInvalidAfterException;

/**
 * Represents an after task.
 * An After object corresponds to a task represented by a description and a time after which it should be done.
 */
public class After extends Task {
    protected LocalDateTime after;

    /**
     * Constructor for After class.
     *
     * @param description The description of the task.
     * @param after The time after which the task should be done.
     *              The after should be in the format yyyy-MM-dd HHmm.
     * @throws CenaInvalidAfterException if the after format is incorrect
     */
    public After(String description, String after) throws CenaInvalidAfterException {
        super(description);
        assert description != null : "Description should not be null";
        assert after != null : "After time should not be null";
        try {
            this.after = LocalDateTime.parse(after, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new CenaInvalidAfterException("Incorrect format for after time. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the time after which the task should be done.
     *
     * @return After time of the task.
     */
    public String getAfter() {
        return this.after.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + this.getAfter() + ")";
    }
}
