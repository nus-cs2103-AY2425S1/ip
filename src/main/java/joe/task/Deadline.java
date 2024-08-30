package joe.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    @Override
    public String serialize() {
        return String.format("D|%b|%s|%s", this.isDone, this.description, this.by);
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
