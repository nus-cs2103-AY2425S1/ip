package snowy.tasklist;

import snowy.data.SnowyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task that has a specific time frame.
 *
 * The Event class extends the Task class and adds the start and end times of the event.
 * Event has a description, a start time ("from") and an end time("to").
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format "yyyy-MM-dd HHmm"
     * @param to the end time of the event in the format "yyyy-MM-dd HHmm"
     * @throws SnowyException if the start time is not before the end time
     */
    public Event(String description, String from, String to) throws SnowyException {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        if (!this.from.isBefore(this.to)) {
            throw new SnowyException("The 'from' date must be before the 'to' date.");
        }
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")" + this.getTags();
    }
}
