package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is an event.
 * An Event task has a description and a specific date and time
 * when it starts and ends.
 */
public class Event extends Task {

    /**
     * Represents an event task with a specific start and end date and time.
     * An Event task has a description, start time, and end time.
     */
    protected LocalDateTime dateStart;
    protected LocalDateTime dateEnd;

    /**
     * Constructs an Event task with the specified description, start date, and end date in string format.
     *
     * @param description The description of the event.
     * @param dateStart   The start date and time of the event in the format "yyyy-MM-dd HH:mm".
     * @param dateEnd     The end date and time of the event in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = LocalDateTime.parse(dateStart.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.dateEnd = LocalDateTime.parse(dateEnd.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Constructs an Event task with the specified description, start date, and end date as LocalDateTime objects.
     *
     * @param description The description of the event.
     * @param dateStart   The start date and time of the event as a LocalDateTime object.
     * @param dateEnd     The end date and time of the event as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime dateStart, LocalDateTime dateEnd) {
        super(description);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    /**
     * Returns the description of the event.
     *
     * @return The trimmed description of the event.
     */
    @Override
    public String getDescription() {
        return description.trim();
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The LocalDateTime object representing the start date and time of the event.
     */
    public LocalDateTime getStartDate() {
        return this.dateStart;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The LocalDateTime object representing the end date and time of the event.
     */
    public LocalDateTime getEndDate() {
        return this.dateEnd;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + dateStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + " to: " + dateEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the task type, completion status, description, and formatted start and end times.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toSaveFormat() {
        return "task.Event | " + (isDone ? "Done" : "Not Done") + " | "
                + description + " | "
                + dateStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"))
                + " | " + dateEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}