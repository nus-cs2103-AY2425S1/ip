package wenjiebot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import wenjiebot.exceptions.InvalidDateInputException;
import wenjiebot.exceptions.InvalidSnoozeFormatException;

/**
 * The Event class represents an event task in the wenjiebot application.
 * It extends the Task class and includes additional information for the event,
 * specifically the start and end times.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDateTime fromInDateTime;
    protected LocalDateTime toInDateTime;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) throws InvalidDateInputException {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.fromInDateTime = LocalDateTime.parse(from.trim(), formatter);
            this.toInDateTime = LocalDateTime.parse(to.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInputException();
        }
    }
    /**
     * Sets the date and time for the event using the provided string.
     * The input must contain both the start time ("/from") and end time ("/to").
     *
     * @param newDate A string containing the new start and end times in the format "/from d/M/yyyy HHmm /to d/M/yyyy HHmm".
     * @throws InvalidSnoozeFormatException if the input format is invalid or cannot be parsed.
     */
    @Override
    public void setDateTime(String newDate) throws InvalidSnoozeFormatException {
        int fromIndex = newDate.indexOf("/from") + 6;
        int toIndex = newDate.indexOf("/to") + 4;

        String fromDateTime = newDate.substring(fromIndex, toIndex - 4).trim();
        String toDateTime = newDate.substring(toIndex).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.fromInDateTime = LocalDateTime.parse(fromDateTime, formatter);
            this.toInDateTime = LocalDateTime.parse(toDateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidSnoozeFormatException();
        }
    }

    /**
     * Returns a string representation of the Event in a format suitable for storage.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for storage.
     */
    @Override
    public String toPrettierString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E" + super.toPrettierString() + " /from " + fromInDateTime.format(formatter)
                + " /to " + toInDateTime.format(formatter);
    }

    /**
     * Returns a string representation of the Event for display purposes.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for display.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm");
        return "[E]" + super.toString() + "(from: " + fromInDateTime.format(formatter)
                + " to: " + toInDateTime.format(formatter) + ")";
    }
}
