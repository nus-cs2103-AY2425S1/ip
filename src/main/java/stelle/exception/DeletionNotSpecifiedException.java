package stelle.exception;

/**
 * Exception thrown when there is no list number given during deletion command.
 * Child of stelle.exception.StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class DeletionNotSpecifiedException extends StelleException {
    public DeletionNotSpecifiedException() {
        super("You have to specify a task before I can delete it!");
    }
}
