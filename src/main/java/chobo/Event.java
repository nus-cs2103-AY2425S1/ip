package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that starts at a specific date/time and ends at a specific date/time.
 * An chobo.Event object corresponds to an event in the Chobo chatbot.
 */
public class Event extends Task {
    private String unformattedFrom;
    private String unformattedTo;
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, boolean done, String unformattedFrom, String unformattedTo) {
        super(name, done);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        this.unformattedFrom = unformattedFrom;
        this.unformattedTo = unformattedTo;
        this.from = LocalDateTime.parse(unformattedFrom.trim(), dateTimeFormatter);
        this.to = LocalDateTime.parse(unformattedTo.trim(), dateTimeFormatter);
    }

    @Override
    public String toFileString() {
        return String.format("%s|%d|%s|%s|%s", this.getType(), this.getIsDone() ? 1 : 0, this.getName(), unformattedFrom, unformattedTo);
    }

    public String getType() {
        return "E";
    }

    /**
     * Returns a string representation of the chobo.Event task, including its type,
     * status, name, start time, and end time.
     *
     * @return A string representing the chobo.Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
