package stan.exceptions;

/**
 * Represents a general exception used in the Stan application.
 */
public class StanException extends Exception {
    /**
     * Constructs a StanException with the specified detail message.
     *
     * @param message The detail message.
     */
    public StanException(String message) {
        super(message);
    }
}
