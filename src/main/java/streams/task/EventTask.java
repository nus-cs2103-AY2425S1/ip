package streams.task;

import java.time.LocalDateTime;

/**
 * Represents an event task in the task list.
 */
public class EventTask extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs an EventTask with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startDateTime The start time of the event.
     * @param endDateTime The end time of the event.
     */
    public EventTask(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        assert startDateTime != null : "Start time should not be null";
        assert endDateTime != null : "End time should not be null";
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return startDateTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return endDateTime;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDateTime.format(Task.OUTPUT_FORMATTER)
                + " to: " + endDateTime.format(Task.OUTPUT_FORMATTER) + ")";
    }
}
