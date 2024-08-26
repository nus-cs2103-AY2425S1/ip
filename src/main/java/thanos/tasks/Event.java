package thanos.tasks;

import static thanos.utility.DateTimeUtility.format;

import java.time.LocalDateTime;

/**
 * Represents a task that occurs between a specified start and end time
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    private final LocalDateTime start;

    /**
     * The end time of the event.
     */
    private final LocalDateTime end;

    /**
     * Constructs an {@code Event} object with the specified description, start time, and end time.
     *
     * @param description the description of the {@code Event}.
     * @param start the start time of the {@code Event}.
     * @param end the end time of the {@code Event}.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the given date falls within the event's duration.
     * <p>
     * This method returns {@code true} if the provided date is equal to the start time,
     * end time, or any time in between; otherwise, it returns {@code false}.
     * </p>
     *
     * @param date the date to check against the event's duration.
     * @return {@code true} if the given date is within the event's duration; {@code false} otherwise.
     */
    @Override
    public boolean checkDate(LocalDateTime date) {
        return date.isEqual(this.start) || date.isEqual(this.end)
                || (date.isAfter(this.start) && date.isBefore(this.end));
    }

    /**
     * Returns a string representation of the {@code Event}.
     *
     * @return a string representation of the {@code Event} in the format
     *         "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), format(this.start), format(this.end));
    }

    /**
     * Returns a string representation of the {@code Event} suitable for saving to a file.
     *
     * @return a string representation of the {@code Event} in the format "E | [status] description | start-end".
     */
    @Override
    public String toFileString() {
        String startEnd = format(this.start) + '-' + format(this.end);
        return String.format("E | %s | %s", super.toFileString(), startEnd);
    }
}
