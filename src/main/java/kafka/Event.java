package kafka;

import java.time.LocalDateTime;

public class Event extends Task {

    public final LocalDateTime from;
    public final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + LocalDateTimeConverter.getDifferentFormat(this.from)
                + " to: " + LocalDateTimeConverter.getDifferentFormat(this.to) + ")";
    }
}
