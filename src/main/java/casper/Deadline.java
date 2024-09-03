package casper;

import java.time.LocalDate;

/**
 * A class representing the Deadline task
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for the deadline task
     * @param description Description of the task
     * @param isDone Represents the completion status of the task
     * @param deadline The date when the task is due
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, "D", isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (by: %s)", this.formatDate(this.deadline));
        return superString + dueDate;
    }

    public String getDeadline() {
        return this.deadline.toString();
    }
}
