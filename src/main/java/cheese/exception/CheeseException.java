package cheese.exception;

/**
 * Parent exception for CheeseBot
 */
public class CheeseException extends Exception {
    /**
     * Creates a CheeseException
     * @param message error message to inform user
     */
    public CheeseException(String message) {
        super(message);
    }
}
