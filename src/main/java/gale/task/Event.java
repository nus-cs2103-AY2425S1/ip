package gale.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import gale.parser.Parser;

/**
 * Represents an event task that has a starting and ending date and time.
 *
 * @author kaikquah
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Constructs an event task with the given description, starting and ending date and time.
     *
     * @param description the description of the event
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Parser.parseDateTime(from);
        this.to = Parser.parseDateTime(to);
    }

    /**
     * Returns the event task as a String.
     *
     * @return the event task as a String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(outputFormatter)
                + " to: " + this.to.format(outputFormatter) + ")";
    }

    /**
     * Returns the event task as a String to be written to a file.
     *
     * @return the event task as a String to be written to a file
     */
    @Override
    public String toFileString() {
        int status = super.status() ? 1 : 0;
        String fromInFileFormat = this.from.format(Parser.getFormatters().get(0));
        String toInFileFormat = this.to.format(Parser.getFormatters().get(0));
        return String.format("E | %d | %s | %s | %s", status, getDescription(),
                fromInFileFormat,
                toInFileFormat);
    }
}
