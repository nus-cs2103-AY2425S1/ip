package sentinel.task;

import java.time.LocalDateTime;

/**
 * Represents an Event task in the Sentinel application.
 * An Event task has a description, a start date, and an end date, defining the period during which the event occurs.
 */
public class Event extends Task {
    protected LocalDateTime endDate;
    protected LocalDateTime startDate;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param startDate The date and time when the event starts.
     * @param endDate The date and time when the event ends.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        assert(startDate.isBefore(endDate));
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the Event task, including the start and end dates.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String listedString() {
        return super.listedString() + " [from: " + super.localDateTimeToString(this.startDate)
                + " | to: " + super.localDateTimeToString(this.endDate) + "]";
    }

    /**
     * Returns the start date of the Event task.
     *
     * @return The start date of the event.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the Event task.
     *
     * @return The end date of the event.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }
}
