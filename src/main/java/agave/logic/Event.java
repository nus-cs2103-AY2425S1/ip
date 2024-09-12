package agave.logic;

import agave.Task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that can be added to the task list.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm");

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_FORMAT);
    }

    @Override
    public String getCorrectFormat() {
        return String.format("%s | %s | %s", getDescription(), start.format(INPUT_FORMAT), end.format(INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return " [E] " + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: "
                + end.format(OUTPUT_FORMAT) + ")";
    }
}
