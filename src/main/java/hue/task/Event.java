package hue.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import hue.DateUtils;
import hue.HueException;

/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an {@code Event} task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event in the format "yyyy-MM-dd HH:mm".
     * @param to          The end date and time of the event in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String from, String to) throws HueException{
        super(description);
        try {
            this.from = DateUtils.parseDateTime(from);
            this.to = DateUtils.parseDateTime(to);
        } catch (DateTimeParseException e) {
            throw new HueException("Invalid Date Format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A string representing the task in file format.
     */
    @Override
    public String toFileFormat() {
        String task = "E";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " +
                this.from.format(DateUtils.DATE_TIME_FORMATTER)
                + " | "
                + this.to.format(DateUtils.DATE_TIME_FORMATTER);
    }

    /**
     * Reschedules the event task to new start and end dates.
     *
     * @param newDate The new start and end dates, separated by a space.
     * @throws HueException If the date format is invalid or both dates are not provided.
     */
    @Override
    public void reschedule(String newDate) throws HueException {
        throw new HueException("Cannot rescheudle an Event without 2 dates!");

    }

    public void rescheduleEvent(String newFrom, String newTo) throws HueException {
        try {
            String[] fromParts = newFrom.split(" ");
            String[] toParts = newTo.split(" ");
            if ((fromParts.length < 0 || fromParts.length > 2)
            || (toParts.length < 0 || toParts.length > 2)) {
                throw new HueException("Please provide both from and to dates");
            }
            this.from = DateUtils.parseDateTime(newFrom);
            this.to = DateUtils.parseDateTime(newTo);
        } catch (DateTimeParseException e) {
            throw new HueException("Invalid Date format");
        }
    }
}