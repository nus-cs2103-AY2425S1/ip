package Naega.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}