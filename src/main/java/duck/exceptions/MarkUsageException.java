package duck.exceptions;

public class MarkUsageException extends RunOnTaskAtIndexUsageException {
    public MarkUsageException() {
        super("mark");
    }
}
