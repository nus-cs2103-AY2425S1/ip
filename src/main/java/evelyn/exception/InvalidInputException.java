package evelyn.exception;

/**
 * Represents an exception thrown when an invalid input has been detected in Evelyn.
 */
public class InvalidInputException extends NoInputException{
    public InvalidInputException(String msg) {
        super(msg);
    }
}
