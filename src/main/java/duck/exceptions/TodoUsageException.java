package duck.exceptions;

/**
 * Class representing errors encountered by {@code TodoCommand} class.
 */
public class TodoUsageException extends UsageException {
    public TodoUsageException() {
        super("todo <description>", "description");
    }
}
