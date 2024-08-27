import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean done) {
        super(description, done);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | from: " + this.from.format(formatter) +
                                              " to: " + this.to.format(formatter);
    }
}
