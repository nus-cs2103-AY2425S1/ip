package hypebot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event type Task with a LocalDateTime type start time and an end time.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Event extends Task {
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter UI_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an Event task with the specified name, start time, and end time.
     *
     * @param name The name of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Takes in a LocalDate object representing a search date
     * and returns whether the Event is happening on the given date.
     *
     * @param date LocalDate object representing a date.
     * @return Whether the Event is happening on the given date.
     */
    @Override
    public boolean isHappeningOn(LocalDate date) {
        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        return (date.isEqual(startDate) || date.isAfter(startDate))
                && (date.isEqual(endDate) || date.isBefore(endDate));
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "E , {0 if not complete, 1 if complete} , {name} , {startTime} , {endTime}".
     *
     * @return String description of Event task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "E , " + (isComplete() ? 1 : 0) + " , " + getName() + " , "
                + startTime.format(FILE_FORMATTER) + " , " + endTime.format(FILE_FORMATTER) + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[E][{X only if complete}] {name} (from: {startTime} to: {endTime})".
     *
     * @return String representation of Event task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(UI_FORMATTER) + " to: "
                + endTime.format(UI_FORMATTER) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            return super.equals(event) && this.startTime.equals(event.startTime) && this.endTime.equals(event.endTime);
        }
        return false;
    }
}
