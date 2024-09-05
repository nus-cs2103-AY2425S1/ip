package julie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class Deadline extends Task {
    /** The due date of the task. */
    private LocalDate due;

    /**
     * Public constructor of the deadline task.
     *
     * @param taskDescription The description of the task.
     * @param due The due date of the deadline task.
     */
    public Deadline(String taskDescription, LocalDate due) {
        super(taskDescription);
        this.due = due;
    }
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)",
                super.toString(),
                this.due.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
    @Override
    public String toStorageString() {
        return String.format("D | %s | %s", this.taskString, this.due);
    }
}
