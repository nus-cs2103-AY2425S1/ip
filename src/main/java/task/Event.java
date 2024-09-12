package task;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs over a specific period.
 * It supports events defined by either a date range or a date-time range.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    /**
     * Constructs an Event with a name and a date range.
     *
     * @param name The name or description of the event.
     * @param from The start date of the event.
     * @param to   The end date of the event.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with a name, a date range, and a completion status.
     *
     * @param name   The name or description of the event.
     * @param from   The start date of the event.
     * @param to     The end date of the event.
     * @param isDone Whether the event is marked as completed.
     */
    public Event(String name, LocalDate from, LocalDate to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with a name and a date-time range.
     *
     * @param name The name or description of the event.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.fromTime = from;
        this.toTime = to;
    }

    /**
     * Constructs an Event with a name, a date-time range, and a completion status.
     *
     * @param name   The name or description of the event.
     * @param from   The start date and time of the event.
     * @param to     The end date and time of the event.
     * @param isDone Whether the event is marked as completed.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.fromTime = from;
        this.toTime = to;
    }

    /**
     * Constructs an Event with a name, a start date, and an end date-time.
     *
     * @param name The name or description of the event.
     * @param from The start date of the event.
     * @param to   The end date and time of the event.
     */
    public Event(String name, LocalDate from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.toTime = to;
    }

    /**
     * Constructs an Event with a name, a start date, an end date-time, and a completion status.
     *
     * @param name   The name or description of the event.
     * @param from   The start date of the event.
     * @param to     The end date and time of the event.
     * @param isDone Whether the event is marked as completed.
     */
    public Event(String name, LocalDate from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.toTime = to;
    }

    /**
     * Constructs an Event with a name, a start date-time, and an end date.
     *
     * @param name The name or description of the event.
     * @param from The start date and time of the event.
     * @param to   The end date of the event.
     */
    public Event(String name, LocalDateTime from, LocalDate to) {
        super(name);
        this.fromTime = from;
        this.to = to;
    }

    /**
     * Constructs an Event with a name, a start date-time, an end date, and a completion status.
     *
     * @param name   The name or description of the event.
     * @param from   The start date and time of the event.
     * @param to     The end date of the event.
     * @param isDone Whether the event is marked as completed.
     */
    public Event(String name, LocalDateTime from, LocalDate to, boolean isDone) {
        super(name, isDone);
        this.fromTime = from;
        this.to = to;
    }

    /**
     * Formats the start date or date-time for display purposes.
     *
     * @return The formatted start date or date-time as a String.
     */
    private String getDisplayStringFrom() {
        if (this.from != null) {
            return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
    }

    /**
     * Formats the end date or date-time for display purposes.
     *
     * @return The formatted end date or date-time as a String.
     */
    private String getDisplayStringTo() {
        if (this.to != null) {
            return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
    }

    /**
     * Formats the start date or date-time for saving to a file.
     *
     * @return The formatted start date or date-time as a String.
     */
    private String getStringFrom() {
        if (this.from != null) {
            return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.fromTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * Formats the end date or date-time for saving to a file.
     *
     * @return The formatted end date or date-time as a String.
     */
    private String getStringTo() {
        if (this.to != null) {
            return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return this.toTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * Converts the Event task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringFrom = this.getStringFrom();
        String stringTo = this.getStringTo();
        return "E," + done + "," + this.getName() + "," + stringFrom + "," + stringTo;
    }

    /**
     * Returns the string representation of the Event task, including its start and end times.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String stringFrom = this.getDisplayStringFrom();
        String stringTo = this.getDisplayStringTo();
        return "[E] " + super.toString() + " (from: " + stringFrom + " to: " + stringTo + ")";
    }
}
