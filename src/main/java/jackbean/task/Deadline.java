package jackbean.task;

import java.time.LocalDate;

/**
 * Represents a deadline task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline task with a description and a deadline.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task in the format yyyy-mm-dd.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
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
     * Returns the deadline of the deadline task.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The deadline of the deadline task.
     */
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + getDateString(this.by) + ")";
    }
}