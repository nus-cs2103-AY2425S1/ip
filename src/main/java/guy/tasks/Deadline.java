package guy.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task.
     *
     * @param name description of the task
     * @param by the task's deadline
     */
    public Deadline(String name, LocalDateTime by) {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
