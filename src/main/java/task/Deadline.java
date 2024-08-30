package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate dueBy;

    public Deadline(String description, LocalDate dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public Deadline(String description, boolean isDone, LocalDate dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public Deadline setAsDone() {
        return new Deadline(this.getDescription(), true, this.getDueBy());
    }

    @Override
    public Deadline setAsUndone() {
        return new Deadline(this.getDescription(), false, this.getDueBy());
    }

    @Override
    public Deadline setDescription(String description) {
        return new Deadline(this.getDescription(), this.isDone(), this.getDueBy());
    }

    public LocalDate getDueBy() {
        return this.dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDueBy());
    }
}
