package tira.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A child class of Task.
 * Manages Task that has a from and to date and time.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Initialises the Event class.
     *
     * @param description The description of event.
     * @param startDate The start date and time of the event.
     * @param endDate The end date and time of the event.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter method to get the start date and time of event in LocalDate object.
     *
     * @return Date and time in LocalDate object.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Getter method to get the end date of an event
     *
     * @return EndDate of the event in LocalDate object.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Prints the Event.
     * @return String of Event isDone, description, StartDate and EndDate.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDate.format(OUT_FORMATTER)
                + " to: " + endDate.format(OUT_FORMATTER)
                + ")";
    }
}
