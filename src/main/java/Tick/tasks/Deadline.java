package Tick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String toFileString() {
        return String.format("D | %s | %s | %s", super.getStatus() ? "1" : "0", super.getDescription(),
                this.by.toString());
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
