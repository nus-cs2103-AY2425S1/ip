package kafka;

import java.time.LocalDateTime;

public class Deadline extends Task {

    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public void snooze(LocalDateTime newBy) {
        this.by = newBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + LocalDateTimeConverter.getDifferentFormat(this.by) + ")";
    }
}
