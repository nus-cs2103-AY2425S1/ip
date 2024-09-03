package drbrown.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(boolean status, String description, LocalDateTime start, LocalDateTime end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "E | " + getStatus() + " | " + getDescription() + " | " + start.format(FILE_DATE_TIME_FORMATTER) + " | " + end.format(FILE_DATE_TIME_FORMATTER);
    }

    @Override
    public String toUIString() {
        return "The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n";
    }

    @Override
    public String toString() {
        return "[E][" + (getStatus() ? "X" : " ") + "] " + getDescription() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " end: " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
