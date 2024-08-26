import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TodoItem represents a to-do entry with a specified time period
 */
public class EventItem extends TodoItem {
    /** Start datetime of the event **/
    private final LocalDateTime from;
    /** End datetime of the event **/
    private final LocalDateTime to;

    /**
     * Creates a new to-do item with the specified content and time period set for the to-do
     *
     * @param content The task description of the to-do item
     * @param from The starting datetime of the event
     * @param to The ending datetime of the event
     */
    public EventItem(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (from: %s to %s)",
                baseString.replaceFirst("T", "E"),
                this.from.format(DateTimeFormatter.ofPattern("E dd MMM yyyy HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("E dd MMM yyyy HHmm")));

    }

    @Override
    public String getSaveString() {
        String baseString = super.getSaveString();
        return String.format("%s | %s | %s", baseString.replaceFirst("T", "E"), this.from, this.to);
    }

}
