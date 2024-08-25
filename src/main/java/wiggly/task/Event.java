package wiggly.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representation of an event task with a specified start and end date
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an {@code Event} instance containing the description and the start and end date
     * @param description The description of the {@code Event} instance
     * @param from The start date of the {@code Event} instance
     * @param to The end date of the {@code Event} instance
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E|" + super.toFileFormat() + "|" + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
