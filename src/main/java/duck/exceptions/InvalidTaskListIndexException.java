package duck.exceptions;

public class InvalidTaskListIndexException extends Exception {
    private RunOnTaskAtIndexUsageException usageException;

    public InvalidTaskListIndexException(RunOnTaskAtIndexUsageException usageException) {
        this.usageException = usageException;
    }

    @Override
    public String toString() {
        return String.format("%s\n<task_number> should be an integer.",
                usageException.toString());
    }
}
