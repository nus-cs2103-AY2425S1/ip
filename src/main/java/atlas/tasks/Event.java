package atlas.tasks;

import java.time.LocalDateTime;

import atlas.utils.DateTime;

/**
 * Represents an event class containing the methods to create an event and represent it as strings.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates an event object.
     *
     * @param name The name or description of the deadline.
     * @param startTime The date and time to start this event at.
     * @param endTime The date and time to finish this event by.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return String The format in which this event is stored in a file.
     */
    @Override
    public String toFileString() {
        return String.format("E %s | %s to %s", super.toFileString(),
                this.startTime.format(DateTime.DATE_TIME_FILE_OUTPUT_FORMATTER),
                this.endTime.format(DateTime.DATE_TIME_FILE_OUTPUT_FORMATTER));
    }

    /**
     * @return String The format in which this event is shown in the ui.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.startTime.format(DateTime.DATE_TIME_PRINT_OUTPUT_FORMATTER),
                this.endTime.format(DateTime.DATE_TIME_PRINT_OUTPUT_FORMATTER));
    }
}
