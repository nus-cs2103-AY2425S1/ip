package moody.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date and time.
 * An Event is a type of Task that includes a range of time during which the event occurs.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event with the specified description, start time, and end time.
     *
     * @param description The description of the Event.
     * @param from The start date and time of the Event.
     * @param to The end date and time of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event to a format suitable for saving to a file.
     * The format includes the task type, description, start time, and end time in a specific format.
     *
     * @return A string representation of the Event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.from.format(INPUT_FORMATTER) + " | "
                + this.to.format(INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the Event for display purposes.
     * The format includes the task type, description, start time, and end time in a user-friendly format.
     *
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        String timeframe = String.format("(from: %s to: %s)", this.from.format(OUTPUT_FORMATTER),
                this.to.format(OUTPUT_FORMATTER));
        return String.format("[E]%s %s", super.toString(), timeframe);
    }
}
