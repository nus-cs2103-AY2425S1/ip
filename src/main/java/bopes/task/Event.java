package bopes.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        this.start = LocalDateTime.parse(start.toLowerCase(), formatter);
        this.end = LocalDateTime.parse(end.toLowerCase(), formatter);

        if (this.end.isBefore(this.start)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.start.format(outputFormat) + " to: " + this.end.format(outputFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.start.format(fileFormat) + " | " + this.end.format(fileFormat);
    }
}
