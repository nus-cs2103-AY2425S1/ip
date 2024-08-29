package task;

import chatbot.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A class representing individual events
 * happening within the interval
 * [from, to]
 *
 * @author celeschai
 */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, String from, String to)  throws DateTimeParseException {
        super(name);
        this.from = Parser.parseStringToDateTime(from);
        this.to = Parser.parseStringToDateTime(to);
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
        return super.equals(event) &&
                this.to.equals(event.to) &&
                this.from.equals(event.from);
    }
}
