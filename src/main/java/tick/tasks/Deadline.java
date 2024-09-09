package tick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which has a deadline.
 */
public class Deadline extends Task {
    private LocalDate dueBy;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param dueBy Deadline of the task.
     */
    public Deadline(String description, LocalDate dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Converts the deadline task to a string format suitable for file storage.
     * The format is "D | [status] | description | deadline".
     *
     * @return A string representation of the deadline task for file storage.
     */
    @Override
    public String toStorageFormat() {
        return String.format("D | %s | %s | %s", super.getStatus() ? "1" : "0", super.getDescription(),
                this.dueBy.toString());
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueBy.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
