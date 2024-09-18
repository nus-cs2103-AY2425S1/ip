package tohru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a to-do entry with a specified deadline.
 */
public class DeadlineItem extends TodoItem {
    /** Deadline that specifies when the to-do is due */
    private final LocalDateTime deadline;

    /**
     * Creates a new to-do item with the specified content and deadline.
     *
     * @param content The task description of the to-do item.
     * @param deadline The deadline of the to-do item.
     */
    public DeadlineItem(String content, LocalDateTime deadline) {
        super(content);

        assert deadline != null : "Deadline datetime cannot be null";

        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (by: %s)", baseString.replaceFirst("T", "D"),
                this.deadline.format(DateTimeFormatter.ofPattern("E dd MMM yyyy HHmm")));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        String baseString = super.getSaveString();
        return String.format("%s | %s", baseString.replaceFirst("T", "D"), this.deadline);
    }

}
