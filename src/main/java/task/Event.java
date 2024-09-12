package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chatbot.Parser;
import task.exceptions.InvalidDurationException;

/**
 * A class representing individual events
 * happening within the interval [from, to].
 *
 * @author celeschai
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Instantiates new Event object.
     *
     * @param name Task name.
     * @param from Start date-time string.
     * @param to End date-time string.
     * @throws DateTimeParseException If date-time string is in incorrect format.
     */
    public Event(String name, String from, String to) throws DateTimeParseException, InvalidDurationException {
        super(name);
        this.from = Parser.parseStringToDateTime(from);
        this.to = Parser.parseStringToDateTime(to);

        if (this.from.isAfter(this.to)) {
            throw new InvalidDurationException();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                Parser.parseDateTimeToString(this.from),
                Parser.parseDateTimeToString(this.to));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event event)) {
            return false;
        }
        return super.equals(event)
                && this.to.equals(event.to)
                && this.from.equals(event.from);
    }
}
