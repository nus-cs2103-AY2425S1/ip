/**
 * Exception for handling incorrectly structured queries
 */
public class InputException extends CloudException {
    public InputException(String message) {
        super(message);
    }
}
