package dudu.task;

import dudu.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String formatString() {
        String from = this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String to = this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("E | %s | %s | %s", super.formatString(), from, to);
    }

    @Override
    public String toString() {
        String from = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String to = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E] %s (from: %s to: %s)", super.toString(), from, to);
    }
}
