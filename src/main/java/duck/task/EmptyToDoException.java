package duck.task;

/**
 * Exception thrown to indicate that a ToDo is empty.
 */
public class EmptyToDoException extends Exception {
    /**
     * Constructs new EmptyToDoException with specified message.
     */
    public EmptyToDoException(String message) {
        super(message);
    }
}
