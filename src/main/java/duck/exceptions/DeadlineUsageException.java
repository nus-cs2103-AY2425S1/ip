package duck.exceptions;

public class DeadlineUsageException extends UsageException {
    public DeadlineUsageException() {
        super("deadline <description> /by <due_date>", "description", "due_date");
    }
}
