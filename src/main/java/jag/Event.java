package jag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event Class that extends the Task Class to represent a task of
 * an Event created by the user
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * A custom constructor for the Event Class
     *
     * @param description String representation of the given task
     * @param from instance of LocalDateTime for the events start date
     * @param to instance of LocalDateTime for the events end date
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh mm ss")) + ", to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh mm ss")) + ")";
    }
}
