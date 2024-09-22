package duck.exceptions;

/**
 * Class representing errors encountered by {@code UnmarkCommand} class.
 */
public class UnmarkUsageException extends RunOnTaskAtIndexUsageException {
    public UnmarkUsageException() {
        super("unmark");
    }
}
