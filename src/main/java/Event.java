import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class represents an Event. It is child of the Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Event extends Task{
    private static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
    private LocalDateTime from;
    private LocalDateTime to;

    /** Constructor for an Event object. Sets name upon creation.
     * @param name Name of Event object.
     * @param from Starting time of event.
     * @param to End time of event.
     */
    public Event(String name, String from, String to) {
        super(name, TASK_TYPE.EVENT);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    /**
     * Returns a string containing time the event starts.
     * @return String A string containing time the event starts.
     */
    public String getFromTime() {
        return this.from.format(formatter);
    }

    /**
     * Returns a string containing time the event ends.
     * @return String A string containing time the event ends.
     */
    public String getToTime() {
        return this.to.format(formatter);
    }

    /** Returns string representation of the Event.
     * @return String containing indication of Event class, and string representation of parent Task class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.RFC_1123_DATE_TIME).replace(" GMT", "")
                + " to: " +
                to.format(DateTimeFormatter.RFC_1123_DATE_TIME).replace(" GMT", "") + ")";
    }
}
