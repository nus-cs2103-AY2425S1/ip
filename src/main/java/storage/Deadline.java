package storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
