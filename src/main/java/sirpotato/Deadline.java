package sirpotato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task with description and deadline date
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task with given description and deadline date
     *
     * @param description the description of the deadline task
     * @param by the deadline date the task should be complete by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public LocalDate getByDate() {
        return by;
    }

    @Override
    public boolean isDeadline() {
        return true;
    }

}
