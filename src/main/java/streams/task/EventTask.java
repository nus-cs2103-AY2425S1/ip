package streams.task;

import java.time.LocalDateTime;

/**
 * Represents an event task in the task list.
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventTask with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(Task.OUTPUT_FORMATTER)
                + " to: " + to.format(Task.OUTPUT_FORMATTER) + ")";
    }
}
