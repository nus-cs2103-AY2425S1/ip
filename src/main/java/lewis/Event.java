package lewis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class implements an Event, which is a task subtype.
 * Along with supporting a description of an event, it can also
 * contain the start and end date and time of this event.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Private constructor for a Task
     *
     * @param description A string description of the event
     */
    private Event(String description, Task.Status status, LocalDateTime from, LocalDateTime to) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Factory method for instantiating a new event. This should be called
     * when generating a new Event via the EventCommand class using the of method.
     * By default, this event is not done.
     * @param description A string representation of this event
     * @param from The starting date and time of this event
     * @param to The ending date and time of this event
     * @return an event
     */
    public static Event of(String description, LocalDateTime from, LocalDateTime to) {
        return new Event(description, Status.NOT_DONE, from, to);
    }
    /**
     * Factory method for instantiating an event while parsing from a csv file
     * This method should only be called when loading the event from Storage.
     * @param description A string representation of this event
     * @param status The event's completion status
     * @param from The starting date and time of this event
     * @param to The ending date and time of this event
     * @return an event
     */
    protected static Event of(String description, String status,
                             LocalDateTime from, LocalDateTime to) {
        Task.Status newStatus = Task.Status.valueOf(status);
        return new Event(description, newStatus, from, to);
    }

    /**
     * Returns a string description of this event
     * @return a string
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (From: %s To: %s)",
                        from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),
                        to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

    /**
     * Returns a csv representation of this event.
     * @return A string in the form "Event,(task description),(task status),(from),(to)"
     */
    @Override
    protected String toCsv() {
        return "Event,"
                + super.toCsv()
                + ","
                + this.from
                + ","
                + this.to;
    }
}
