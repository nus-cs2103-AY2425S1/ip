package stelle.exception;

/**
 * Parent for all exceptions related to tasks.
 * Child of stelle.exception.StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class TaskException extends StelleException {
    public TaskException(String message) {
        super(message);
    }
}
