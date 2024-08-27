package GPT;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the GPT application.
 * This class extends the Task class to include a specific date and time by which the task should be completed.
 */
class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns a string format suitable for saving the deadline task to a file.
     *
     * @return A string representation of the deadline task in a format suitable for file storage.
     */
    @Override
    public String toFileFormat() {
        if (by != null) {
            return String.format("D | %d | %s | %s",
                    isDone ? 1 : 0,
                    description,
                    by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } else {
            return String.format("D | %d | %s | [Invalid Date]",
                    isDone ? 1 : 0,
                    description);
        }
    }

    /**
     * Returns a string representation of the deadline task, including the deadline date and time.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        if (by != null) {
            return super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
        } else {
            return super.toString() + " (by: [Invalid Date])";
        }
    }
}