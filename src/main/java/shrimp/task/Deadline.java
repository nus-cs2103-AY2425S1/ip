package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return getType() + super.toString() + " (by: " + by.format(pattern) + ")";
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(getDescription(), this.by, true);
    }

    @Override
    public Deadline markAsNotDone() {
        return new Deadline(getDescription(), this.by, false);
    }

    public String getType() {
        return "[D]";
    }
}