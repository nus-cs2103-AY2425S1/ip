package noosy.task;

import noosy.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, boolean status, LocalDateTime start, LocalDateTime end) {
        super(name, status);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    @Override
    public String storeInFile() {
        String startingTime = (this.start != null) ? start.format(OUTPUT_FORMATTER) : "Invalid start date";
        String endingTime = (this.end != null) ? end.format(OUTPUT_FORMATTER) : "Invalid end date";
        return String.format("E | %s | %s | %s - %s", super.storeInFile(), this.name, startingTime, endingTime);
    }

    @Override
    public String toString() {
        String formattedStart = (this.start != null) ? start.format(OUTPUT_FORMATTER) : "Invalid start date";
        String formattedEnd = (this.end != null) ? end.format(OUTPUT_FORMATTER) : "Invalid end date";
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedStart, formattedEnd);
    }
}
