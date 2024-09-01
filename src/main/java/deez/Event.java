package deez;

import java.time.LocalDateTime;

/**
 * Extends the Task class with event-specific properties and behavior.
 */
public class Event extends Task {
    private static final long serialVersionUID = 1L;

    /**
     * The start date of the event.
     */
    protected LocalDateTime eventStartDate;

    /**
     * The end date of the event.
     */
    protected LocalDateTime eventEndDate;

    /**
     * Constructs a new event with the given description, start date, and end date.
     *
     * @param description    A brief summary of the event.
     * @param eventStartDate The start date and time of the event.
     * @param eventEndDate   The end date and time of the event.
     */
    public Event(String description, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        super(description);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string that describes the event and its dates in a human-readable format.
     */
    @Override
    public String toString() {
        return "[E]"
            + super.toString()
            + " (from: "
            + this.eventStartDate.format(DEFAULT_DATE_TIME_FORMATTER)
            + ")"
            + " (to"
            + ": "
            + this.eventEndDate.format(DEFAULT_DATE_TIME_FORMATTER)
            + ")";
    }
}
