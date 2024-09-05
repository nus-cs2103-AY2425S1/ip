package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", (isDone ? 1 : 0), description, by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}