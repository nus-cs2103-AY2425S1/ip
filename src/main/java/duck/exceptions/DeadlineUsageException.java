package duck.exceptions;

/**
 * Class representing errors encountered by {@code DeadlineCommand} class.
 */
public class DeadlineUsageException extends UsageException {
    public DeadlineUsageException() {
        super("deadline <description> /by <due_date>", "description", "due_date");
    }
}
