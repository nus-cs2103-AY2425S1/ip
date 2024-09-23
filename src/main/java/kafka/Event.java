package kafka;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event extends Task {

    public LocalDateTime from;
    public LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public void snooze(LocalDateTime newTime) {
        Duration difference = Duration.between(this.from, this.to);
        this.from = newTime;
        this.to = newTime.plus(difference);
    }

    public void snooze(LocalDateTime newFrom, LocalDateTime newTo) {
        this.from = newFrom;
        this.to = newTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + LocalDateTimeConverter.getDifferentFormat(this.from)
                + " to: " + LocalDateTimeConverter.getDifferentFormat(this.to) + ")";
    }
}
