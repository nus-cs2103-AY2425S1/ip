package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public Deadline(String description, boolean isDone, String dueBy) {
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
    public String toFileRecord() {
        return String.format("D | %s | %s", this.getDescription(), this.getDueBy());
    }

    public String getDueBy() {
        return this.dueBy;
    }

    public LocalDate getDueByLocalDate() {
        try {
            return LocalDate.parse(this.getDueBy());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDueBy());
    }
}
