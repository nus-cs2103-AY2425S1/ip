package task;
import java.time.LocalDateTime;

/**
 * A simple data carrier class that holds the details of an event.
 *
 * <p>This class is used to transfer event information, including
 * the event's description, start time, and end time, between different
 * parts of the program. The fields are private and final, ensuring
 * that the data is immutable once an instance of the class is created.</p>
 *
 * <p>The class provides getter methods for accessing the event details.</p>
 */
public class EventDetails {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventDetails object with the specified description,
     * start time, and end time.
     *
     * @param description a brief description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public EventDetails(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time of the event
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event
     */
    public LocalDateTime getTo() {
        return to;
    }
}
