package skywalker.exception;

/**
 * throws a special kind of exception called empty description exception
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
