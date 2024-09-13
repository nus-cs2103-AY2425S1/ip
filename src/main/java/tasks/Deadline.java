package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a subtype of Task that comes with an additional deadline
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Deadline represents a specific subset of Task that comes with an additional deadline
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by, int priority) {
        super(description, priority);
        this.by = by;
        Task.incrementTaskCount();
    }


    /*@Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by;
    }*/
    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by + " | " + super.getPriorityNum();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormatter) + ")";
    }

    public LocalDate getDeadlineDate() {
        return this.by;
    }
}
