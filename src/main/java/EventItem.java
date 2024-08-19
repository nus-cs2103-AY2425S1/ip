/**
 * TodoItem represents a to-do entry with a specified time period
 */
public class EventItem extends TodoItem {
    /** Start datetime of the event **/
    private final String from;
    /** End datetime of the event **/
    private final String to;

    /**
     * Creates a new to-do item with the specified content and time period set for the to-do
     *
     * @param content The task description of the to-do item
     * @param from The starting datetime of the event
     * @param to The ending datetime of the event
     */
    public EventItem(String content, String from, String to) {
        super(content);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (from: %s to %s)",
                baseString.replaceFirst("T", "E"),
                this.from, this.to);

    }

}
