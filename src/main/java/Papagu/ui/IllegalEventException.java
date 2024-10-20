package papagu.ui;

/**
 * Represents an exception thrown when an invalid event is created
 */
public class IllegalEventException extends IllegalArgumentException {
    public IllegalEventException(String message) {
        super(message);
    }
}
