package duck.exceptions;

public class TodoUsageException extends UsageException {
    public TodoUsageException() {
        super("todo <description>", "description");
    }
}
