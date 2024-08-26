package papadom.Exceptions;

/**
 * Exception thrown when the task number provided does not exist.
 */
public class WrongTaskNumberException extends Exception {
    /**
     * Constructs a WrongTaskNumberException with a message indicating that
     * the specified task number is invalid.
     */
    public WrongTaskNumberException() {
        super("There is no such task number. Please check again!");
    }
}
