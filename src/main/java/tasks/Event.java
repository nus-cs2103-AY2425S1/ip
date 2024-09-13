package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs over a specific time period.
 * This class extends the Task class and includes start and end times.
 */

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the specified action, start time, and end time.
     *
     * @param action The description of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */

    public Event(String action, LocalDateTime start, LocalDateTime end) {
        super(action);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with the specified action, completion status, start time, and end time.
     *
     * @param action The description of the task.
     * @param complete The completion status of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */

    public Event(String action, boolean complete, LocalDateTime start, LocalDateTime end) {
        super(action, complete);
        this.start = start;
        this.end = end;
    }

    /**
     * Changes the due date of the deadline.
     *
     * @param newDate The new start date of the task
     */

    public void changeStartDate(LocalDateTime newDate) {
        this.start = newDate;
    }

    /**
     * Changes the due date of the deadline.
     *
     * @param newDate The new end date of the task
     */

    public void changeEndDate(LocalDateTime newDate) {
        this.end = newDate;
    }

    /**
     * Returns the date of the event task (the start date).
     *
     * @return The start date of the event as a LocalDate.
     */

    @Override
    public LocalDate getDate() {
        return this.start.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "E | " + super.toString() + " | " + start.format(dateFormatter)
                + " - " + end.format(dateFormatter);
    }
}
