package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    private LocalDateTime by;

    public DeadLine(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + super.toFileFormat() + " | " + by.format(formatter);
    }

    public LocalDateTime getBy() {
        return by;
    }
}
