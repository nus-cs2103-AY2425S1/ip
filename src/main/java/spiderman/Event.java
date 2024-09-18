package spiderman;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event object with the given description, from and to.
     * @param description The description of the event.
     * @param from The start date & time for the event.
     * @param to The end date & time for the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * Formats the event's description, status, start and end datetime and
     * tags task with an E to be saved into a txt file.
     * @return A string that contains the event's description, status, start and end datetime.
     */
    @Override
    public String toTextFormat() {
        return "E|" + (super.isTaskDone() ? "T" : "F") + "|" +
                super.getDescription() + "|" + this.from + "|" + this.to;
    }

    /**
     * Converts event description, status, start and end datetime to a string and
     * also tags the task with an E.
     * @return A string containing the event's description, current status, start and end datetime.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) +
                " to: " +
                to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
