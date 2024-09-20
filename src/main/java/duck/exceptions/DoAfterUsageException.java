package duck.exceptions;

public class DoAfterUsageException extends UsageException {
    public DoAfterUsageException() {
        super("after <description> /after <earliest_date>", "description", "earliest_date");
    }
}
