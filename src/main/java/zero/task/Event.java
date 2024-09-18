package zero.task;

import java.time.LocalDateTime;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It is a subclass of {@code Task} and adds two {@code LocalDateTime} fields to store the start (from)
 * and end (to) times.
 */
public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toYear;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param fromDate The starting date and time of the event.
     * @param toYear The ending date and time of the event.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toYear) {
        super(description);
        this.fromDate = fromDate;
        this.toYear = toYear;
    }

    /**
     * Converts the {@code Event} task to a formatted string for saving to a file.
     *
     * @return A string representation of the {@code Event} task for file storage.
     */
    @Override
    public String toFormatted() {
        return "E," + this.isDone() + "," + this.description + "," + this.fromDate + "," + this.toYear + "\n";
    }

    /**
     * Returns a string representation of the {@code Event} task, including its start and end times.
     *
     * @return A string in the format [E][status] description (from: day month year to: day month year).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.getDayOfMonth() + " " + fromDate.getMonth() + " "
                + fromDate.getYear() + " to: " + toYear.getDayOfMonth() + " " + toYear.getMonth() + " " + toYear.getYear() + ")";
    }
}
