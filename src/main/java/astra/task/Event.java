package astra.task;

import astra.AstraException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for Event.
     *
     * @param name The name of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws AstraException If the name is empty.
     */
    public Event(String name, String from, String to) throws AstraException {
        super(name);
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);
    }

    /**
     * Constructor for Event.
     *
     * @param done Whether the event is done.
     * @param name The name of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws AstraException If the name is empty.
     */
    public Event(boolean done, String name, String from, String to) throws AstraException {
        super(done, name);
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);
    }

    @Override
    public String toText() {
        return String.format("E | %s | %s | %s",
                super.toText(),
                from.format(inputFormatter),
                to.format(inputFormatter));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.format(outputFormatter), to.format(outputFormatter));
    }
}
