package barney.data.exception;

/**
 * Exception thrown when an invalid command is encountered.
 * 
 * @param message The detail message of the exception.
 */
public class InvalidCommandException extends BarneyException {
    public InvalidCommandException(String message) {
        super(message);
    }

}
