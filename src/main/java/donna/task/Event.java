package donna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import donna.DonnaException;

/**
 * Represents an Event task which includes a description, start time, and end time.
 */
public class Event extends Task {
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;
    private final String description;
    private final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param fromTime The start time of the event in the format "dd/MM/yyyy HHmm".
     * @param toTime The end time of the event in the format "dd/MM/yyyy HHmm".
     * @throws DonnaException If the start or end time is empty, or if the date and time format is invalid.
     */
    public Event(String description, String fromTime, String toTime) throws DonnaException {
        super(description);

        assert fromTime != null : "Event start time should not be null";
        assert toTime != null : "Event end time should not be null";

        this.description = description;

        if (fromTime.trim().isEmpty() && toTime.trim().isEmpty()) {
            throw DonnaException.emptyEventTime();
        }
        try {
            this.fromTime = LocalDateTime.parse(fromTime, inputFormatter);
            this.toTime = LocalDateTime.parse(toTime, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DonnaException(
                    "Invalid date and time format! Please use dd/MM/yyyy HHmm (24hr format)"
                    + "\n" + "Use this format for specifying the date and time for both,"
                    + "\n" + "/from and /to");
        }
    }

    /**
     * Returns a string representation of the event in a format suitable for saving to a file.
     *
     * @return A string representing the event in the format "E | mark status | description | from | to".
     */
    @Override
    public String toFileFormat() {
        String fromFormatted = fromTime.format(inputFormatter);
        String toFormatted = toTime.format(inputFormatter);
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.description + " | " + fromFormatted + " | "
                + toFormatted;
    }

    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a string representation of the event task.
     * Includes the description, status, and time range.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        String fromFormatted = fromTime.format(outputFormatter);
        String toFormatted = toTime.format(outputFormatter);
        return super.toString() //type, status & desc
                + "(from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}
