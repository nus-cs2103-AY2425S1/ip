package lawrence.task;

import java.time.LocalDateTime;

import lawrence.utils.DateParser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor. Creates a {@link Deadline} object with the specified
     * task description and completion date.
     * <p>
     * The task will be marked as incomplete by default.
     * </p>
     *
     * @param description the name of the deadline
     * @param by the date to complete the deadline by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor. Creates a {@link Deadline} object with the specified
     * task description, completion status and completion date.
     * <p>
     * The task can be marked as complete or incomplete on creation.
     * </p>
     *
     * @param description the name of the deadline
     * @param isComplete indicates whether the deadline is complete
     * @param by the date by which the deadline should be completed
     */
    public Deadline(String description, boolean isComplete, LocalDateTime by) {
        super(description, isComplete);
        this.by = by;
    }

    /**
     * Returns a string representation of the object in a
     * standardised save format.
     *
     * @return a string representation of the object in save format
     */
    public String toSaveFormat() {
        return String.format("D | %s | %s",
                super.toSaveFormat(),
                DateParser.toOutputString(by));
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                DateParser.toOutputString(by));
    }
}
