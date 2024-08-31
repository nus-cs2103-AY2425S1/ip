package main.java.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from, to;

    public Event(String name, String from, String to) {
        super(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
        return "[E]" + super.toString() + String.format("(from: %s to: %s)",
                this.from.format(formatter), this.to.format(formatter));
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %d | %s | %s | %s", markedInt, this.getName(),
                this.from.format(formatter), this.to.format(formatter));
    }
}
