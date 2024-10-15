package sirpotato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description, a from date, and to date
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task with a description, start, and end date
     *
     * @param description The task description
     * @param from The start date of the event
     * @param to The end date of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    public LocalDate getToDate() {
        return to;
    }

    public LocalDate getFromDate() {
        return from;
    }

}
