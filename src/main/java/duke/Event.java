package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String _description, LocalDateTime _from, LocalDateTime _to) {
        super(_description);
        this.from = _from;
        this.to = _to;

    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public String saveTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public String saveFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + this.saveFrom() + " | " + this.saveTo() + "\n";
    }
}
