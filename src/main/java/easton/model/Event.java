package easton.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import easton.exception.DateTimeFormatException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs a new event task with the specified description, date & time from and to.
     * If the date & time format is wrong, an exception is thrown.
     *
     * @param description Description of the task.
     * @param startDate Date & time the task begins.
     * @param endDate Date & time the task ends.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public Event(String description, String startDate, String endDate) throws DateTimeFormatException {
        super(description);
        try {
            this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "E," + super.getCsvFormat()
                + "," + startDate.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
                + "," + endDate.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm")) + ")";
    }
}
