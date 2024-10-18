package papagu.ui;

/**
 * Represents an exception thrown when an illegal todo is detected.
 */
public class IllegalTodoException extends IllegalArgumentException {
    public IllegalTodoException(String message) {
        super(message);
    }
}
