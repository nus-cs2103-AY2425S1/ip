package yapper.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.exception.YapperException;

/**
 * Represents an event task that occurs within a specific time frame.
 * This class stores the start and end times of the event in addition to the description.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in the format yyyy-MM-dd HHmm.
     * @param to          The end time of the event in the format yyyy-MM-dd HHmm.
     * @throws YapperException If the date and time format is incorrect.
     */
    public Event(String description, String from, String to) throws YapperException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new YapperException("Boss, the date and time format seems off! Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the string representation of the event task for saving to a file.
     *
     * @return A string in the format "E | status | description | start time | end time".
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
            + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
            + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the string representation of the event task for display.
     *
     * @return A string in the format "[E][status] description (from: start time to: end time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
            + " to: "
            + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}

