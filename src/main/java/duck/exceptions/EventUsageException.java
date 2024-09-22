package duck.exceptions;

/**
 * Class representing errors encountered by {@code EventCommand} class
 */
public class EventUsageException extends UsageException {
    /**
     * Constructor for EventUsageException
     */
    public EventUsageException() {
        super("event <description> /from <start_date> /to <end_date>",
                "description", "start_date", "end_date");
    }
}
