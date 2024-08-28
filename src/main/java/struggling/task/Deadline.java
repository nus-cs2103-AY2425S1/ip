package struggling.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class stores the description, the
 * completion state and the deadline of a task.
 */
public class Deadline extends Task {

    private final LocalDate by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Initializes a Deadline object.
     *
     * @param description Description of Task.
     * @param by          The date which the Task has to be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(formatter));
    }

    @Override
    public String getState() {
        return String.format("D | %s | %s", super.getState(), this.by.toString());
    }
}
