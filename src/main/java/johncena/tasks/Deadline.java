package johncena.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import johncena.exceptions.CenaInvalidDeadlineException;

/**
 * Represents a deadline task.
 * A tasks.Deadline object corresponds to a task represented by a description and a deadline date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for tasks.Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     *           The deadline should be in the format yyyy-MM-dd HHmm.
     * @throws CenaInvalidDeadlineException if the deadline format is incorrect
     */
    public Deadline(String description, String by) throws CenaInvalidDeadlineException {
        super(description);
        assert description != null : "Description should not be null";
        assert by != null : "Deadline should not be null";
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new CenaInvalidDeadlineException("Incorrect format for deadline. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the deadline of the task.
     *
     * @return tasks.Deadline of the task.
     */
    public String getBy() {

        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Checks if the task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the given date, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}
