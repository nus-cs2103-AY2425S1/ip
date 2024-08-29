package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate dueBy;

    public Deadline(String description, LocalDate dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public LocalDate getDueBy() {
        return this.dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDueBy());
    }
}
