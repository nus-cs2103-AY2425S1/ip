package easton.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import easton.exception.DateTimeFormatException;
/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Constructs a new deadline task with the specified description, date & time by.
     * If the date & time format is wrong, an exception is thrown.
     *
     * @param description Description of the task.
     * @param dueDate Date & time the task is due by.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public Deadline(String description, String dueDate) throws DateTimeFormatException {
        super(description);

        try {
            this.dueDate = LocalDateTime.parse(dueDate, DATE_TIME_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "D," + super.getCsvFormat() + "," + dueDate.format(DATE_TIME_PARSE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DATE_TIME_PRINT_FORMATTER) + ")";
    }
}
