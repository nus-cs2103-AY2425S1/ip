package taskon.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs during a specific time frame.
 * It extends the task.Task class by adding a start and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new task Event with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = getLocalDateTime(start);
        this.end = getLocalDateTime(end);
    }

    /**
     * Returns the start date of the event
     * @return The start date of the event as a LocalDateTime object
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end date of the event
     * @return The end date of the event as a LocalDateTime object
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Checks if the event occurs on the specified date.
     *
     * @param date The date to compare with.
     * @return true if the event occurs on the specified date, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return this.start.toLocalDate().equals(date) || this.end.toLocalDate().equals(date)
                || (date.isAfter(ChronoLocalDate.from(start)) && date.isBefore(ChronoLocalDate.from(end)));
    }

    /**
     * Edits the description, start date or end date of task based on user input.
     *
     * @param description The new description for the task.
     * @param start The new start date for the task.
     * @param end The new end date for the task.
     */
    public void editTask(String description, String start, String end) {
        if (!description.isEmpty()) {
            super.editDescription(description);
        }
        if (!start.isEmpty()) {
            this.start = getLocalDateTime(start);
        }
        if (!end.isEmpty()) {
            this.end = getLocalDateTime(end);
        }
    }

    /**
     * Converts the date from String representation to a LocalDateTime object.
     *
     * @param date The date to convert to a LocalDateTime object from a String.
     * @return LocalDateTime object
     */
    private static LocalDateTime getLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event, including its status icon, description,
     * start time, and end time.
     *
     * @return A string in the format "[E][statusIcon] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}

