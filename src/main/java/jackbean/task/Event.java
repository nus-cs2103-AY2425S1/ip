package jackbean.task;

import java.time.LocalDate;

/**
 * Represents an event task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an event task with a description, a start date, and an end date.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param description The description of the event task.
     * @param from The start date of the event task in the format yyyy-mm-dd.
     * @param to The end date of the event task in the format yyyy-mm-dd.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the date in MMM dd yyyy format.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param date The date to be converted.
     * @return The date in MMM dd yyyy format.
     */
    public String getDateString(LocalDate date) {
        return date.getMonth().toString().substring(0, 1) + date.getMonth().toString().substring(1, 3).toLowerCase()
                + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Returns the start date of the event task.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The start date of the event task.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event task.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The end date of the event task.
     */
    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + getDateString(this.from)
                + " to: " + getDateString(this.to) + ")";
    }
}