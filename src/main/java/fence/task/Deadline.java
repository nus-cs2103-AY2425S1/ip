package fence.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which contains a description and deadline.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a deadline task with the specified description and deadline.
     * @param description Description of the task.
     * @param by Deadline for the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this deadline task with the task type, completion status, description and
     * deadline.
     * @return String representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the string representation within the storage file of this deadline task with the task type, completion
     * status, description and deadline.
     * @return String representation within the storage file of this deadline task.
     */
    @Override
    public String toTxt() {
        return "DEADLINE " + super.toTxt() + " /by " + this.by;
    }

    @Override
    public boolean isDue() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.equals(this.by);
    }
}
