package futureyou.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that extends the basic Task class.
 * The event has a name, start time, end time, and completion status.
 */
public class Events extends Task {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs an Events task with the specified name, start date, and end date.
     *
     * @param name      The name of the event.
     * @param startDate The start date and time of the event.
     * @param endDate   The end date and time of the event.
     */
    public Events(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    /**
     * Constructs an Events task with the specified name, completion status, start
     * date, and end date.
     *
     * @param name       The name of the event.
     * @param taskStatus The completion status of the event.
     * @param startDate  The start date and time of the event.
     * @param endDate    The end date and time of the event.
     */
    public Events(String name, Boolean taskStatus, LocalDateTime startDate, LocalDateTime endDate) {
        super(name, taskStatus);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    /**
     * Returns the formatted start date and time of the event.
     * The format used is "d MMM yyyy - HH:mm".
     *
     * @return The formatted start date and time as a string.
     */
    public String getStartDate() {
        return this.startDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    /**
     * Returns the formatted end date and time of the event.
     * The format used is "d MMM yyyy - HH:mm".
     *
     * @return The formatted end date and time as a string.
     */
    public String getEndDate() {
        return this.endDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, which is "E" for an event.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a string representation of the event formatted for storage.
     *
     * @return A formatted string representation of the event.
     */
    @Override
    public String toString() {
        // The format is: "E|<done>|<taskName>|<startDate>|<endDate>", where <done> is 1 if completed, 0 otherwise.
        return super.toString() + "|" + this.getStartDate() + "|" + this.getEndDate();
    }

    /**
     * Returns a formatted string representation of the event for display.
     *
     * @return A formatted string for displaying the event.
     */
    @Override
    public String print() {
        // The format is: "[E] [X] <taskName> (from: <startDate> to: <endDate>)", where [X] is marked if completed.
        return super.print() + " (from: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
