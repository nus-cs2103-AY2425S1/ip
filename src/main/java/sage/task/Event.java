package sage.task;

import sage.exception.SageException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class represents a task that occurs during a specific period of time
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event task with the given description and period of time
     *
     * @param description The description of the event
     * @param from The data and time the event starts
     * @param to The date and time the event ends
     * @throws SageException if the description is empty or if the time format is inavlid
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws SageException {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(fileFormatter) + " | " + to.format(fileFormatter);
    }
}
