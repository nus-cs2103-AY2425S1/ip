package skd.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Creates an Event task with the description, start time and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event in  "yyyy-MM-dd HHmm" format.
     * @param to End time of the event in  "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    /**
     * Creates an Event task with the description, start time ,end time,
     * and status.
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to End time of the event in the format "yyyy-MM-dd HHmm".
     * @param isDone Status of the event(Done or not)
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the type of Event.
     *
     * @return Type of event.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event,its type, status,
     * description, start time, and end time.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}