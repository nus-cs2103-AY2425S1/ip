package tira.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that represents tasks with deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected LocalDate by;

    /**
     * Initialises the Deadline class.
     * @param description the Task description.
     * @param by Deadline of the deadline task, represented by LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getEndDate() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUT_FORMATTER) + ")";
    }
}
