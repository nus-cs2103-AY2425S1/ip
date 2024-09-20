package sadcat.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the task
     * @param by The deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(printFormatter) + ")";
    }

    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + by.format(saveFormatter);
    }
}
