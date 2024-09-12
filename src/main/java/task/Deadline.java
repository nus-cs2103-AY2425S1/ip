package task;

import java.time.LocalDateTime;

/**
 * Represents the task with deadline
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for the Deadline
     * 
     * @param description Description of task
     * @param deadline The deadline for the task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for the Deadline
     * 
     * @param description Description of task
     * @param deadline The deadline for the task (In string format, to be converted)
     */
    public Deadline(String description, String deadline) {
        this(description, Converter.InputToDateTime(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Converter.DateTimeToOutput(deadline) + ")";
    }
}
