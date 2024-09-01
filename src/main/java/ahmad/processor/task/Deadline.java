package ahmad.processor.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    private Deadline(Deadline a, boolean state) {
        super(a, state);
        this.deadline = a.deadline;
    }

    @Override
    public Deadline mark() {
        return new Deadline(this, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this, false);
    }

    @Override
    public String getExtraInformation() {
        return "(by: " + super.formatDate(this.deadline) + ")";
    }

    @Override
    public String getSymbol() {
        return "[D]";
    }
}
