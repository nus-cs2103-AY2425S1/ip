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

    /**
     * Instantiates a new Event.
     *
     * @param taskName        Event name.
     * @param isDone          Whether event is over.
     * @param unformattedFrom Unformatted start date and time.
     * @param unformattedTo   Unformatted end date and time.
     */
    public Event(String taskName, boolean isDone, String unformattedFrom, String unformattedTo) {
        super(taskName, isDone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        this.unformattedFrom = unformattedFrom;
        this.unformattedTo = unformattedTo;
        this.from = LocalDateTime.parse(unformattedFrom.trim(), dateTimeFormatter);
        this.to = LocalDateTime.parse(unformattedTo.trim(), dateTimeFormatter);
    }

    @Override
    public String toFileString() {
        return String.format("%s|%d|%s|%s|%s", this.getType(), this.getIsDone() ? 1 : 0, this.getTaskName(), unformattedFrom, unformattedTo);
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
