package easton.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import easton.exception.DateTimeFormatException;
/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a new deadline task with the specified description, date & time by.
     * If the date & time format is wrong, an exception is thrown.
     *
     * @param description Description of the task.
     * @param by Date & time the task is due by.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "D," + super.getCsvFormat() + "," + by.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm")) + ")";
    }
}
