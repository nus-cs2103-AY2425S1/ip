package duck.exceptions;

public class UnmarkUsageException extends RunOnTaskAtIndexUsageException {
    public UnmarkUsageException() {
        super("unmark");
    }
}
