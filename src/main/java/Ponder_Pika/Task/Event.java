package Ponder_Pika.Task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This class represents a specific type of task that has a start time and end time.
 * It extends the {@code Task} class and includes additional information about the
 * start and end time during which the even takes place.
 * The {@code Deadline} class overrides the following methods from the {@code Task} class:
 * saveFullDetails() and toString()
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representing the full details of the event.
     * The format of the returned string is:
     * <pre>
     *   E | [completion status] | [description] | [start time] | [end time]
     * </pre>
     *
     * @return a string representing the full details of the event
     */
    @Override
    public String saveFullDetails() {
        return String.format("E | %b | %s | %s | %s", isDone(), getDescription(),
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Returns a string representation of the event suitable for display.
     * The format of the returned string is:
     * <pre>
     *   [E][description] (from: [start time] to: [end time])
     * </pre>
     *
     * @return a formatted string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
