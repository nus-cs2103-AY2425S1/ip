package charlotte.task;

import charlotte.exception.CharlotteException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that spans a period of time, with a start and end date.
 * This task contains a description, a start date, and an end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in the format "yyyy-MM-dd".
     * @param to The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String from, String to) throws CharlotteException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.from = LocalDate.parse(from, formatter);
            this.to = LocalDate.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new CharlotteException("Invalid date format! Use yyyy-MM-dd instead");
        }

    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event as a LocalDate.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event as a LocalDate.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Converts the event task to a format suitable for saving to a file.
     *
     * @return A string representing the event task in a file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " to " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a string representation of the event task.
     * The string includes the task type, status icon, description, start date, and end date.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
