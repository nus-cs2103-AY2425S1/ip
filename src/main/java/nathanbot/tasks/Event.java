package nathanbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its type, description, start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[E]" + super.toString()
            + " (from: " + from.format(dateTimeFormatter) + " to: " + to.format(dateTimeFormatter) + ")";
    }
}