package meow;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * An event task has a description, a start date, and an end date.
 */
public class Event extends Task implements Serializable {
    protected LocalDate from;
    protected LocalDate to;


    /**
     * Constructs an Event object with the given description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date as a LocalDate object.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date as a LocalDate object.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the string representation of the Event task, including its type,
     * description, and formatted start and end date as "MMM dd yyyy".
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() +
                " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
