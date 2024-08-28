package nathanbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type, description, and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[D]" + super.toString() + " (by: " + by.format(dateTimeFormatter) + ")";
    }
}