package oliver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a starting time and ending time
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the formatted string to be stored in the data file.
     *
     * @return formatted string representing the event task
     */
    @Override
    public String getFormatted() {
        return "E|" + super.getStatusIcon() + "|" + super.description + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + ")";
    }
}
