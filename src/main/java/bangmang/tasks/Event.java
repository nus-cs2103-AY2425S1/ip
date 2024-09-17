package bangmang.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 * Inherits from the Task class and is used for tasks that occur between specific times.
 */

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with a description, completion status, start time, and end time.
     *
     * @param description Description of the event.
     * @param isDone Whether the event is marked as done.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event.
     * Formats the date and time based on the event's duration and the current date.
     *
     * @return A string with the format specific to Event tasks, including the task type and time range.
     */
    @Override
    public String toString() {
        DateTimeFormatter sameYearFullFormatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        DateTimeFormatter diffYearFullFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Format the start and end times based on different conditions
        String fromString = this.from.format(sameYearFullFormatter);
        String toString = this.to.format(sameYearFullFormatter);

        // Different year
        if (LocalDateTime.now().getYear() != this.from.getYear()) {
            fromString = this.from.format(diffYearFullFormatter);
            toString = this.to.format(diffYearFullFormatter);
        } else if (this.from.toLocalDate().equals(this.to.toLocalDate())) {
            // Within the same day and same year
            toString = this.to.format(timeFormatter);
        } else if (this.from.toLocalTime().equals(LocalDateTime.MIN.toLocalTime()) &&
                this.to.toLocalTime().equals(LocalDateTime.MIN.toLocalTime())) {
            // Same year, not same day, but whole day
            fromString = this.from.format(dateFormatter);
            toString = this.to.format(dateFormatter);
        }

        return "[E]" + super.toString() + " | " + fromString + " - " + toString;
    }
}
