package matcha.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + this.by.format(Task.getOutputFormat()) + ")";
    }
}
