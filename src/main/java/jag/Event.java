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

    public void update(String description, LocalDateTime ... dateTime) {
        this.description = description;
        this.from = dateTime[0];
        this.to = dateTime[1];
    }

    /**
     * A custom constructor for the Event Class
     *
     * @param description String representation of the given task
     * @param dateTime Varargs of an instance of LocalDateTime for the
     *                 events start and end date
     */
    public Event(String description, LocalDateTime ... dateTime) {
        super(description);
        this.from = dateTime[0];
        this.to = dateTime[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh mm ss")) + ", to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh mm ss")) + ")";
    }
}
