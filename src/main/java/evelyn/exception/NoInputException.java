package evelyn.exception;

/**
 * Represents an exception that is thrown when no input has been detected in Evelyn.
 */
public class NoInputException extends EvelynException{
    public NoInputException(String msg) {
        super(msg);
    }
}
