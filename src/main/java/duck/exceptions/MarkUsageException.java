package duck.exceptions;

/**
 * Class representing errors encountered by {@code MarkCommand} class.
 */
public class MarkUsageException extends RunOnTaskAtIndexUsageException {
    public MarkUsageException() {
        super("mark");
    }
}
