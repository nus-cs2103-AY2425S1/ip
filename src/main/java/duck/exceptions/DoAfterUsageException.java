package duck.exceptions;

/**
 * Class representing errors encountered by {@code DoAfterCommand} class.
 */
public class DoAfterUsageException extends UsageException {
    public DoAfterUsageException() {
        super("after <description> /after <earliest_date>", "description", "earliest_date");
    }
}
