package friday.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    public LocalDate getFrom() {
        return this.from.toLocalDate();
    }

    public LocalDate getTo() {
        return this.to.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}