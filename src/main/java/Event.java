package main.java;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, "E", isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (from: %s to: %s)", this.formatDate(this.start), this.formatDate(this.end));
        return superString + dueDate;
    }

    public String getStart() {
        return this.start.toString();
    }

    public String getEnd() {
        return this.end.toString();
    }
}
