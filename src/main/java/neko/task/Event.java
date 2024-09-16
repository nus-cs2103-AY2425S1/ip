package neko.task;
import java.time.LocalDate;
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
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String startStr;
    private final String endStr;

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
        this.startStr = start.format(dateTimeFormatter);
        this.endStr = end.format(dateTimeFormatter);
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
        return "[E]" + super.toString()
                + " (from: " + startStr
                + " to: " + endStr + ")";
    }

    /**
     * Return a string representation of the Event task's
     * start time and end time in the format: "eee, d MMM uuuu h:mma"
     * separated by a " | ".
     *
     * @return a string representation of the event's start time and end time.
     */
    @Override
    public String getDateTimeStr() {
        return startStr + " | "
                + endStr;
    }

    /**
     * Checks if the specified date is in the range of the event start and end.
     * @param date The date to check.
     * @return true if there is an overlapping of the event and the input date;
     *         false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        // Check if the date is within the range (inclusive)
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }

    /**
     * Returns a formatted string representing the event schedule for a given date.
     *
     * <p> The event can be in one of three states:</p>
     * <ul>
     *   <li>Starting on the given date: The event starts on the provided date, so the
     *   method will return the start and end times of the event.</li>
     *   <li>Ongoing: The event spans multiple days, and the provided date falls between
     *   the start and end dates, so the method will return that the event is ongoing.</li>
     *   <li>Ending on the given date: The event ends on the provided date, and the method
     *   will return the time at which the event ends.</li>
     * </ul>
     *
     * @param date The specific date to check for event scheduling.
     * @return A string representing the event on the given date. The string will show
     *         whether the event starts, ends, or is ongoing on the provided date. An empty
     *         string is returned if none of the tree conditions is met.
     */
    @Override
    public String getScheduleStr(LocalDate date) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        if (date.isEqual(startDate)) {
            return "Event: " + this.getDescription() + " (from: "
                    + startStr + " to: " + endStr + ")";
        } else if (date.isAfter(startDate) && date.isBefore(endDate)) {
            return "Ongoing event: " + this.getDescription() + " (from: "
                    + startStr + " to: " + endStr + ")";
        } else if (date.isEqual(endDate)) {
            return "Event: " + this.getDescription() + " ends at "
                    + this.end.toLocalTime() + ")";
        }
        return "";
    }
}
