package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import puke.exceptions.WrongDateTimeFormatException;

/**
 * Represents an event with a description, completion status, and time range.
 */
public class Event extends Task {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description, completion status, start time, and end time.
     *
     * @param description the description of the event
     * @param isDone true if the event is completed, false otherwise
     * @param from the start time of the event in the format "dd/MM/yyyy HHmm"
     * @param to the end time of the event in the format "dd/MM/yyyy HHmm"
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        if (isDone) {
            markAsDone();
        }
    }

    /**
     * Compares this event to another object for equality.
     *
     * @param obj the object to compare this event to
     * @return true if the object is equal to this event, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        return Objects.equals(from, other.from) && Objects.equals(to, other.to);
    }

    /**
     * Returns a string representation of the event, including its type indicator and time range.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(TIME_FORMATTER) + " to: "
                + to.format(TIME_FORMATTER) + ")";
    }

    /**
     * Converts the event to its file format representation.
     *
     * @return the string representation of the event in file format
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Sets the start time of this event.
     * The provided date string must follow the format "dd/MM/yyyy HHmm".
     *
     * @param from the new start time in the format "dd/MM/yyyy HHmm".
     * @throws WrongDateTimeFormatException if the provided date string is not in the correct format.
     */
    public void setFrom(String from) throws WrongDateTimeFormatException {
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException("dd/MM/yyyy HHmm");
        }
    }

    /**
     * Sets the end time of this event.
     * The provided date string must follow the format "dd/MM/yyyy HHmm".
     *
     * @param to the new end time in the format "dd/MM/yyyy HHmm".
     * @throws WrongDateTimeFormatException if the provided date string is not in the correct format.
     */
    public void setTo(String to) throws WrongDateTimeFormatException {
        try {
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException("dd/MM/yyyy HHmm");
        }
    }
}
