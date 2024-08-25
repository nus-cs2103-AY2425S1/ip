package lama.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DATE_FORMAT)
                + " to: " + to.format(DATE_FORMAT) + ")";
    }

    @Override
    public String toFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(FILE_FORMAT) + " | " + to.format(FILE_FORMAT);
    }
}