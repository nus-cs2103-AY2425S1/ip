package casper;

import java.time.LocalDate;

/**
 * Represents the Event task
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructor for the event task
     * @param description Description of the event
     * @param isDone Represents the completion status of the event
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, "E", isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (from: %s to: %s)", this.formatDate(this.start), this.formatDate(this.end));
        return superString + dueDate;
    }

    public String getStart() {
        return this.start.toString();
    }

    public String getEnd() {
        return this.end.toString();
    }
}
