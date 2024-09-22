package duck.exceptions;

/**
 * Class representing errors encountered by {@code DeleteCommand} class.
 */
public class DeleteUsageException extends RunOnTaskAtIndexUsageException {
    public DeleteUsageException() {
        super("delete");
    }
}
