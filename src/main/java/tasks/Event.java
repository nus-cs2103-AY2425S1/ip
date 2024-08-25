package tasks;

import static utility.DateTimeUtility.format;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean checkDate(LocalDateTime date) {
        return date.isEqual(this.start) || date.isEqual(this.end)
                || (date.isAfter(this.start) && date.isBefore(this.end));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), format(this.start), format(this.end));
    }

    @Override
    public String toFileString() {
        String startEnd = format(this.start) + '-' + format(this.end);
        return String.format("E | %s | %s", super.toFileString(), startEnd);
    }
}
