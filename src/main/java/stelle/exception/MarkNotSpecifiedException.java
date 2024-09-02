package stelle.exception;

/**
 * Exception thrown when there is no list number given during mark/unmark command.
 * Child of stelle.exception.StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class MarkNotSpecifiedException extends StelleException {
    public MarkNotSpecifiedException() {
        super("Mark / Unmark must specify a task!");
    }
}
