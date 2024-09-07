package exceptions;

/**
 * Exception thrown when a completion command is run on a completed task.
 *
 * @author IsaacPangTH
 */
public class AlreadyCompletedException extends HimException {

    /**
     * Constructor for<code>AlreadyCompletedException</code>
     */
    public AlreadyCompletedException() {
        super("the task has already been completed");
    }
}
