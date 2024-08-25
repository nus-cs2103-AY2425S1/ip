package duck.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task implements Datable {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.from = start;
        this.to = end;
    }

    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.from = start;
        this.to = end;
    }

    public boolean isEndingAfter(LocalDate date) {
        return !to.toLocalDate().isBefore(date);
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat()
                + " | " + getFileDateString(from)
                + " | " + getFileDateString(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + getDisplayDateString(from) + " "
                + "to: " + getDisplayDateString(to) + ")";
    }
}
