package samson.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import samson.SamException;

/**
 * The <code> Event </code> class represents a task that occurs at a specific time period.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an <code> Event </code> task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event, in the format "yyyy-MM-dd HHmm".
     * @param to          The end time of the event, in the format "yyyy-MM-dd HHmm".
     * @throws SamException If the provided date format is invalid.
     */
    public Event(String description, String from, String to) throws SamException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            throw new SamException("Invalid date format! Please use yyyy-MM-dd HHmm. \n" +
                    "Example call: event your_event /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
    }

    /**
     * Returns the time in the format "yyyy-MM-dd HHmm".
     *
     * @param time The time to be formatted.
     * @return The time as a formatted string.
     */
    private String getTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    /**
     * Returns the time in a more readable format "MMM dd, yyyy HH:mm".
     *
     * @param time The time to be formatted.
     * @return The time as a readable formatted string.
     */
    private String getMeaningfulTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return time.format(formatter);
    }

    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return The string representation of the task for storage.
     */
    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getDescription() + " | " + this.getTime(from) + " | " + this.getTime(to);
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.getTime(from) + " | " + this.getTime(to);
        }
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.getMeaningfulTime(from) + " to: "
                + this.getMeaningfulTime(to) + ")";
    }
}
