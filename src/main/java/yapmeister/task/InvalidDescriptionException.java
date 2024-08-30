package yapmeister.task;

/**
 * Represents an Exception caused by an Invalid Description input
 */
public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
