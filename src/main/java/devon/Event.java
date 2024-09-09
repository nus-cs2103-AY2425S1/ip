package devon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an "Event" task in the Devon application.
 * An "Event" task has a description and a time range indicating the start and end times of the event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new "Event" task with the specified description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description A brief description of the "Event" task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Provides a string representation of the "Event" task in a format suitable for database storage.
     *
     * @return A string representing the "Event" task in a format suitable for database storage,
     *         with the format "Event|&lt;status&gt;|&lt;description&gt;|&lt;from&gt;|&lt;to&gt;",
     *         where &lt;status&gt; is 1 if done, 0 if not done, &lt;from&gt; is the start time of the event,
     *         and &lt;to&gt; is the end time of the event.
     */
    @Override
    public String dbReadableFormat() {
        return String.format("Event|%d|%s|%s|%s", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }

    /**
     * Converts the start date and time of the event to a formatted string.
     *
     * @return A string representing the start date and time of the event in the format "MMM d yyyy, h:mm a".
     */
    private String fromToString() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Converts the end date and time of the event to a formatted string.
     *
     * @return A string representing the end date and time of the event in the format "MMM d yyyy, h:mm a".
     */
    private String toToString() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * Returns a string representation of the "Event" task for display purposes.
     *
     * @return A string representing the "Event" task, including its status icon, description, start time, and end time,
     *         with the format "[E][status] description (from: start_time to: end_time)",
     *         where [status] is "X" if done, " " if not done.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromToString() + " to: " + toToString() + ")";
    }
}
