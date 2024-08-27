package qwerty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates an Event type task.
 * An Event starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    /** The time at which the event starts */
    private final LocalDateTime from;
    /** The time at which the event ends */
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event start time as a formatted string.
     * The format is "MMM dd yyyy HHmm", e.g. "Aug 26 2024 1450".
     *
     * @return Formatted string representing the event start time.
     */
    public String getEventStartTime() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Returns the event end time as a formatted string.
     * The format is "MMM dd yyyy HHmm", e.g. "Aug 26 2024 1450".
     *
     * @return Formatted string representing the event end time.
     */
    public String getEventEndTime() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + getEventStartTime() + " to: " + getEventEndTime() + ")";
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "E",
                getStatusIcon(),
                getDescription(),
                from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
        );
    }
}
