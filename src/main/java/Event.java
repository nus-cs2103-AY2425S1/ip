import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class represents an Event. It is child of the Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Event extends Task{
    private static final String DATE_TIME_INPUT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_OUTPUT_FORMATTER_PATTERN = "d LLLL yyyy HH:mm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.
            ofPattern(DATE_TIME_INPUT_FORMATTER_PATTERN);
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.
            ofPattern(DATE_TIME_OUTPUT_FORMATTER_PATTERN);
    private LocalDateTime from;
    private LocalDateTime to;

    /** Constructor for an Event object. Sets name upon creation.
     * @param name Name of Event object.
     * @param from A string indicating starting date and time of event.
     * @param to A string indicating end date and time of event.
     */
    public Event(String name, String from, String to) {
        super(name, TASK_TYPE.EVENT);
        try {
            this.from = LocalDateTime.parse(from.strip(), dateTimeInputFormatter);
            this.to = LocalDateTime.parse(to.strip(), dateTimeInputFormatter);
        } catch (Exception e) {
            throw new WrongDateFormatException();
        }
    }

    /**
     * Returns a string containing time the event starts.
     * @return String A string containing time the event starts.
     */
    public String getFromTime() {
        return this.from.format(dateTimeInputFormatter);
    }

    /**
     * Returns a string containing time the event ends.
     * @return String A string containing time the event ends.
     */
    public String getToTime() {
        return this.to.format(dateTimeInputFormatter);
    }

    /** Returns string representation of the Event.
     * @return String containing indication of Event class, and string representation of parent Task class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(dateTimeOutputFormatter).replace(" GMT", "")
                + " to: " +
                to.format(dateTimeOutputFormatter).replace(" GMT", "") + ")";
    }
}
