package puke.exceptions;

/**
 * Exception thrown when a task number is out of the valid range.
 */
public class TaskNumberOutOfBoundsException extends PukeException {

    /**
     * Constructs a TaskNumberOutOfBoundsException with a message indicating the out-of-bounds task number.
     *
     * @param taskNumber the task number that is out of bounds
     */
    public TaskNumberOutOfBoundsException(int taskNumber) {
        super("OOPS!!! The task number " + taskNumber + " is out of bounds.");
    }
}
