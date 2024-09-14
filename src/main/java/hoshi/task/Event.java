package hoshi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an Event with a description, completion status, start date and end date.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of the event, whether the event has been completed or not and its start and end time</p>
 */
public class Event extends Task {

    /**
     * Indicates when the event is due to start.
     */
    private final LocalDate startDate;

    /**
     * Indicates when the event is due to end.
     */
    private final LocalDate endDate;

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Event.
     * @param startDate LocalDateTime start date of Event.
     * @param endDate LocalDateTime end date of Event.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Event.
     * @param isDone Boolean indicating whether Event is done or not.
     * @param startDate LocalDateTime start date of Event.
     * @param endDate LocalDateTime end date of Event.
     */
    public Event(String description, Boolean isDone, LocalDate startDate, LocalDate endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Gets start date of the Event.
     *
     * @return description startDate which indicates when the event is starting.
     */
    public LocalDate getStartTime() {
        return this.startDate;
    }

    /**
     * Gets end date of the Event.
     *
     * @return description endDate which indicates when the event is ending.
     */
    public LocalDate getEndTime() {
        return this.endDate;
    }

    /**
     * Returns a string representation of the Event.
     *
     * <p>The string includes the E identifier, status icon and the description of the Event.</p>
     *
     * @return string in format," "[E]" + [statusIcon] description + " (from: " + startDate + " to: " + endDate + " ) ".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " )";
    }
}
