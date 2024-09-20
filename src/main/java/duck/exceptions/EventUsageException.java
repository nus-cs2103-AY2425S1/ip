package duck.exceptions;

public class EventUsageException extends UsageException {
    public EventUsageException() {
        super("event <description> /from <start_date> /to <end_date>",
                "description", "start_date", "end_date");
    }
}
