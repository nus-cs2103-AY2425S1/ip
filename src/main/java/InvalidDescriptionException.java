package ip.derrick;

/**
 * Exception class that handles an invalid description for an input.
 */
public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
