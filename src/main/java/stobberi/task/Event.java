package stobberi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import stobberi.stobberiexception.NotPossibleDurationStobberiException;

/**
 * The Event class represents a task that occurs within a specific time range.
 * It stores the start and end times of the event and ensures that the start time is before the end time.
 */
public class Event extends Task {
    private LocalDateTime startOfEvent;
    private LocalDateTime endOfEvent;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description   The description of the event.
     * @param startOfEvent  The start time of the event in the format "dd-MM-yyyy HHmm'hrs'".
     * @param endOfEvent    The end time of the event in the format "dd-MM-yyyy HHmm'hrs'".
     * @throws NotPossibleDurationStobberiException If the start time is after the end time.
     */
    public Event(String description,
                 String startOfEvent,
                 String endOfEvent) throws NotPossibleDurationStobberiException {
        super(description);
        this.startOfEvent = LocalDateTime.parse(startOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        this.endOfEvent = LocalDateTime.parse(endOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        if (this.startOfEvent.isAfter(this.endOfEvent)) {
            throw new NotPossibleDurationStobberiException("Not possible! Start time should be BEFORE end time.");
        }
    }

    /**
     * Constructs an Event with the specified description, start time, end time, and completion status.
     *
     * @param description   The description of the event.
     * @param startOfEvent  The start time of the event in the format "dd-MM-yyyy HHmm'hrs'".
     * @param endOfEvent    The end time of the event in the format "dd-MM-yyyy HHmm'hrs'".
     * @param done          The completion status of the event ("1" if done, otherwise not done).
     * @throws NotPossibleDurationStobberiException If the start time is after the end time.
     */
    public Event(String description,
                 String startOfEvent,
                 String endOfEvent,
                 String done) throws NotPossibleDurationStobberiException {
        super(description);
        this.startOfEvent = LocalDateTime.parse(startOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        this.endOfEvent = LocalDateTime.parse(endOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        if (done.equals("1")) {
            super.setDone();
        }
        if (this.startOfEvent.isAfter(this.endOfEvent)) {
            throw new NotPossibleDurationStobberiException("Not possible! Start should be BEFORE end time.");
        }
    }

    /**
     * Checks if the event occurs during the specified date.
     *
     * @param date The date to check in the format "dd-MM-yyyy".
     * @return true if the event occurs during the specified date, false otherwise.
     */
    public boolean isDuring(String date) {
        LocalDateTime start = LocalDateTime.parse(date + " 0000hrs",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        LocalDateTime end = LocalDateTime.parse(date + " 2359hrs", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));

        return (endOfEvent.isAfter(start) || endOfEvent.isEqual(start))
                && (startOfEvent.isBefore(end) || startOfEvent.isEqual(end));
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event as a formatted string.
     */
    public String getStartOfEvent() {
        return startOfEvent.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event as a formatted string.
     */
    public String getEndOfEvent() {
        return endOfEvent.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event including its description, start, and end times.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString()
                + " (from: " + startOfEvent.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"))
                + " to: " + endOfEvent.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma")) + ")";
    }
}
