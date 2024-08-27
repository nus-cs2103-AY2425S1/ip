package waterfall.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    private LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateString, formatter);
    }

    private String getFormattedTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    private String getBeautifulTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getBeautifulTime(start) + " to: " + getBeautifulTime(end) + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getTitle() + " | " + getFormattedTime(start) + " | " + getFormattedTime(end);
        } else {
            return "E | 0 | " + this.getTitle() + " | " + getFormattedTime(start) + " | " + getFormattedTime(end);
        }
    }
}
