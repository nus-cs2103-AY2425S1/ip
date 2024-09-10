package tohru.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a to-do entry with a specified time period.
 */
public class EventItem extends TodoItem {
    /** Start datetime of the event */
    private final LocalDateTime from;
    /** End datetime of the event */
    private final LocalDateTime to;

    /**
     * Creates a new to-do item with the specified content and time period set for the to-do.
     *
     * @param content The task description of the to-do item.
     * @param from The starting datetime of the event.
     * @param to The ending datetime of the event.
     */
    public EventItem(String content, LocalDateTime from, LocalDateTime to) {
        super(content);

        assert from != null : "From datetime cannot be null";
        assert to != null : "To datetime cannot be null";
        assert (to.isAfter(from)) : "To datetime should be earlier than From datetime";

        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (from: %s to %s)",
                baseString.replaceFirst("T", "E"),
                this.from.format(DateTimeFormatter.ofPattern("E dd MMM yyyy HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("E dd MMM yyyy HHmm")));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        String baseString = super.getSaveString();
        return String.format("%s | %s | %s", baseString.replaceFirst("T", "E"), this.from, this.to);
    }

}
