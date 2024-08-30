package bot.tasks;

import java.time.LocalDate;

public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        // TODO: Replace `D` with enum
        return "D | " + super.toData() + " | " + by;
    }
}