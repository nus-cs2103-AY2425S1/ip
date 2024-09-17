package stelle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an stelle.task.Event. It is child of the stelle.task.Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Event extends Task {
    private static final String DATE_TIME_INPUT_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_TIME_OUTPUT_FORMATTER_PATTERN = "d LLLL yyyy HH:mm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_INPUT_FORMATTER_PATTERN);
    private static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter
            .ofPattern(DATE_TIME_OUTPUT_FORMATTER_PATTERN);
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an stelle.task.Event object. Sets name upon creation.
     * @param name Name of stelle.task.Event object.
     * @param from Starting date and time of event.
     * @param to End date and time of event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
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

    /**
     * Returns string representation of the stelle.task.Event.
     * @return String containing indication of stelle.task.Event class,
     *      and string representation of parent stelle.task.Task class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(dateTimeOutputFormatter).replace(" GMT", "")
                + " to: "
                + to.format(dateTimeOutputFormatter).replace(" GMT", "") + ")";
    }
}
