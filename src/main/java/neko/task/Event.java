package neko.task;
import java.time.LocalDateTime;

/**
 * The Event class represents a task with a specified start date and time and end date and time.
 * It extends the Task class and adds a start field and an end field for the event, both represented
 * as LocalDateTime objects. This task type is represented by a "[E]" prefix in its string output.
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */

public class Event extends Task {
    private final LocalDateTime start, end;

    /**
     * Constructs a new Event task with the specified name, start time and end time.
     *
     * @param name the name or description of the event
     * @param start the start date and time of the event
     * @param end the end date and time of the event
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Return a string representation of the Event task.
     * The string is prefixed with "[E]" to indicate it's an event,
     * ended with the string representations of its start time
     * followed by its end time.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(dateFormatter)
                + " to: " + end.format(dateFormatter) + ")";
    }

    /**
     * Return a string representation of the Event task's
     * start time and end time in the format: "eee, d MMM uuuu h:mma"
     * separated by a " | ".
     *
     * @return a string representation of the event's start time and end time.
     */
    @Override
    public String getTime() {
        return start.format(dateFormatter) + " | "
                + end.format(dateFormatter);
    }
}
