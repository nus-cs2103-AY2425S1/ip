package exceptions;

public class TaskNumberOutOfBoundsException extends PukeException {
    public TaskNumberOutOfBoundsException(int taskNumber) {
        super("OOPS!!! The task number " + taskNumber + " is out of bounds.");
    }
}

