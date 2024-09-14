package ynch.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that extends the Task class.
 */
class Event extends Task {
    LocalDate from;
    LocalDate to;
    static DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param task  the description of the event task
     * @param from  the start date of the event
     * @param to    the end date of the event
     */
    Event(String task, String from, String to) {
        super(task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(to);
    }

    /**
     * Constructs a new Event task with the specified status, description, start date, and end date.
     *
     * @param status the completion status of the event task (1 for done, 0 for not done)
     * @param task   the description of the event task
     * @param from   the start date of the event
     * @param to     the end date of the event
     */
    Event(int status, String task, String from, String to) {
        super(status, task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(to);
    }

    /**
     * Determines whether a reminder is needed based on the current date
     *
     * The method compares the current date with a date calculated by
     * subtracting a predefined number of days (defined by
     * {@code DAYS_FOR_REMINDER}) from the start of the event.
     * If the current date is after the calculated reminder date,
     * the method returns {@code true}, indicating that a reminder is needed.
     * Otherwise, it returns {@code false}.
     *
     * @return {@code true} if the current date is after the date to remind;
     *         {@code false} otherwise.
     */
    @Override
    boolean needsReminder() {
        LocalDate today = LocalDate.now();
        LocalDate dateToRemind = this.from.minusDays(DAYS_FOR_REMINDER);
        return today.isAfter(dateToRemind);
    }

    /**
     * Returns a string representation of the Event task, including its completion status and date range.
     *
     * @return a string indicating the type of task, its status, and the date range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + 
            " (from: " + this.from.format(DATEFORMAT) + " to: " + this.to.format(DATEFORMAT) + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a file.
     *
     * @return a string representing the type of task, its status, description, and date range
     */
    @Override
    String toSaveAsString() {
        return "E" + super.toSaveAsString() + "/" + this.from.toString() + "/" + this.to.toString();
    }
}