package tira.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that represents tasks with deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    protected LocalDate endDate;

    /**
     * Initialises the Deadline class.
     *
     * @param description the Task description.
     * @param endDate Deadline of the deadline task, represented by LocalDate.
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate.format(OUT_FORMATTER) + ")";
    }
}
