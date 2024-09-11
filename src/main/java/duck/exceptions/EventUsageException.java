package duck.exceptions;

public class EventUsageException extends UsageException {
    public EventUsageException() {
        super("event <description> /from <start_time> /to <end_time>",
                "description", "start_time", "end_time");
    }
}
