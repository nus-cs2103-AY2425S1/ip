package qwerty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates an Event type task.
 * An Event starts at a specific time and ends at a specific time.
 */
public class Event extends DeadlineTask {

    /** The time at which the event ends */
    private final LocalDateTime to;

    /**
     * Creates a new Event instance.
     *
     * @param description String description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, from);
        this.to = to;
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
                + getDeadlineAsString() + " to: " + getEventEndTime() + ")";
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "E",
                getStatusIcon(),
                getDescription(),
                getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
        );
    }
}
