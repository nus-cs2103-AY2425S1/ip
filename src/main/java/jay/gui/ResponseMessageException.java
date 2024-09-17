package jay.gui;

/**
 * Represents an exception thrown when the response message is a message cause by other exception.
 */
public class ResponseMessageException extends Exception {
    public ResponseMessageException(String message) {
        super(message);
    }
}
