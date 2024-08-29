package skibidi.task;

import java.time.LocalDate;

public class Event extends AbstractTask {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String marker, String description, LocalDate from, LocalDate to) {
        super(marker, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }

    public String serialize() {
        return String.join("|", new String[]{"E", getStatusIcon(), description, from.toString(), to.toString()});
    }
}
