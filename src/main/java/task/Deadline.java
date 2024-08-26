package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task
 *
 * @author dwsc37
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + deadline;
    }
}

