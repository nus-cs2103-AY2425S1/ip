package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private final String PATTERN = "MMM d yyyy HHmm";

    /**
     * Creates a new Deadline task.
     *
     * @param desc Description of the task
     * @param mark Whether the task is marked as done
     * @param deadline Deadline for the task
     * @throws MissingDescriptionException If the description is missing
     */
    public Deadline(String desc, Boolean mark, LocalDateTime deadline) throws MissingDescriptionException {
        super(desc, mark);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return Task description with deadline
     */
    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(),
                deadline.format(DateTimeFormatter.ofPattern(PATTERN)));
    }

    /**
     * Returns a string format of the task for saving to a file.
     *
     * @return Task details in a format suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s", super.toFileFormat(),
                deadline.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
