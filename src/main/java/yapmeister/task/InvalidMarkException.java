package yapmeister.task;

/**
 * Represents an Exception caused by an Invalid Mark input
 */
public class InvalidMarkException extends Exception {
    public InvalidMarkException(String message) {
        super(message);
    }
}
