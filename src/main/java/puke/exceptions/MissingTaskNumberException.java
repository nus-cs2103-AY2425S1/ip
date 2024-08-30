package puke.exceptions;

/**
 * Exception thrown when a task number is missing for a mark or unmark operation.
 */
public class MissingTaskNumberException extends PukeException {

    /**
     * Constructs a MissingTaskNumberException with a message indicating the type of operation.
     *
     * @param isDone true if the operation is to mark the task, false if it is to unmark the task
     */
    public MissingTaskNumberException(boolean isDone) {
        super("OOPS!!! You must specify a task number to " + (isDone ? "mark" : "unmark") + ".");
    }
}
