package ahmad.processor.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

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
