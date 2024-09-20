package bot.tasks;

import java.time.LocalDate;

import bot.enums.TaskSymbol;

/**
 * Represents an deadline task.
 *
 * @author mongj
 */
public class Deadline extends Task {

    protected final LocalDate by;

    /**
     * Creates a new <code>Deadline</code> object.
     *
     * @param description of the event.
     * @param by when the deadline ends.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new <code>Deadline</code> object.
     *
     * @param description of the event.
     * @param isDone indicating if the task has been marked completed.
     * @param by when the deadline ends.
     */
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
        return TaskSymbol.DEADLINE.name + " | " + super.toData() + " | " + by;
    }
}
