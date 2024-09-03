package susan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * This class is part of the Susan task management application.
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
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by "
            + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            + ")";
    }
}
