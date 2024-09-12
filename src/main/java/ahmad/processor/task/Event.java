package ahmad.processor.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Event class that extends from Task.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event instance based on the name, from, and to.
     *
     * @param name Name of the event.
     * @param from From date of event.
     * @param to   To date of event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    private Event(Event a, boolean state) {
        super(a, state);
        this.from = a.from;
        this.to = a.to;
    }

    @Override
    protected long getTime() {
        return this.from.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public Event mark() {
        return new Event(this, true);
    }

    @Override
    public Event unmark() {
        return new Event(this, false);
    }

    @Override
    public String getExtraInformation() {
        return "(from: " + super.formatDate(this.from) + ", to: " + super.formatDate(this.to) + ")";
    }

    @Override
    public String getSymbol() {
        return "[E]";
    }
}
