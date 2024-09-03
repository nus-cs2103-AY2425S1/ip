package easton.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import easton.DateTimeFormatException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new event task with the specified description, date & time from and to.
     * If the date & time format is wrong, an exception is thrown.
     *
     * @param description Description of the task.
     * @param from Date & time the task begins.
     * @param to Date & time the task ends.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public Event(String description, String from, String to) throws DateTimeFormatException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }
    }

    @Override
    public String getCsvFormat() {
        return "E," + super.getCsvFormat()
                + "," + from.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
                + "," + to.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm")) + ")";
    }
}
