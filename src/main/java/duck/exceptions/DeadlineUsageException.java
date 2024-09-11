package duck.exceptions;

public class DeadlineUsageException extends UsageException {
    public DeadlineUsageException() {
        super("deadline <description> /by <deadline>", "description", "deadline");
    }
}
