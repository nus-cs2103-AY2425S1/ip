package duck.exceptions;

public class DeleteUsageException extends RunOnTaskAtIndexUsageException {
    public DeleteUsageException() {
        super("delete");
    }
}
