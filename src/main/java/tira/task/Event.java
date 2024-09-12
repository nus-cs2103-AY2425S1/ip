package tira.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child class of Task.
 * Manages Task that has a from and to date and time.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Initialises the Event class.
     * @param description The description of event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Getter method to get the start date and time of event in LocalDate object.
     * @return Date and time in LocalDate object.
     */
    public LocalDate getStartDate() {
        return start;
    }

    /**
     * Getter method to get the end date of an event
     * @return EndDate of the event in LocalDate object.
     */
    public LocalDate getEndDate() {
        return end;
    }

    /**
     * Prints the Event.
     * @return String of Event isDone, description, StartDate and EndDate.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(OUT_FORMATTER)
                + " to: " + end.format(OUT_FORMATTER)
                + ")";
    }
}
