package stelle.exception;

/**
 * Exception thrown when the date given by user for a task is not of the following format.
 * Child of stelle.exception.StelleException.
 * @author Lee Ze Hao (A0276123J)
 */

public class WrongDateFormatException extends StelleException {
    public WrongDateFormatException() {
        super("Dates must be in \"yyyy-MM-dd HH:mm\" format!");
    }
}
