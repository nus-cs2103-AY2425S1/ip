package lbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime dueDate;
    private final String taskType = "[D]";

    /**
     * Constructs {@link Deadline} object.
     * Sets deadline to be incomplete.
     *
     * @param description of deadline.
     * @param dueDate     of deadline.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.type = taskType;
    }

    /**
     * Constructs {@link Deadline} object.
     * Allows status of {@link Deadline} to be declared.
     *
     * @param description of deadline.
     * @param isComplete  status of the deadline.
     * @param dueDate     of deadline.
     */
    public Deadline(String description, boolean isComplete, LocalDateTime dueDate) {
        super(description, isComplete);
        this.dueDate = dueDate;
        this.type = taskType;
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description
                + " (by: " + dueDate.format(dateTimeFormat) + ")";
    }
}
