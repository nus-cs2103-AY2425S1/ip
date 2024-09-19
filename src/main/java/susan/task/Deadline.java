package susan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * This class is 1 of 3 types of tasks in the Susan task management application.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task in yyyy-MM-dd.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by "
            + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            + ")";
    }

    @Override
    public LocalDate getDate() {
        return by;
    }
}
