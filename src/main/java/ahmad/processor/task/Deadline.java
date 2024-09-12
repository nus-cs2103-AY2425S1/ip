package ahmad.processor.task;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Deadline class that extends from Task
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline instance based on the name and deadline.
     *
     * @param name     Name of deadline.
     * @param deadline Deadline date of deadline.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    private Deadline(Deadline a, boolean state) {
        super(a, state);
        this.deadline = a.deadline;
    }

    @Override
    protected long getTime() {
        return this.deadline.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
